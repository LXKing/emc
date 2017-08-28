package com.huak.mdc.dao;

import com.huak.mdc.model.MeterCollect;


import java.util.List;
import java.util.Map;

public interface MeterCollectDao {
    int deleteByPrimaryKey(String id);

    int insert(MeterCollect record);

    int insertSelective(MeterCollect record);

    MeterCollect selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeterCollect record);

    int updateByPrimaryKey(MeterCollect record);

    List<MeterCollect> selectPageByMap(Map<String,Object> paramsMap);

}