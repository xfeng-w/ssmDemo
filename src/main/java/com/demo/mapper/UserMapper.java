package com.demo.mapper;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectById(Long userId);

    int addUser(User user);

    User selectByAccountOrPhone(String loginName);

    User selectByAccountAndPhone(@Param("account") String account, @Param("phone") String phone);

}
