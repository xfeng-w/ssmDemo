package com.demo.service;

import com.demo.entity.UserSignInRecord;
import com.demo.exception.BadRequestException;
import com.demo.exception.ErrorCode;
import com.demo.vo.SignInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 签到
 */
@Service
public class SignInService {

    @Autowired
    private UserSignInRecordService userSignInRecordService;

    @Autowired
    private UserLuckDrawNumberService userLuckDrawNumberService;

    @Transactional
    public SignInVo signIn(Long activityId, Long userId) {
        UserSignInRecord userSignInRecord = userSignInRecordService.selectByActivityIdAndUserId(activityId, userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        if (Objects.isNull(userSignInRecord)) {
            // 如果该用户没有签到记录，生成一条新的签到记录
            userSignInRecord = new UserSignInRecord();
            userSignInRecord.setActivityId(activityId);
            userSignInRecord.setUserId(userId);
            userSignInRecord.setSignInDays(1);
            userSignInRecord.setCreatedTime(today);
            userSignInRecord.setUpdatedTime(today);
            userSignInRecord.setVersion(1);
            userSignInRecord.setLastSignInDates(today);
            userSignInRecordService.add(userSignInRecord);
        } else {
            // 存在签到记录更新最后签到时间
            Date lastSignDate = userSignInRecord.getLastSignInDates();
            if (sdf.format(today).equals(sdf.format(lastSignDate))) {
                throw new BadRequestException(ErrorCode.REPEAT_SIGN_IN);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DATE, -1);
            Date previousDay = calendar.getTime();
            // 如果断签或签到满28天重置签到次数
            if (userSignInRecord.getSignInDays() >= 28 || !sdf.format(previousDay).equals(sdf.format(lastSignDate))) {
                userSignInRecord.setLastSignInDates(today);
                userSignInRecord.setSignInDays(1);
            } else {
                userSignInRecord.setSignInDays(userSignInRecord.getSignInDays() + 1);
                userSignInRecord.setLastSignInDates(today);
            }
            userSignInRecord.setUpdatedTime(today);
            userSignInRecordService.updateByActivityIdAndUserId(userSignInRecord);
        }
        SignInVo signInVo = new SignInVo();
        signInVo.setActivityId(activityId);
        signInVo.setUserId(userId);
        signInVo.setSignInDays(userSignInRecord.getSignInDays());
        signInVo.setLastSignInDates(userSignInRecord.getLastSignInDates());
        userLuckDrawNumberService.increaseLuckDrawNum(userId, activityId, today);
        if (userSignInRecord.getSignInDays().equals(7) || userSignInRecord.getSignInDays().equals(14)
                || userSignInRecord.getSignInDays().equals(21) || userSignInRecord.getSignInDays().equals(28)) {
            // 额外奖励
            userLuckDrawNumberService.increaseLuckDrawNum(userId, activityId, today);
        }
        return signInVo;
    }
}
