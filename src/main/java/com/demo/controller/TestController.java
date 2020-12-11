package com.demo.controller;

import com.demo.entity.User;
import com.demo.exception.BadRequestException;
import com.demo.exception.ErrorCode;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public int test() {
        User user = new User();
        user.setAccount("123");
        user.setPassword("pwd");
        user.setUpdatedTime(new Date());
        user.setCreatedTime(new Date());
        user.setVersion(1);
        return userService.testAdd(user);
    }

    @ResponseBody
    @GetMapping("exception")
    public void exceptionTest() throws BadRequestException {
        throw new BadRequestException(ErrorCode.INTERNAL_SYSTEM_ANOMALY);
    }
}
