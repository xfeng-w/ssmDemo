package com.demo.dao;

import com.demo.entity.LuckDrawRecord;
import org.apache.ibatis.annotations.Param;

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
    List<LuckDrawRecord> listByUserId(@Param("userId") Long userId, @Param("activityId") Long activityId);

    /**
     * 根据奖品类型查询抽奖记录
     *
     * @param prizeType
     * @param activityId
     * @return
     */
    List<LuckDrawRecord> listByPrizeTypeAndActivityId(@Param("prizeType") Integer prizeType, @Param("activityId") Long activityId);
}
