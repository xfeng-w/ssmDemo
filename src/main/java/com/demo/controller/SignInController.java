package com.demo.controller;

import com.demo.entity.Activity;
import com.demo.exception.BadRequestException;
import com.demo.exception.ErrorCode;
import com.demo.service.ActivityService;
import com.demo.service.CheckTokenService;
import com.demo.service.SignInService;
import com.demo.vo.SignInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("signIn")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CheckTokenService checkTokenService;

    @GetMapping
    public SignInVo signIn(String token, String activityCode, Long userId) {
        checkTokenService.checkToken(userId, token);
        Activity activity = activityService.selectByCode(activityCode);
        if (Objects.isNull(activity)) {
            throw new BadRequestException(ErrorCode.ACTIVITY_NOT_EXIST);
        }
        return signInService.signIn(activity.getId(), userId);
    }
}
