package com.huak.mdc.dao;


import com.huak.mdc.model.RecordChange;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordChangeDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(RecordChange record);

    RecordChange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RecordChange record);

}