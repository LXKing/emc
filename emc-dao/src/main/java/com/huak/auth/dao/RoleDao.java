package com.huak.auth.dao;

import com.huak.auth.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    List<Role> selectPageByMap(Map<String,Object> paramsMap);

    List<Map<String,Object>> exportRoles(Map<String, Object> paramsMap);
}