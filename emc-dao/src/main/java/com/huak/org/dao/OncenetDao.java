package com.huak.org.dao;

import com.huak.org.model.Oncenet;

import java.util.List;
import java.util.Map;

public interface OncenetDao {
    int deleteByPrimaryKey(Long id);

    int insert(Oncenet record);

    int insertSelective(Oncenet record);

    Oncenet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Oncenet record);

    int updateByPrimaryKey(Oncenet record);

    List<Oncenet> selectPageByMap(Map<String,Object> paramsMap);
}