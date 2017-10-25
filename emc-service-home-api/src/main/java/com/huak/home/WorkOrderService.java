package com.huak.home;

import com.huak.workorder.model.WorkOrderRecord;
import com.huak.workorder.vo.WorkOrderRecordA;


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

public interface WorkOrderService {

    void insertWorkOrderRecord(WorkOrderRecord record);

    WorkOrderRecordA selectAllRecord(String code);
}
