package com.huak.mdc.dao;


import com.huak.mdc.model.RecordPrestore;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordPrestoreDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(RecordPrestore record);

    RecordPrestore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RecordPrestore record);

}