package com.huak.auth.dao;

import com.huak.auth.model.Role;
import com.huak.model.Role;

public interface RoleDao {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}