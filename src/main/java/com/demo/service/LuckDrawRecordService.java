package com.demo.service;

import com.demo.dao.LuckDrawRecordDao;
import com.demo.entity.LuckDrawRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuckDrawRecordService {

    @Autowired
    private LuckDrawRecordDao luckDrawRecordDao;

    @Caching(put = {@CachePut(value = "LuckDrawRecords", key = "#result.id + '_' + #result.userId", condition = "#result != null")})
    public LuckDrawRecord add(LuckDrawRecord luckDrawRecord) {
        luckDrawRecordDao.add(luckDrawRecord);
        return luckDrawRecord;
    }

    @Cacheable(value = "LuckDrawRecords", key = "#result.id + '_' + #result.userId", unless = "#result eq null || #result.size() < 1")
    public List<LuckDrawRecord> listByUserId(Long userId,Long activityId) {
        return luckDrawRecordDao.listByUserId(userId,activityId);
    }

    @Cacheable(value = "LuckDrawRecords", key = "#result.id + '_' + #result.userId", unless = "#result eq null || #result.size() < 1")
    public List<LuckDrawRecord> listByPrizeTypeAndActivityId(Integer prizeType, Long activityId) {
        return luckDrawRecordDao.listByPrizeTypeAndActivityId(prizeType, activityId);
    }

}
