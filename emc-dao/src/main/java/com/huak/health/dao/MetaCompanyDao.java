package com.huak.health.dao;

import com.huak.health.model.MetaCompany;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaCompanyDao {
    int deleteByPrimaryKey(String tag);

    int insert(MetaCompany record);

    int insertSelective(MetaCompany record);

    MetaCompany selectByPrimaryKey(String tag);

    int updateByPrimaryKeySelective(MetaCompany record);

    int updateByPrimaryKey(MetaCompany record);
}