package com.huak.home.workorder;

import com.huak.base.dao.DateDao;
import com.huak.common.UUIDGenerator;
import com.huak.workorder.dao.WorkOrderInfoDao;
import com.huak.workorder.dao.WorkOrderRecordDao;
import com.huak.workorder.dao.WorkOrderResetDao;
import com.huak.workorder.model.WorkOrderInfo;
import com.huak.workorder.model.WorkOrderRecord;
import com.huak.workorder.type.WorkOrderOperate;
import com.huak.workorder.type.WorkOrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.workorder<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-10-25<BR>
 * Description:  工单   <BR>
 * Function List:  <BR>
 */
@Service
public class WorkOrderInfoServiceImpl implements WorkOrderInfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WorkOrderInfoDao workOrderInfoDao;
    @Resource
    private WorkOrderRecordDao workOrderRecordDao;
    @Resource
    private WorkOrderResetDao workOrderResetDao;
    @Resource
    private DateDao dateDao;
    /**
     * 保存工单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void saveA(WorkOrderInfo workOrder) {
        logger.info("保存工单");
        String dateTime = dateDao.getTime();
        //todo 生成code
        String code = "";
        //封装工单
        workOrder.setId(UUIDGenerator.getUUID());
        workOrder.setCode(code);
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        workOrderInfoDao.insertSelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b/a-b-c
     * a派送工单b
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void sendABorC(WorkOrderInfo workOrder) {
        logger.info("派单员派送工单给班长");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b/a-b-c
     * a保存工单并派送工单b
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void saveAndSendABorC(WorkOrderInfo workOrder) {
        logger.info("派单员保存工单并派送工单给班长");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);

    }

    /**
     * a-b
     * b接单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void takingB(WorkOrderInfo workOrder) {
        logger.info("班长接单");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b退单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void backB(WorkOrderInfo workOrder) {
        logger.info("班长退单");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b完成
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void finishB(WorkOrderInfo workOrder) {
        logger.info("班长完成");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b完成a确认
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void confirmAB(WorkOrderInfo workOrder) {
        logger.info("班长完成派单员确认");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b完成a重新派送
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void resetFinishAB(WorkOrderInfo workOrder) {
        logger.info("班长完成派单员重新派送");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b退单a关闭
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void closeAB(WorkOrderInfo workOrder) {
        logger.info("班长退单派单员关闭");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-b
     * b退单a重新派送
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void resetBackAB(WorkOrderInfo workOrder) {
        logger.info("班长退单派单员重新派送");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * a派送工单c
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void sendAC(WorkOrderInfo workOrder) {
        logger.info("派单员派送工单接单员");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * a保存工单并派送工单c
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void saveAndSendAC(WorkOrderInfo workOrder) {
        logger.info("派单员保存工单并派送工单接单员");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c接单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void takingC(WorkOrderInfo workOrder) {
        logger.info("接单员接单");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c退单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void backC(WorkOrderInfo workOrder) {
        logger.info("接单员退单");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c完成
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void finishC(WorkOrderInfo workOrder) {
        logger.info("接单员完成");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c完成a确认
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void confirmAC(WorkOrderInfo workOrder) {
        logger.info("接单员完成派单员确认");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c完成a重新派送
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void resetFinishAC(WorkOrderInfo workOrder) {
        logger.info("接单员完成派单员重新派送");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c退单a关闭
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void closeAC(WorkOrderInfo workOrder) {
        logger.info("接单员退单派单员关闭");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }

    /**
     * a-c
     * c退单a重新派送
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void resetBackAC(WorkOrderInfo workOrder) {
        logger.info("接单员退单派单员重新派送");
        String dateTime = dateDao.getTime();

        //封装工单
        workOrder.setStatus(WorkOrderStatus.A111.getKey());
        //派单
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);

        //保存工单操作记录
        WorkOrderRecord record = new WorkOrderRecord();
        record.setId(UUIDGenerator.getUUID());
        record.setCode(workOrder.getCode());
        record.setBeforStatus(WorkOrderStatus.E000.getKey());
        record.setAfterStatus(WorkOrderStatus.A111.getKey());
        record.setOpertor(workOrder.getCreator());
        record.setOperateTime(dateTime);
        record.setSendee(null);
        record.setDes(WorkOrderOperate.A_SAVE.getValue());
        workOrderRecordDao.insertSelective(record);
    }
}
