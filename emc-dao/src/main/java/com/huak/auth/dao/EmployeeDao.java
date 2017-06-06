package com.huak.auth.dao;


import com.huak.auth.model.Employee;

import java.util.*;


public interface EmployeeDao {
    /**
     * 分页查询员工列表
     * @param paramsMap
     * @return
     */
    List<Employee> selectPageByMap(Map<String, Object> paramsMap);

    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    int delete(String[] ids);
}