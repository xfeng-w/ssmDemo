package com.demo.dao;

import com.demo.entity.LuckDrawRecord;

import java.util.List;

public interface LuckDrawRecordDao {

    /**
     * 插入抽奖记录
     *
     * @param luckDrawRecord
     * @return
     */
    int add(LuckDrawRecord luckDrawRecord);

    /**
     * 根据用户查询抽奖记录
     *
     * @param userId 用户id
     * @return
     */
    List<LuckDrawRecord> listByUserId(Long userId);
}
