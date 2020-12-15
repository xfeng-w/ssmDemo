package com.demo.service;

import com.demo.dto.loginResponse;
import com.demo.entity.User;
import com.demo.exception.BadRequestException;
import com.demo.exception.ErrorCode;
import com.demo.util.Md5SaltUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class LoginService {

    private static final String randomChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @return
     */
    public loginResponse login(String loginName, String password) {
        loginResponse response = new loginResponse();
        User user = userService.selectByAccountOrPhone(loginName);
        if (Objects.isNull(user)) {
            throw new BadRequestException(ErrorCode.USER_NOT_EXIST);
        }
        if (!Md5SaltUtils.verify(password, user.getPassword())) {
            throw new BadRequestException(ErrorCode.USER_PASSWORD_ERROR);
        }
        String token = RandomStringUtils.random(16,randomChar);
        user.setToken(Md5SaltUtils.md5Hex(token));
        userService.edit(user);
        response.setUserId(user.getId());
        response.setToken(token);
        return response;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    public loginResponse register(User user) {
        loginResponse response = new loginResponse();
        if (Objects.isNull(user.getAccount()) || Objects.isNull(user.getPassword()) || Objects.isNull(user.getPhone())) {
            throw new BadRequestException(ErrorCode.REQUIRED_FIELDS_NOT_BE_EMPTY);
        }
        User existEntity = userService.selectByAccountAndPhone(user.getAccount(), user.getPhone());
        if (Objects.nonNull(existEntity)) {
            throw new BadRequestException(ErrorCode.ACCOUNT_OR_PHONE_EXIST);
        }
        User saveEntity = new User();
        saveEntity.setAccount(user.getAccount());
        saveEntity.setPhone(user.getPhone());
        String salt = Md5SaltUtils.getSalt(16);
        saveEntity.setSalt(salt);
        String password = Md5SaltUtils.generateBySalt(user.getPassword(), salt);
        saveEntity.setPassword(password);
        Date date = new Date();
        saveEntity.setCreatedTime(date);
        saveEntity.setUpdatedTime(date);
        saveEntity.setVersion(1);
        userService.addUser(saveEntity);
        response.setUserId(saveEntity.getId());
        return response;
    }

}
