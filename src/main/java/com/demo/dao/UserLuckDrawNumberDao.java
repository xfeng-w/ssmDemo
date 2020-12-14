package com.demo.dao;

import com.demo.entity.UserLuckDrawNumber;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserLuckDrawNumberDao {

    UserLuckDrawNumber selectByUserId(@Param("userId") Long userId, @Param("luckDrawDate") Date luckDrawDate);
}
