package com.huak.auth.dao;

import com.huak.auth.model.Func;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FuncDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(Func record);

    Func selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Func record);

    List<Func> selectAllByMap(Map<String,Object> paramsMap);
}