package com.huak.health.dao;

import com.huak.health.model.AlarmConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmConfigDao {
    int deleteByPrimaryKey(String id);

    int insert(AlarmConfig record);

    int insertSelective(AlarmConfig record);

    AlarmConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AlarmConfig record);

    int updateByPrimaryKey(AlarmConfig record);
}