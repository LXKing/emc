package com.huak.workorder.dao;

import com.huak.workorder.model.WorkOrderInfo;
import com.huak.workorder.vo.WorkOrderInfoDetail;
import com.huak.workorder.vo.WorkOrderInfoRel;
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

    WorkOrderInfoDetail getWorkInfoByCode(String code);

    List<WorkOrderInfoRel> selectWorkRelByCode(String code);

    List<Map<String,Object>> getEmployee(Map<String,Object> map);

    List<Map<String,Object>> getEmployeeAndRole(Map<String,Object> map);

    List<Map<String,Object>> getEmployeeById(Map<String,Object> map);

    List<WorkOrderInfo> selectWorkOrderInfoByCreator(Map<String, Object> params);

    List<WorkOrderInfo> selectWorkOrderInfoByMonitor(Map<String, Object> params);

    List<WorkOrderInfo> selectWorkOrderInfoByTakor(Map<String, Object> params);
}