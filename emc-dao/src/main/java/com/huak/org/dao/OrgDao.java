package com.huak.org.dao;

import com.huak.org.model.Org;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgDao {
    int deleteByPrimaryKey(String id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);
}