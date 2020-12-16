package com.demo.service;

import com.demo.dao.UserLuckDrawNumberDao;
import com.demo.entity.UserLuckDrawNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 用户抽奖次数
 */
@Service
public class UserLuckDrawNumberService {

    @Autowired
    private UserLuckDrawNumberDao userLuckDrawNumberDao;

    @Cacheable(value = "UserLuckDrawNumber", key = "#result.userId + '_' + #result.activityId + '_' + #result.luckDrawDate", condition = "#result != null")
    public UserLuckDrawNumber selectByUserIdAndActivityId(Long userId, Long activityId, Date luckDrawDate) {
        return userLuckDrawNumberDao.selectByUserIdAndActivityId(userId, activityId, luckDrawDate);
    }

    @Cacheable(value = "UserLuckDrawNumber", key = "#result.userId + '_' + #result.activityId + '_' + #result.luckDrawDate", condition = "#result != null")
    public UserLuckDrawNumber toReduceLuckDrawNum(Long userId, Long activityId, Date luckDrawDate) {
        UserLuckDrawNumber userLuckDrawNumber = selectByUserIdAndActivityId(userId, activityId, luckDrawDate);
        userLuckDrawNumber.setUpdatedTime(new Date());
        userLuckDrawNumber.setLuckDrawNumber(userLuckDrawNumber.getLuckDrawNumber() - 1);
        userLuckDrawNumberDao.updateByUserIdAndActivityId(userLuckDrawNumber);
        return userLuckDrawNumber;
    }

    @Cacheable(value = "UserLuckDrawNumber", key = "#result.userId + '_' + #result.activityId + '_' + #result.luckDrawDate", condition = "#result != null")
    @Transactional
    public UserLuckDrawNumber increaseLuckDrawNum(Long userId, Long activityId, Date luckDrawDate) {
        UserLuckDrawNumber userLuckDrawNumber = selectByUserIdAndActivityId(userId, activityId, luckDrawDate);
        Date date = new Date();
        if (Objects.isNull(userLuckDrawNumber)) {
            userLuckDrawNumber = new UserLuckDrawNumber();
            userLuckDrawNumber.setLuckDrawNumber(1);
            userLuckDrawNumber.setCreatedTime(date);
            userLuckDrawNumber.setUpdatedTime(date);
            userLuckDrawNumber.setUserId(userId);
            userLuckDrawNumber.setActivityId(activityId);
            userLuckDrawNumber.setLuckDrawDate(date);
            userLuckDrawNumber.setVersion(1);
            userLuckDrawNumberDao.add(userLuckDrawNumber);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            if (sdf.format(date).equals(sdf.format(userLuckDrawNumber.getLuckDrawDate()))) {
                userLuckDrawNumber.setLuckDrawNumber(userLuckDrawNumber.getLuckDrawNumber() + 1);
            } else {
                userLuckDrawNumber.setLuckDrawDate(date);
                userLuckDrawNumber.setLuckDrawNumber(1);
            }
            userLuckDrawNumber.setUpdatedTime(new Date());
            userLuckDrawNumberDao.updateByUserIdAndActivityId(userLuckDrawNumber);
        }
        return userLuckDrawNumber;
    }
}
