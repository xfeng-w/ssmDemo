package com.demo.controller;

import com.demo.dto.loginResponse;
import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public String login(String loginName, String password) {
        loginResponse response = userService.login(loginName, password);
        if (response.getSuccess()) {
            return "loginSuccess";
        }
        return "loginError";
    }

    @PostMapping("register")
    public String register(User user) {
        loginResponse response = userService.register(user);
        return "loginSuccess";
    }
}
