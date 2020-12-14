package com.demo.service;

import com.demo.dao.ActivityDao;
import com.demo.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Cacheable(value = "users", key = "#result.id+'_'+#code", unless = "#result eq null")
    public Activity selectByCode(String code) {
        return activityDao.selectByCode(code);
    }
}
