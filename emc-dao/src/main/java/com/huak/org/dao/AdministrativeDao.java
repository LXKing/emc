package com.huak.org.dao;

import com.huak.org.model.Administrative;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdministrativeDao {
    int deleteByPrimaryKey(String admCode);

    int insert(Administrative record);

    int insertSelective(Administrative record);

    Administrative selectByPrimaryKey(String admCode);

    int updateByPrimaryKeySelective(Administrative record);

    int updateByPrimaryKey(Administrative record);

    List<Map<String,Object>> findAllByLevel(Map<String, String> paramsMap);

	List<Map<String, String>> selectByMap(Map<String, String> param);
}