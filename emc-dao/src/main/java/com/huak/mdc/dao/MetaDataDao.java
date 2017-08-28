package com.huak.mdc.dao;


import com.huak.mdc.model.MetaData;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaDataDao {
    int deleteByPrimaryKey(String tag);

    int insertSelective(MetaData record);

    MetaData selectByPrimaryKey(String tag);

    int updateByPrimaryKeySelective(MetaData record);

}