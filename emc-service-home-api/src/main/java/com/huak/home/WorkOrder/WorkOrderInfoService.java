package com.huak.home.workorder;

import com.huak.workorder.model.WorkOrderInfo;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.workorder<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-10-25<BR>
 * Description:   工单 a派单员 b班长 c接单员  <BR>
 * Function List:  <BR>
 */
public interface WorkOrderInfoService {
    /**
     * 保存工单
     * @param workOrder
     * @return
     */
    int saveA(WorkOrderInfo workOrder);



    /**
     * a-b/a-b-c
     * a派送工单b
     * @param workOrder
     * @return
     */
    int sendABorC(WorkOrderInfo workOrder);

    /**
     * a-b/a-b-c
     * a保存工单并派送工单b
     * @param workOrder
     * @return
     */
    int saveAndSendABorC(WorkOrderInfo workOrder);

    /**
     * a-b
     * b接单
     * @param workOrder
     * @return
     */
    int takingB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b退单
     * @param workOrder
     * @return
     */
    int backB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b完成
     * @param workOrder
     * @return
     */
    int finishB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b完成a确认
     * @param workOrder
     * @return
     */
    int confirmAB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b完成a重新派送
     * @param workOrder
     * @return
     */
    int resetFinishAB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b退单a关闭
     * @param workOrder
     * @return
     */
    int closeAB(WorkOrderInfo workOrder);

    /**
     * a-b
     * b退单a重新派送
     * @param workOrder
     * @return
     */
    int resetBackAB(WorkOrderInfo workOrder);



    /**
     * a-c
     * a派送工单c
     * @param workOrder
     * @return
     */
    int sendAC(WorkOrderInfo workOrder);

    /**
     * a-c
     * a保存工单并派送工单c
     * @param workOrder
     * @return
     */
    int saveAndSendAC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c接单
     * @param workOrder
     * @return
     */
    int takingC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c退单
     * @param workOrder
     * @return
     */
    int backC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c完成
     * @param workOrder
     * @return
     */
    int finishC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c完成a确认
     * @param workOrder
     * @return
     */
    int confirmAC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c完成a重新派送
     * @param workOrder
     * @return
     */
    int resetFinishAC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c退单a关闭
     * @param workOrder
     * @return
     */
    int closeAC(WorkOrderInfo workOrder);

    /**
     * a-c
     * c退单a重新派送
     * @param workOrder
     * @return
     */
    int resetBackAC(WorkOrderInfo workOrder);
}
