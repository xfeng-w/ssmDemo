package com.demo.dao;

import com.demo.entity.Activity;

public interface ActivityDao {

    Activity selectById(Long id);

    Activity selectByCode(String code);
}
