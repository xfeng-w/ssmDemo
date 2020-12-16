package com.demo.service;

import com.demo.dao.UserSignInRecordDao;
import com.demo.entity.UserSignInRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class UserSignInRecordService {

    @Autowired
    private UserSignInRecordDao userSignInRecordDao;

    @Caching(put = {@CachePut(value = "UserSignInRecords", key = "#result.activityId + '_' + #result.userId", condition = "#result != null")})
    public UserSignInRecord selectByActivityIdAndUserId(Long activityId, Long userId) {
        return userSignInRecordDao.selectByActivityIdAndUserId(activityId, userId);
    }

    @Cacheable(value = "UserSignInRecords", key = "#result.activityId + '_' + #result.userId", condition = "#result != null")
    public UserSignInRecord add(UserSignInRecord userSignInRecord) {
        userSignInRecordDao.add(userSignInRecord);
        return userSignInRecord;
    }

    @Cacheable(value = "UserSignInRecords", key = "#result.activityId + '_' + #result.userId", condition = "#result != null")
    public UserSignInRecord updateByActivityIdAndUserId(UserSignInRecord userSignInRecord) {
        userSignInRecordDao.updateByActivityIdAndUserId(userSignInRecord);
        return userSignInRecord;
    }
}
