package com.huak.task.dao;

import com.huak.task.model.Temperature;

import java.util.List;
import java.util.Map;

public interface TemperatureDao {
    int deleteByPrimaryKey(String id);

    int insert(Temperature record);

    int insertSelective(Temperature record);

    Temperature selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Temperature record);

    int updateByPrimaryKey(Temperature record);

    List<Temperature> selectAllByMap(Map<String,Object> map);
}