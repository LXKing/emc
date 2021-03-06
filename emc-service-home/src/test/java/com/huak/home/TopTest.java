package com.huak.home;

import com.huak.base.BaseTest;
import com.huak.data.vo.LookupTableTime;
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
    private  DataStatisticsService dataStatisticsService;
//
//    @Resource
//    private  FrameService frameService;
//    @Resource
//    private DateDao dateDao;
//    @Resource
//    private WorkOrderRecordService workOrderService;
//    @Test
//    @Rollback
//    public void getCostTotal(){
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("orgId","74");
//        params.put("feetType","2");
//        params.put("startTime","2016-11-15 00:00:00");
//        params.put("endTime","2017-03-15 23:59:59");
//        params.put("tableName","t_emc_final_data_hour_tj");
//        String  eTotal= frameService.selectTopEtotalByMap(params);
//        System.out.print(eTotal);
//    }
//
//    @Test
//    @Rollback
//    public void getFeedTotal(){
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("orgId","42");
//        params.put("feetType","1");
//        params.put("startTime","2017-06-04 00:00:00");
//        params.put("endTime","2017-06-30 00:00:00");
//        String ss = frameService.selectFeedTotalByMap(params);
//
//        System.out.print(ss);
//    }
//    @Test
//    @Rollback
//    public void getCTotal(){
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("orgId","39");
//        params.put("feetType","1");
//        params.put("startTime","2017-06-04 00:00:00");
//        params.put("endTime","2017-06-30 00:00:00");
//        String ss = frameService.selectTopFeedCarbonTotalByMap(params);
//
//        System.out.print(ss);
//    }
//    @Test
//    @Rollback
//    public void getFeedCostTotal(){
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("orgId","39");
//        params.put("feetType","1");
//        params.put("startTime","2017-06-04 00:00:00");
//        params.put("endTime","2017-06-30 00:00:00");
////        CostVo ss = frameService.selectFeedCostTotalByMap(params);
////
////        System.out.print(ss.getDevice());
//    }
//    @Test
//    @Rollback
//    public void testgd(){
//        String code = "12345";
//        WorkOrderRecordA work = workOrderService.selectAllRecord(code);
//        System.out.println(work.getCode());
//    }
//    @Test
//    @Rollback
//    public void testinsert(){
//        WorkOrderRecord record = new WorkOrderRecord();
//        record.setId(UUIDGenerator.getUUID());
//        record.setCode(UUIDGenerator.getUUID());
//        record.setBeforStatus(Byte.valueOf("0"));
//        record.setOperateTime(dateDao.getTime());
//        workOrderService.insertWorkOrderRecord(record);
//    }
     @Test
     @Rollback
     public  void testTime(){
         Map<String,Object> params = new HashMap<String,Object>();
         params.put("startTime","2017-11-15");
         params.put("endTime","2017-11-16");
         LookupTableTime list =  dataStatisticsService.getDataTime(params);
         System.out.println(list.getLastTime());
     }
    @Test
    @Rollback
    public  void testSanLine(){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("bcTime","2017-11-19 21:00");
        params.put("scTime","2017-11-18 21:00");
        Map<String,Object> map =  dataStatisticsService.getSanWestLine(params);
        System.out.println(map.get("bc"));
    }
    @Test
    @Rollback
    public  void testTotal(){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("bcTime","2017-11-19 21:00");
        params.put("scTime","2017-11-18 21:00");
        Map<String,Object> map =  dataStatisticsService.getTotal(params);
        System.out.println(map.get("bc"));
    }
}
