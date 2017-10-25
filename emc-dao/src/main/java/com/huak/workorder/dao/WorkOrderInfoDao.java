package com.huak.workorder.dao;

import com.huak.workorder.model.WorkOrderInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderInfoDao {
    int deleteByPrimaryKey(String id);

    int insert(WorkOrderInfo record);

    int insertSelective(WorkOrderInfo record);

    WorkOrderInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkOrderInfo record);

    int updateByPrimaryKey(WorkOrderInfo record);
}