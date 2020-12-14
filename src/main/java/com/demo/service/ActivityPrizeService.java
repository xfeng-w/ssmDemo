package com.demo.service;

import com.demo.dao.ActivityPrizeDao;
import com.demo.entity.ActivityPrize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityPrizeService {

    @Autowired
    private ActivityPrizeDao activityPrizeDao;

    @Cacheable(value = "ActivityPrizes", key = "#result.id+'_'+#activityId", unless = "#result eq null")
    public List<ActivityPrize> selectByActivityId(Long activityId){
        return activityPrizeDao.selectByActivityId(activityId);
    }
}
