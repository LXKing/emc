package com.huak.home.workorder;

import com.huak.workorder.dao.WorkOrderInfoDao;
import com.huak.workorder.dao.WorkOrderRecordDao;
import com.huak.workorder.dao.WorkOrderResetDao;
import com.huak.workorder.model.WorkOrderInfo;
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
    /**
     * 保存工单
     *
     * @param workOrder
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int saveA(WorkOrderInfo workOrder) {
        logger.info("保存工单");
        return 0;
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
    public int sendABorC(WorkOrderInfo workOrder) {
        logger.info("派单员派送工单给班长");
        
        workOrderInfoDao.updateByPrimaryKeySelective(workOrder);
        
        return 0;
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
    public int saveAndSendABorC(WorkOrderInfo workOrder) {
        logger.info("派单员保存工单并派送工单给班长");
        return 0;
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
    public int takingB(WorkOrderInfo workOrder) {
        logger.info("班长接单");
        return 0;
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
    public int backB(WorkOrderInfo workOrder) {
        logger.info("班长退单");
        return 0;
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
    public int finishB(WorkOrderInfo workOrder) {
        logger.info("班长完成");
        return 0;
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
    public int confirmAB(WorkOrderInfo workOrder) {
        logger.info("班长完成派单员确认");
        return 0;
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
    public int resetFinishAB(WorkOrderInfo workOrder) {
        logger.info("班长完成派单员重新派送");
        return 0;
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
    public int closeAB(WorkOrderInfo workOrder) {
        logger.info("班长退单派单员关闭");
        return 0;
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
    public int resetBackAB(WorkOrderInfo workOrder) {
        logger.info("班长退单派单员重新派送");
        return 0;
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
    public int sendAC(WorkOrderInfo workOrder) {
        logger.info("派单员派送工单接单员");
        return 0;
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
    public int saveAndSendAC(WorkOrderInfo workOrder) {
        logger.info("派单员保存工单并派送工单接单员");
        return 0;
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
    public int takingC(WorkOrderInfo workOrder) {
        logger.info("接单员接单");
        return 0;
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
    public int backC(WorkOrderInfo workOrder) {
        logger.info("接单员退单");
        return 0;
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
    public int finishC(WorkOrderInfo workOrder) {
        logger.info("接单员完成");
        return 0;
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
    public int confirmAC(WorkOrderInfo workOrder) {
        logger.info("接单员完成派单员确认");
        return 0;
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
    public int resetFinishAC(WorkOrderInfo workOrder) {
        logger.info("接单员完成派单员重新派送");
        return 0;
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
    public int closeAC(WorkOrderInfo workOrder) {
        logger.info("接单员退单派单员关闭");
        return 0;
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
    public int resetBackAC(WorkOrderInfo workOrder) {
        logger.info("接单员退单派单员重新派送");
        return 0;
    }
}
