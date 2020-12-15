package com.demo.service;

import com.demo.dao.UserLuckDrawNumberDao;
import com.demo.entity.UserLuckDrawNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserLuckDrawNumberService {

    @Autowired
    private UserLuckDrawNumberDao userLuckDrawNumberDao;


    public UserLuckDrawNumber selectByUserIdAndActivityId(Long userId, Long activityId, Date luckDrawDate) {
        return userLuckDrawNumberDao.selectByUserIdAndActivityId(userId, activityId, luckDrawDate);
    }
}
