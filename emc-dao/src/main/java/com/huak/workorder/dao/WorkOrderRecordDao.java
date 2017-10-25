package com.huak.workorder.dao;


import com.huak.workorder.model.WorkOrderRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRecordDao {
    int deleteByPrimaryKey(String id);

    int insert(WorkOrderRecord record);

    int insertSelective(WorkOrderRecord record);

    WorkOrderRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkOrderRecord record);

    int updateByPrimaryKey(WorkOrderRecord record);
}