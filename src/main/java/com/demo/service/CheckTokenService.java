package com.demo.service;

import com.demo.entity.User;
import com.demo.exception.BadRequestException;
import com.demo.exception.ErrorCode;
import com.demo.util.Md5SaltUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CheckTokenService {

    @Autowired
    private UserService userService;

    public void checkToken(Long userId, String token) {
        User user = userService.selectUserById(userId);
        if (Objects.isNull(user)) {
            throw new BadRequestException(ErrorCode.USER_NOT_EXIST);
        }
        if (!Md5SaltUtils.md5Hex(token).equals(user.getToken())) {
            throw new BadRequestException(ErrorCode.USER_LOGON_FAILURE);
        }
    }
}
