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
            userSignInRecord.setContinuousSignInDays(1);
            userSignInRecord.setSignInDays(1);
            userSignInRecord.setCreatedTime(today);
            userSignInRecord.setUpdatedTime(today);
            userSignInRecord.setVersion(1);
            userSignInRecord.setSignInDates(sdf.format(today));
            userSignInRecordService.add(userSignInRecord);
        } else {
            // 存在签到记录，在原来的签到记录上添加新的签到日期
            String signDates = userSignInRecord.getSignInDates();
            String[] signDatesStr = signDates.split(",");
            String lastSignDate = signDatesStr[signDatesStr.length - 1];
            if (sdf.format(today).equals(lastSignDate)) {
                throw new BadRequestException(ErrorCode.REPEAT_SIGN_IN);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DATE, -1);
            Date previousDay = calendar.getTime();
            // 如果断签或签到满28天重置签到次数
            if (userSignInRecord.getContinuousSignInDays() >= 28 || !sdf.format(previousDay).equals(lastSignDate)) {
                userSignInRecord.setSignInDates(sdf.format(today));
                userSignInRecord.setSignInDays(1);
                userSignInRecord.setContinuousSignInDays(1);
            } else {
                userSignInRecord.setContinuousSignInDays(userSignInRecord.getContinuousSignInDays() + 1);
                userSignInRecord.setSignInDays(userSignInRecord.getSignInDays() + 1);
                userSignInRecord.setSignInDates(userSignInRecord.getSignInDates() + "," + sdf.format(today));
            }
            userSignInRecord.setUpdatedTime(today);
            userSignInRecordService.updateByActivityIdAndUserId(userSignInRecord);
        }
        SignInVo signInVo = new SignInVo();
        signInVo.setActivityId(activityId);
        signInVo.setUserId(userId);
        signInVo.setSignInDays(userSignInRecord.getSignInDays());
        signInVo.setContinuousSignInDays(userSignInRecord.getContinuousSignInDays());
        signInVo.setSignInDates(userSignInRecord.getSignInDates());
        userLuckDrawNumberService.increaseLuckDrawNum(userId, activityId, today);
        if (userSignInRecord.getContinuousSignInDays().equals(7) || userSignInRecord.getContinuousSignInDays().equals(14)
                || userSignInRecord.getContinuousSignInDays().equals(21) || userSignInRecord.getContinuousSignInDays().equals(28)) {
            // 额外奖励
            userLuckDrawNumberService.increaseLuckDrawNum(userId, activityId, today);
        }
        return signInVo;
    }
}
