package com.huak.org.dao;

import com.huak.org.model.Administrative;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativeDao {
    int deleteByPrimaryKey(String admCode);

    int insert(Administrative record);

    int insertSelective(Administrative record);

    Administrative selectByPrimaryKey(String admCode);

    int updateByPrimaryKeySelective(Administrative record);

    int updateByPrimaryKey(Administrative record);
}