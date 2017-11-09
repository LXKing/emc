package com.huak.home;

import com.huak.base.BaseTest;
import com.huak.base.dao.DateDao;
import com.huak.home.workorder.WorkOrderRecordService;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/9<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class TopTest extends BaseTest{

    @Resource
    private  FrameService frameService;
    @Resource
    private DateDao dateDao;
    @Resource
    private WorkOrderRecordService workOrderService;
    @Test
    @Rollback
    public void getCostTotal(){
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId","74");
        params.put("feetType","2");
        params.put("startTime","2016-11-15 00:00:00");
        params.put("endTime","2017-03-15 23:59:59");
        params.put("tableName","t_emc_final_data_hour_tj");
        String  eTotal= frameService.selectTopEtotalByMap(params);
        System.out.print(eTotal);
    }

    @Test
    @Rollback
    public void getFeedTotal(){
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId","42");
        params.put("feetType","1");
        params.put("startTime","2017-06-04 00:00:00");
        params.put("endTime","2017-06-30 00:00:00");
        String ss = frameService.selectFeedTotalByMap(params);

        System.out.print(ss);
    }
    @Test
    @Rollback
    public void getCTotal(){
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId","39");
        params.put("feetType","1");
        params.put("startTime","2017-06-04 00:00:00");
        params.put("endTime","2017-06-30 00:00:00");
        String ss = frameService.selectTopFeedCarbonTotalByMap(params);

        System.out.print(ss);
    }
    @Test
    @Rollback
    public void getFeedCostTotal(){
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId","39");
        params.put("feetType","1");
        params.put("startTime","2017-06-04 00:00:00");
        params.put("endTime","2017-06-30 00:00:00");
//        CostVo ss = frameService.selectFeedCostTotalByMap(params);
//
//        System.out.print(ss.getDevice());
    }
//    @Test
//    @Rollback
//    public void testgd(){
//        String code = "12345";
//        WorkOrderRecordA work = workOrderService.selectAllRecord(code);
//        System.out.println(work.getCode());
//    }
    @Test
    @Rollback
    public void testinsert(){
//        WorkOrderRecord record = new WorkOrderRecord();
//        record.setId(UUIDGenerator.getUUID());
//        record.setCode(UUIDGenerator.getUUID());
//        record.setBeforStatus(Byte.valueOf("0"));
//        record.setOperateTime(dateDao.getTime());
//        record.setOpertor("73155a30b2484c89b853d8683fca5935");
//        record.setSendee("73155a30b2484c89b853d8683fca5935");
//        record.setAfterStatus(Byte.valueOf("0"));
//        record.setDes("sdsfdsfds");
//        workOrderService.insertWorkOrderRecord(record);
    }

//    @Test
//    @Rollback
//    public void testSendgd(){
//        String code = "12345";
//        WorkOrderRecordA work = workOrderService.selectAllRecord(code);
//        System.out.println(work.getCode());
//        //创建工单--controller
//
//        WorkOrderInfo workOrderInfo = new WorkOrderInfo();
//        workOrderInfo.setId(UUIDGenerator.getUUID());
//        workOrderInfo.setCode("123456");
//        workOrderInfo.setType(Byte.valueOf("0"));
//
//        workOrderInfo.setName("工单类型");
//        workOrderInfo.setContent("赶紧写代码，受不了了，快快滴");
//        workOrderInfo.setStartTime(dateDao.getTime());
//        workOrderInfo.setFinishTime(dateDao.getTime());
//        workOrderInfo.setCreateTime(dateDao.getTime());
//
//        workOrderInfo.setCreator("73155a30b2484c89b853d8683fca5935");
//        workOrderInfo.setStatus(1);
//        workOrderInfo.setFinish("73155a30b2484c89b853d8683fca5935");
//        workOrderInfo.setActualFinishTime(dateDao.getTime());
//        workOrderInfo.setComid("a3e5e868e7844c349e5cf51c5e6bc37d");
//        workOrderInfo.setReason("");
//        workOrderInfo.setMonitor("73155a30b2484c89b853d8683fca5935");
//        workOrderInfo.setTakor("73155a30b2484c89b853d8683fca5935");
//        //service 处理工单流转 更新工单当前状态
////        int i = workOrderService.sendABorC(workOrderInfo);
////        System.out.println(i);
//    }
//        public static void main(String[] args) {
//        System.out.println(WorkOrderStatus.A111.getKey());
//        System.out.println(WorkOrderStatus.R993.getStatus());
//    }
}
