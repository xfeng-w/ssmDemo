package com.demo.service;

import com.demo.dao.PrizeDao;
import com.demo.entity.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizeService {

    @Autowired
    private PrizeDao prizeDao;

    public List<Prize> selectByActivityId(Long activityId) {
        return prizeDao.selectByActivityId(activityId);
    }

    public Prize selectById(Long id) {
        return prizeDao.selectById(id);
    }

    public Prize updateById(Prize prize) {
        prizeDao.updateById(prize);
        return prize;
    }

    public Prize add(Prize prize) {
        prizeDao.add(prize);
        return prize;
    }
}
