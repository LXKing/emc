package com.huak.home;

import com.huak.base.BaseTest;
import com.huak.org.model.vo.CostVo;
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
    @Test
    @Rollback
    public void getCostTotal(){
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId","1");
        params.put("feetType","2");
        params.put("startTime","2017-05-05");
        params.put("endTime","2017-05-25");
        CostVo costVo = frameService.selectCostTotalByMap(params);
        Double costAll=Double.valueOf(costVo.getEnergy())
                +Double.valueOf(costVo.getDevice())
                +Double.valueOf(costVo.getLabor())+Double.valueOf(costVo.getManage())+Double.valueOf(costVo.getOther());
        System.out.print(costAll);
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
        CostVo ss = frameService.selectFeedCostTotalByMap(params);

        System.out.print(ss.getDevice());
    }
}
