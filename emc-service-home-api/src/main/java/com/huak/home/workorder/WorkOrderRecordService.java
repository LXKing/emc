package com.huak.home.workorder;

import com.huak.workorder.vo.WorkOrderRecordA;

/**
 * Created by MR-BIN on 2017/8/2.
 */
public interface WorkOrderRecordService {



    /**
     * 查询所有工单信息记录
     * @param code
     * @return
     */
    public WorkOrderRecordA selectAllRecord(String code);



}
