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

    public UserLuckDrawNumber selectByUserId(Long userId, Date luckDrawDate) {
        return userLuckDrawNumberDao.selectByUserId(userId, luckDrawDate);
    }
}
