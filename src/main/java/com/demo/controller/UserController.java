package com.demo.controller;

import com.demo.dto.loginResponse;
import com.demo.entity.User;
import com.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public loginResponse login(String loginName, String password) {
        return loginService.login(loginName, password);
    }

    @PostMapping("register")
    public loginResponse register(User user) {
        return loginService.register(user);
    }
}
