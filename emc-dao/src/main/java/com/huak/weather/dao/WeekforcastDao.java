package com.huak.weather.dao;


import com.huak.weather.model.Weekforcast;

import java.util.List;
import java.util.Map;

public interface WeekforcastDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Weekforcast record);

    int insertSelective(Weekforcast record);

    Weekforcast selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weekforcast record);

    int updateByPrimaryKey(Weekforcast record);

    void deletebyParmas(Map<String, Object> params);

    void insertWeekForcastByArray(List<Weekforcast> weeforca);
}