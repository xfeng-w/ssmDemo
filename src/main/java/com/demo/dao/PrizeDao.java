package com.demo.dao;

import com.demo.entity.Prize;

import java.util.List;

public interface PrizeDao {

    Prize selectById(Long id);

    List<Prize> selectByActivityId(Long activityId);

    int add(Prize prize);

    int updateById(Prize prize);
}
