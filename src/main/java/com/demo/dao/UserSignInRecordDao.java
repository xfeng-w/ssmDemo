package com.demo.dao;

import com.demo.entity.UserSignInRecord;
import org.apache.ibatis.annotations.Param;

public interface UserSignInRecordDao {


    UserSignInRecord selectByActivityIdAndUserId(@Param("activityId") Long activityId, @Param("userId") Long userId);

    int add(UserSignInRecord userSignInRecord);

    int updateByActivityIdAndUserId(UserSignInRecord userSignInRecord);
}
