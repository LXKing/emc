package com.huak.workorder.dao;

import com.huak.workorder.model.WorkOrderInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WorkOrderInfoDao {
    int deleteByPrimaryKey(String id);

    int insert(WorkOrderInfo record);

    int insertSelective(WorkOrderInfo record);

    WorkOrderInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkOrderInfo record);

    int updateByPrimaryKey(WorkOrderInfo record);

    int getToDayNum(Map<String, Object> params);

    List<WorkOrderInfo> selectWorkOrderInfo(Map<String, Object> params);
}