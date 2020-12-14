package com.demo.service;

import com.demo.dao.ActivityDao;
import com.demo.dao.ActivityPrizeDao;
import com.demo.dao.PrizeDao;
import com.demo.entity.Activity;
import com.demo.entity.ActivityPrize;
import com.demo.entity.Prize;
import com.demo.entity.User;
import com.demo.exception.BadRequestException;
import com.demo.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 转盘抽奖类
 */
@Service
public class LuckDrawService {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ActivityPrizeDao activityPrizeDao;

    @Autowired
    private PrizeDao prizeDao;

    public void lottery(Long userId, Long activityId) {
        User user = userService.selectUserById(userId);
        if (Objects.isNull(user)) {
            throw new BadRequestException(ErrorCode.USER_NOT_EXIST);
        }
        Activity activity = activityDao.selectActivityById(activityId);
        if (Objects.isNull(activity)) {
            throw new BadRequestException(ErrorCode.ACTIVITY_NOT_EXIST);
        }
        //TODO 添加活动开始时间和活动状态校验，查询玩家剩余抽奖次数

        //查询活动关联的奖品
        List<ActivityPrize> activityPrizes = activityPrizeDao.selectActivityPrizeByActivityId(activityId);
        //查询活动下的奖品
        List<Prize> prizes = prizeDao.selectPrizeByActivityId(activityId);
        Map<Long, Prize> prizeMap = prizes.stream().collect(Collectors.toMap(Prize::getId, Function.identity()));
        for (ActivityPrize activityPrize : activityPrizes) {
            activityPrize.setPrize(prizeMap.get(activityPrize.getPrizeId()));
        }
        //抽奖逻辑
    }
}
