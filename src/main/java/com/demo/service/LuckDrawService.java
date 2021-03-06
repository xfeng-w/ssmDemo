package com.demo.service;

import com.demo.entity.*;
import com.demo.exception.BadRequestException;
import com.demo.exception.ErrorCode;
import com.demo.vo.LuckDrawVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 转盘抽奖类
 */
@Service
public class LuckDrawService {

    private static final Logger logger = LoggerFactory.getLogger(LuckDrawService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityPrizeService activityPrizeService;

    @Autowired
    private UserLuckDrawNumberService userLuckDrawNumberService;

    @Autowired
    private LuckDrawRecordService luckDrawRecordService;

    @Autowired
    private PrizeService prizeService;

    @Transactional
    public LuckDrawVO lottery(Long userId, String activityCode) {
        User user = userService.selectUserById(userId);
        if (Objects.isNull(user)) {
            throw new BadRequestException(ErrorCode.USER_NOT_EXIST);
        }
        Activity activity = activityService.selectByCode(activityCode);
        if (Objects.isNull(activity)) {
            throw new BadRequestException(ErrorCode.ACTIVITY_NOT_EXIST);
        }
        // 活动开始时间结束时间和活动状态校验，查询玩家剩余抽奖次数
        if (!activity.getStatus().equals(1)) {
            throw new BadRequestException(ErrorCode.ACTIVITY_ID_CLOSE);
        }
        long currentTime = System.currentTimeMillis();
        if (activity.getStartTime().getTime() > currentTime) {
            // 活动未开始
            throw new BadRequestException(ErrorCode.ACTIVITY_NOT_START);
        }
        if (activity.getEndTime().getTime() < currentTime) {
            // 活动已结束
            throw new BadRequestException(ErrorCode.ACTIVITY_IS_END);
        }
        // 查询用户的剩余抽奖次数
        UserLuckDrawNumber userLuckDrawNumber = userLuckDrawNumberService.selectByUserIdAndActivityId(userId, activity.getId(), new Date());
        if (Objects.isNull(userLuckDrawNumber) || userLuckDrawNumber.getLuckDrawNumber() < 1) {
            // 用户没有剩余抽奖次数了
            throw new BadRequestException(ErrorCode.USER_HAVE_NO_LUCK_DRAW_NUMBER);
        }
        // 保存抽奖记录
        LuckDrawRecord luckDrawRecord = new LuckDrawRecord();
        luckDrawRecord.setActivityId(activity.getId());
        Date recordDate = new Date();
        luckDrawRecord.setCreatedTime(recordDate);
        luckDrawRecord.setUpdatedTime(recordDate);
        luckDrawRecord.setLuckDrawTime(recordDate);
        luckDrawRecord.setUserId(userId);
        luckDrawRecord.setVersion(1);
        List<LuckDrawRecord> luckDrawRecords = luckDrawRecordService.listByUserId(userId, activity.getId());
        // 判断用户应该走活动的哪个中奖概率
        double probability = this.getProbability(luckDrawRecords, activity);
        // 生成1-10000之间的随机数.
        Random rand = new Random();
        int randNumber = rand.nextInt(10000) + 1;
        // 随机数落在概率中间触发抽奖
        LuckDrawVO result = new LuckDrawVO();
        if (randNumber <= probability * 100) {
            // 查询活动关联的奖品
            List<ActivityPrize> activityPrizes = activityPrizeService.selectByActivityId(activity.getId());
            // 查询活动下的奖品
            List<Prize> prizes = prizeService.selectByActivityId(activity.getId());
            Map<Long, Prize> prizeMap = prizes.stream().collect(Collectors.toMap(Prize::getId, Function.identity()));
            for (ActivityPrize activityPrize : activityPrizes) {
                activityPrize.setPrize(prizeMap.get(activityPrize.getPrizeId()));
            }
            ActivityPrize bigPrize = null;
            for (ActivityPrize item : activityPrizes) {
                if (item.getPrize().getPrizeType().equals(5)) {
                    bigPrize = item;
                }
            }
            // 根据条件筛选使用的奖品
            List<ActivityPrize> useActivityPrizes = chooseUsePrize(activity.getId(), luckDrawRecords, activityPrizes);
            // 获得奖品
            ActivityPrize activityPrize = this.getPrize(useActivityPrizes);
            if (Objects.nonNull(activityPrize)) {
                luckDrawRecord.setActivityPrizeId(activityPrize.getId());
                result.setWinning(true);
                result.setPrizeName(activityPrize.getName());
                result.setPrizeId(activityPrize.getPrizeId());
                result.setPrizeNum(activityPrize.getPrizeNum());
                result.setPrizeType(activityPrize.getPrize().getPrizeType());
                // 如果获取的是代币，判断是否发放大奖
                if (result.getPrizeType().equals(4)) {
                    // 玩家获得代币的数量
                    int userTokensNum = (int) luckDrawRecords.stream().filter(o -> Objects.nonNull(o.getActivityPrizeId()))
                            .filter(o -> o.getPrizeType().equals(4)).mapToDouble(LuckDrawRecord::getPrizeNum).sum();
                    if (userTokensNum + result.getPrizeNum() > 100) {
                        LuckDrawRecord bigPrizeRecord = new LuckDrawRecord();
                        bigPrizeRecord.setActivityId(activity.getId());
                        bigPrizeRecord.setCreatedTime(recordDate);
                        bigPrizeRecord.setUpdatedTime(recordDate);
                        bigPrizeRecord.setLuckDrawTime(recordDate);
                        bigPrizeRecord.setUserId(userId);
                        bigPrizeRecord.setVersion(1);
                        bigPrizeRecord.setActivityPrizeId(bigPrize.getId());
                        luckDrawRecordService.add(bigPrizeRecord);
                    }
                }
            }
        } else {
            result.setWinning(false);
        }
        luckDrawRecordService.add(luckDrawRecord);
        // 抽奖次数-1
        userLuckDrawNumberService.toReduceLuckDrawNum(userId, activity.getId(), new Date());
        return result;
    }

    private ActivityPrize getPrize(List<ActivityPrize> activityPrizes) {
        int prizeNum = (int) activityPrizes.stream().mapToDouble(o -> o.getProbability() * 100).sum();
        Random rand = new Random();
        int randNumber = rand.nextInt(prizeNum) + 1;
        int startNum = 0;
        for (ActivityPrize item : activityPrizes) {
            int itemProbability = (int) (item.getProbability() * 100);
            if (randNumber >= startNum + 1 && randNumber <= itemProbability + startNum) {
                return item;
            }
            startNum += itemProbability;
        }
        return null;
    }

    /**
     * 根据配置选择使用的奖品
     *
     * @param activityPrizes
     * @return
     */
    private List<ActivityPrize> chooseUsePrize(Long activityId, List<LuckDrawRecord> luckDrawRecords, List<ActivityPrize> activityPrizes) {
        List<LuckDrawRecord> bigPrizeRecord = luckDrawRecordService.listByPrizeTypeAndActivityId(5, activityId);

        ActivityPrize bigPrize = null;
        for (ActivityPrize item : activityPrizes) {
            if (item.getPrize().getPrizeType().equals(5)) {
                bigPrize = item;
            }
        }
        if (Objects.isNull(bigPrize)) {
            throw new BadRequestException(ErrorCode.BIG_PRIZE_NOT_EXIST);
        }
        // 终极大奖总的获取数量
        int bigPrizeNum = bigPrizeRecord.size();
        // 玩家获得代币的数量
        int userTokensNum = (int) luckDrawRecords.stream().filter(o -> Objects.nonNull(o.getActivityPrizeId()))
                .filter(o -> o.getPrizeType().equals(4)).mapToDouble(LuckDrawRecord::getPrizeNum).sum();
        Date today = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        // 玩家当天获取的代币数量
        int userTodayTokensNum = (int) luckDrawRecords.stream().filter(o -> Objects.nonNull(o.getActivityPrizeId()))
                .filter(o -> fmt.format(today).equals(fmt.format(o.getLuckDrawTime())))
                .filter(o -> o.getPrizeType().equals(4)).mapToDouble(LuckDrawRecord::getPrizeNum).sum();
        // 玩家获得终极大奖得数量
        int userBigPrizeNum = (int) luckDrawRecords.stream().filter(o -> Objects.nonNull(o.getActivityPrizeId()))
                .filter(o -> o.getPrizeType().equals(5)).mapToDouble(LuckDrawRecord::getPrizeNum).sum();
        Iterator<ActivityPrize> iterator = activityPrizes.listIterator();
        while (iterator.hasNext()) {
            ActivityPrize item = iterator.next();
            if (!item.getLuckyDrawPrize()) {
                iterator.remove();
                continue;
            }
            // 删除不可以继续掉落的代币奖励
            if (item.getPrize().getPrizeType().equals(4)) {
                // 超级大奖发放完毕，并且该玩家已经获得过超级大奖
                if (bigPrizeNum >= bigPrize.getPrize().getMaxNum() && userBigPrizeNum >= 1) {
                    iterator.remove();
                    continue;
                }
                // 超级大奖已经发放完毕并且玩家没获得过超级大奖，可以获得代币但是不能超过100个
                if (bigPrizeNum >= bigPrize.getPrize().getMaxNum() && userBigPrizeNum <= 1) {
                    if (userTokensNum + item.getPrizeNum() >= 100) {
                        iterator.remove();
                        continue;
                    }
                }
                // 玩家每天最多获取n个代币
                if (userTodayTokensNum + item.getPrizeNum() > item.getPrize().getDayMaxNum()) {
                    iterator.remove();
                }
            }
        }
        return activityPrizes;
    }

    /**
     * 获取该用户参与活动的中奖率
     *
     * @param activity
     * @return
     */
    private Double getProbability(List<LuckDrawRecord> luckDrawRecords, Activity activity) {

        boolean highProbability = false;
        boolean lowProbability = false;
        // 判断是否满足高概率
        if (luckDrawRecords.size() <= 10) {
            highProbability = true;
        }
        // 玩家获得的红包数量
        double redPacketNum = luckDrawRecords.stream().filter(o -> Objects.nonNull(o.getActivityPrizeId()))
                .filter(o -> o.getPrizeType().equals(1)).mapToDouble(LuckDrawRecord::getPrizeNum).sum();
        // 玩家获得的元宝数量
        double wingNum = luckDrawRecords.stream().filter(o -> Objects.nonNull(o.getActivityPrizeId()))
                .filter(o -> o.getPrizeType().equals(2)).mapToDouble(LuckDrawRecord::getPrizeNum).sum();
        // 判断是否满足低概率
        if (redPacketNum > 40 || (luckDrawRecords.size() > 15 && wingNum > 300) || luckDrawRecords.size() > 30) {
            lowProbability = true;
        }
        // 如果同时满足高概率和低概率走低概率
        if (highProbability && lowProbability) {
            return activity.getLowProbability();
        } else if (lowProbability) {
            return activity.getLowProbability();
        } else if (highProbability) {
            return activity.getHighProbability();
        } else {
            return activity.getMediumProbability();
        }
    }
}
