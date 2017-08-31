package com.huak.mdc.dao;

import com.huak.mdc.model.Prestore;
import com.huak.mdc.vo.PrestoreA;

import java.util.List;
import java.util.Map;

public interface PrestoreDao {
    int deleteByPrimaryKey(String id);

    int insert(Prestore record);

    int insertSelective(Prestore record);

    Prestore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Prestore record);

    int updateByPrimaryKey(Prestore record);

    List<PrestoreA> selectPageByMap(Map<String,Object> paramsMap);

}