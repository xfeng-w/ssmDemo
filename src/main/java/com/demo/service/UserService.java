package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.util.Md5SaltUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public int testAdd(User user) {
        return userDao.addUser(user);
    }

    @Cacheable(value = "users", key = "#result.id", unless = "#result eq null")
    public User addUser(User user) {
        userDao.addUser(user);
        return user;
    }

    @Cacheable(value = "users", key = "#result.id", unless = "#result eq null")
    public User selectByAccountOrPhone(String loginName) {
        return userDao.selectByAccountOrPhone(loginName);
    }

    @Cacheable(value = "users", key = "#result.id", unless = "#result eq null")
    public User selectByAccountAndPhone(String account, String phone) {
        return userDao.selectByAccountAndPhone(account, phone);
    }

    @Cacheable(value = "users", key = "#result.id", unless = "#result eq null")
    public User edit(User user) {
        userDao.edit(user);
        return user;
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
