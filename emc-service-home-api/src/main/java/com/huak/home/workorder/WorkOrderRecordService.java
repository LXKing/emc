package com.huak.home.workorder;

import com.huak.workorder.model.WorkOrderInfo;
import com.huak.workorder.model.WorkOrderRecord;
import com.huak.workorder.vo.WorkOrderRecordA;

/**
 * Created by MR-BIN on 2017/8/2.
 */
public interface WorkOrderRecordService {

    /**
     * 新建工单，保存工单
     * @param record
     */
    void insertWorkOrderRecord(WorkOrderInfo workOrder);

    /**
     * 查询所有工单信息记录
     * @param code
     * @return
     */
    public WorkOrderRecordA selectAllRecord(String code);


    /**
     * a-b/a-b-c
     * ab派送工单c
     * @param workOrder
     * @return
     */
    int sendABorC(WorkOrderInfo workOrder);


    /**
     * ab-c c退工单回A
     * b退单
     * @param workOrder
     * @return
     */
    int backA(WorkOrderInfo workOrder);

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
     * a-b-c
     * c完成
     * @param workOrder
     * @return
     */
    int finishC(WorkOrderInfo workOrder);

    /**
     * a-b-c
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
     * c-a
     * c退单a重新派送
     * @param workOrder
     * @return
     */
    int resetBackABC(WorkOrderInfo workOrder);
}
