package com.demo.dao;

import com.demo.entity.UserLuckDrawNumber;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserLuckDrawNumberDao {

    UserLuckDrawNumber selectByUserIdAndActivityId(@Param("userId") Long userId, @Param("activityId") Long activityId, @Param("luckDrawDate") Date luckDrawDate);
}
