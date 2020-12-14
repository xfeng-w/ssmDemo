package com.demo.dao;

import com.demo.entity.Prize;

import java.util.List;

public interface PrizeDao {

    List<Prize> selectByActivityId(Long activityId);
}
