package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.home.FrameService;
import com.huak.org.model.vo.CostVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/8<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/energy/top")
public class EnergyTopController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private  FrameService frameService;

    /**
     * 查询首页顶部All的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public String getAllData(@RequestParam String orgId,
                             @RequestParam String feetType,
                             @RequestParam String startTime,
                             @RequestParam String endTime){
        logger.info("查询首页顶部All的数据");
        JSONObject jo = new JSONObject();
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId",orgId);
        params.put("feetType",feetType);
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        String eTotal=null;
        String carbonTotal=null;
        //Map<String,String> costMap = new HashMap<String,String>();
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        CostVo costVo =new CostVo();
        String yardage=null;
        String priceArea=null;
        try{

            eTotal= frameService.selectTopEtotalByMap(params);
            carbonTotal = frameService.selectCarbonTotalByMap(params);
            yardage = frameService.selectYardageByMap(params);
            priceArea = frameService.selectPriceAreaByMap(params);
            costVo = frameService.selectCostTotalByMap(params);
            Double costAll=Double.valueOf(costVo.getEnergy())
                    +Double.valueOf(costVo.getDevice())
                    +Double.valueOf(costVo.getLabor())+Double.valueOf(costVo.getManage())+Double.valueOf(costVo.getOther());
            topAll.put("eTotal",Double.valueOf(eTotal));
            topAll.put("carbonTotal",Double.valueOf(carbonTotal));
            topAll.put("yardage",Double.valueOf(yardage));
            topAll.put("priceArea",Double.valueOf(priceArea));
            topAll.put("costAll",costAll);
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
        jo.put("all", topAll);
        jo.put("success", true);
        jo.put("message", "查询数据成功！");

        return jo.toJSONString();
    }
    /**
     * 查询首页顶部供热源的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    @ResponseBody
    public String getFeedData(@RequestParam String orgId,
                             @RequestParam String feetType,
                             @RequestParam String startTime,
                             @RequestParam String endTime){
        logger.info("查询首页顶部供热源的数据");
        JSONObject jo = new JSONObject();
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId",orgId);
        params.put("feetType",feetType);
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        String eTotal=null;
        String carbonTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        CostVo costVo =new CostVo();
        try{
            eTotal= frameService.selectFeedTotalByMap(params);
            carbonTotal = frameService.selectTopFeedCarbonTotalByMap(params);
            costVo = frameService.selectFeedCostTotalByMap(params);
            Double costAll=Double.valueOf(costVo.getEnergy())
                    +Double.valueOf(costVo.getDevice())
                    +Double.valueOf(costVo.getLabor())+Double.valueOf(costVo.getManage())+Double.valueOf(costVo.getOther());
            topAll.put("eTotal",Double.valueOf(eTotal));
            topAll.put("carbonTotal",Double.valueOf(carbonTotal));
            topAll.put("costAll",costAll);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jo.put("all", topAll);
        jo.put("success", true);
        jo.put("message", "查询数据成功！");

        return jo.toJSONString();
    }
}
