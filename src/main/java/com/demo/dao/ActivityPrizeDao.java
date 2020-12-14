package com.demo.dao;

import com.demo.entity.ActivityPrize;

import java.util.List;

public interface ActivityPrizeDao {

    List<ActivityPrize> selectActivityPrizeByActivityId(Long activityId);
}
