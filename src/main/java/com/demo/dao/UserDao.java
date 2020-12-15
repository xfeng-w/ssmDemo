package com.demo.dao;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    User selectById(Long userId);

    int addUser(User user);

    int edit(User user);

    User selectByAccountOrPhone(String loginName);

    User selectByAccountAndPhone(@Param("account") String account, @Param("phone") String phone);

}
