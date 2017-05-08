package com.huak.org.dao;

import com.huak.org.model.Unit;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitDao {
    int deleteByPrimaryKey(Long id);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);
}