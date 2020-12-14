package com.demo.service;

import com.demo.constant.LoginMessage;
import com.demo.dto.loginResponse;
import com.demo.entity.User;
import com.demo.dao.UserDao;
import com.demo.util.Md5SaltUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public int testAdd(User user) {
        return userDao.addUser(user);
    }

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @return
     */
    public loginResponse login(String loginName, String password) {
        loginResponse response = new loginResponse();
        response.setSuccess(true);
        User user = userDao.selectByAccountOrPhone(loginName);
        if (Objects.isNull(user)) {
            response.setSuccess(false);
            response.setMessage(LoginMessage.ACCOUNT_NOT_EXIST);
            logger.error(LoginMessage.ACCOUNT_NOT_EXIST);
            return response;
        }
        if (!Md5SaltUtils.verify(password, user.getPassword())) {
            response.setSuccess(false);
            response.setMessage(LoginMessage.ACCOUNT_OR_PASSWORD_ERROR);
            logger.error(LoginMessage.ACCOUNT_OR_PASSWORD_ERROR);
            return response;
        }
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
        response.setSuccess(true);
        if (Objects.isNull(user.getAccount()) || Objects.isNull(user.getPassword()) || Objects.isNull(user.getPhone())) {
            response.setSuccess(false);
            response.setMessage(LoginMessage.REQUIRED_FIELDS_NOT_BE_EMPTY);
            logger.error(LoginMessage.REQUIRED_FIELDS_NOT_BE_EMPTY);
            return response;
        }
        User existEntity = userDao.selectByAccountAndPhone(user.getAccount(), user.getPhone());
        if (Objects.nonNull(existEntity)) {
            response.setSuccess(false);
            response.setMessage(LoginMessage.ACCOUNT_OR_PHONE_EXIST);
            logger.error(LoginMessage.ACCOUNT_OR_PHONE_EXIST);
            return response;
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
        userDao.addUser(saveEntity);
        return response;
    }

    /**
     * 根据用户id查询用户
     *
     * @param userId
     * @return
     */
    @Cacheable(value = "users", key = "#userId", unless = "#result eq null")
    public User selectUserById(Long userId) {
        return userDao.selectById(userId);
    }

    public static void main(String[] args) {
        String salt = Md5SaltUtils.getSalt(16);
        System.out.println(salt);
        System.out.println(Md5SaltUtils.generateBySalt("123456", salt));
    }
}
