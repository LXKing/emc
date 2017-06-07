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

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(Employee record);

    /**
     * 新增
     * @param record
     * @return
     */
    int insertSelective(Employee record);

    /**
     * 根据id选择实体
     * @param id
     * @return
     */
    Employee selectByPrimaryKey(Long id);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Employee record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Employee record);

    /**
     * 根据id数组批量删除
     * @param ids
     * @return
     */
    int delete(String[] ids);

    /**
     * 删除数据，修改状态，软删除
     * @param ids
     * @return
     */
    int deleteByStatus(String ids);

    /**
     * 导出
     * @param paramsMap
     * @return
     */
    List<Map<String,Object>> export(Map<String, Object> paramsMap);
}