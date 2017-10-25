package com.huak.home;

import com.alibaba.dubbo.config.annotation.Service;
import com.huak.workorder.dao.WorkOrderRecordDao;
import com.huak.workorder.model.WorkOrderRecord;
import com.huak.workorder.vo.WorkOrderRecordA;

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
public class WorkOrderServiceImpl implements WorkOrderService {

    @Resource
    private WorkOrderRecordDao workOrderRecordDao;
    @Override
    public void insertWorkOrderRecord(WorkOrderRecord record) {
        workOrderRecordDao.insertSelective(record);
    }

    @Override
    public WorkOrderRecordA selectAllRecord(String code) {
        return workOrderRecordDao.selectAllRecord(code);
    }
}
