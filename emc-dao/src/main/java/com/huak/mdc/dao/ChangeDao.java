package com.huak.mdc.dao;

import com.huak.mdc.model.Change;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeDao {
    int deleteByPrimaryKey(String id);

    int insert(Change record);

    int insertSelective(Change record);

    Change selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Change record);

    int updateByPrimaryKey(Change record);
}