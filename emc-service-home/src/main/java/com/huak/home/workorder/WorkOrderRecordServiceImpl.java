package com.huak.home.workorder;

import com.huak.base.dao.DateDao;
import com.huak.common.UUIDGenerator;
import com.huak.workorder.dao.WorkOrderInfoDao;
import com.huak.workorder.dao.WorkOrderRecordDao;
import com.huak.workorder.dao.WorkOrderResetDao;
import com.huak.workorder.model.WorkOrderInfo;
import com.huak.workorder.model.WorkOrderRecord;
import com.huak.workorder.type.WorkOrderStatus;
import com.huak.workorder.vo.WorkOrderRecordA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/10/25<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class WorkOrderRecordServiceImpl implements WorkOrderRecordService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WorkOrderInfoDao workOrderInfoDao;
    @Resource
    private WorkOrderRecordDao workOrderRecordDao;
    @Resource
    private WorkOrderResetDao workOrderResetDao;
    @Resource
    private DateDao dateDao;
    @Override
    public void insertWorkOrderRecord(WorkOrderInfo workOrder) {
        logger.info("保存工单");


        //workOrderRecordDao.insertSelective(record);
    }
    @Override
    public WorkOrderRecordA selectAllRecord(String code) {

        logger.info("查询记录表中所有的记录");
        return workOrderRecordDao.selectAllRecord(code);
    }

    @Override
    public int sendABorC(WorkOrderInfo workOrder) {
        logger.info("班长发送工单到接单员");
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.B214.getKey());
        record.setOperateTime(dateDao.getTime());
        record.setOpertor(workOrder.getMonitor());
        record.setSendee(workOrder.getTakor());
        record.setAfterStatus(WorkOrderStatus.C311.getKey());
        record.setDes("班长发送工单到接单员");
        //更新当前工单状态
        workOrder.setStatus(WorkOrderStatus.C311.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        return workOrderRecordDao.insertSelective(record);
    }
    @Override
    public int backA(WorkOrderInfo workOrder) {
        logger.info("接单员退回工单到派单员");
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C311.getKey());
        record.setOperateTime(dateDao.getTime());
        record.setOpertor(workOrder.getMonitor());
        record.setSendee(workOrder.getTakor());
        record.setAfterStatus(WorkOrderStatus.C312.getKey());
        record.setDes("接单员退回工单到派单员");
        //更新当前工单状态 包括退回原因
        workOrder.setStatus(WorkOrderStatus.C312.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        return workOrderRecordDao.insertSelective(record);
    }

    @Override
    public int finishC(WorkOrderInfo workOrder) {
        logger.info("接单员端确认并且完成");
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C311.getKey());
        record.setOperateTime(dateDao.getTime());
        record.setOpertor(workOrder.getMonitor());
        record.setSendee(workOrder.getTakor());
        record.setAfterStatus(WorkOrderStatus.C311.getKey());
        record.setDes("接单员端确认并且完成");
        //更新当前工单状态
        workOrder.setStatus(WorkOrderStatus.C311.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        return workOrderRecordDao.insertSelective(record);
    }

    @Override
    public int confirmAC(WorkOrderInfo workOrder) {
        logger.info("接端确认并且完成等待派单员确认完成且派单员确认完成");
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.C311.getKey());
        record.setOperateTime(dateDao.getTime());
        record.setOpertor(workOrder.getMonitor());
        record.setSendee(workOrder.getTakor());
        record.setAfterStatus(WorkOrderStatus.A121.getKey());
        record.setDes("接端确认并且完成等待派单员确认完成且派单员确认完成");
        //更新当前工单状态
        workOrder.setStatus(WorkOrderStatus.A121.getKey());
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        return workOrderRecordDao.insertSelective(record);
    }
    @Override
    public int resetBackABC(WorkOrderInfo workOrder) {
            logger.info("派单员关闭退单，且派单员重新发送到班长");
            WorkOrderRecord record = new WorkOrderRecord();
            record.setId(UUIDGenerator.getUUID());
            record.setCode(workOrder.getCode());//重新发送，工单号为新工单号，与旧工单号无关(状态有关)
            record.setBeforStatus(WorkOrderStatus.C312.getKey());
            record.setOperateTime(dateDao.getTime());
            record.setOpertor(workOrder.getMonitor());
            record.setSendee(workOrder.getTakor());
            record.setAfterStatus(WorkOrderStatus.A131.getKey());
            record.setDes("派单员关闭退单，且派单员重新发送到班长");
            //更新当前工单状态  工单号为新工单号 (状态有关，延续之前状态)
            workOrder.setStatus(WorkOrderStatus.A131.getKey());
            workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        return workOrderRecordDao.insertSelective(record);
    }


    @Override
    public int finishB(WorkOrderInfo workOrder) {
        return 0;
    }

    @Override
    public int confirmAB(WorkOrderInfo workOrder) {
        return 0;
    }

    @Override
    public int resetFinishAB(WorkOrderInfo workOrder) {
        return 0;
    }

    @Override
    public int closeAB(WorkOrderInfo workOrder) {
        return 0;
    }

    @Override
    public int resetBackAB(WorkOrderInfo workOrder) {
        return 0;
    }

    @Override
    public int sendAC(WorkOrderInfo workOrder) {
        return 0;
    }

    @Override
    public int saveAndSendAC(WorkOrderInfo workOrder) {
        return 0;
    }

    @Override
    public int takingC(WorkOrderInfo workOrder) {
        return 0;
    }

    @Override
    public int backC(WorkOrderInfo workOrder) {
        return 0;
    }
    @Override
    public int resetFinishAC(WorkOrderInfo workOrder) {
        return 0;
    }

    @Override
    public int closeAC(WorkOrderInfo workOrder) {
        return 0;
    }

}
