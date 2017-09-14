package com.huak.health.dao;


import com.huak.health.model.IndexRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IndexRecordDao {
    int deleteByPrimaryKey(String id);

    int insert(IndexRecord record);

    int insertSelective(IndexRecord record);

    IndexRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(IndexRecord record);

    int updateByPrimaryKey(IndexRecord record);

    List<IndexRecord> selectPageByMap(Map<String, Object> paramsMap);
}