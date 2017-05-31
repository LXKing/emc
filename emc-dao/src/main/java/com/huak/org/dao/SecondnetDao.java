package com.huak.org.dao;

import com.huak.org.model.vo.Secondnet;

import java.util.List;
import java.util.Map;

public interface SecondnetDao {

    int deleteByPrimaryKey(Long id);

    int insert(Secondnet record);

    int insertSelective(Secondnet record);

    Secondnet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Secondnet record);

    int updateByPrimaryKey(Secondnet record);

    List<Secondnet> selectPageByMap(Map<String, Object> paramsMap);
}