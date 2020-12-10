package com.demo.service;

import com.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LuckDrawService {

    @Autowired
    private UserService userService;

    public void lottery(Long userId, Long activityId) {
        User user = userService.selectUserById(userId);

    }
}
