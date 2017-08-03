package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.home.FrameService;
import com.huak.home.type.ToolVO;
import com.huak.org.OrgService;
import com.huak.org.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
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
    @Autowired
    private OrgService orgService;
    /**
     * 查询首页顶部All的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
     @ResponseBody
     public String getAllData(ToolVO toolVO){
        logger.info("查询首页顶部All的数据");
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        JSONObject jo = new JSONObject();
        Map<String,String> params = new HashMap<String,String>();
        Org org = orgService.selectByPrimaryKey(toolVO.getToolOrgId());

        params.put("comId",org.getComId());
        params.put("orgId",toolVO.getToolOrgId());
        params.put("feetType",toolVO.getToolFeedType());
        params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
        params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
        String eTotal=null;
        //String carbonTotal=null;
        //Map<String,String> costMap = new HashMap<String,String>();
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        //CostVo costVo =new CostVo();
//        String yardage=null;
//        String priceArea=null;
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        try{

            eTotal= frameService.selectTopEtotalByMap(params);
            //carbonTotal = frameService.selectCarbonTotalByMap(params);
//            yardage = frameService.selectYardageByMap(params);
//            priceArea = frameService.selectPriceAreaByMap(params);
            Map<String, Object>  costVo = frameService.selectCostTotalByMap(params);
            if(costVo==null||costVo.isEmpty()) {
                logger.info("----------------------该成本没有值--------------------------");
            }else {
                if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
                    device = Double.valueOf(costVo.get("device").toString());
                }
                if (costVo.get("energy") != null && !"".equals(costVo.get("energy"))) {
                    energy = Double.valueOf(costVo.get("energy").toString());
                }
                if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
                    labor = Double.valueOf(costVo.get("labor").toString());
                }
                if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
                    manage = Double.valueOf(costVo.get("manage").toString());
                }
                if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
                    other = Double.valueOf(costVo.get("other").toString());
                }
                costAll=device+energy+labor+manage+other;
            }

            topAll.put("eTotal",eTotal);
            //topAll.put("carbonTotal",carbonTotal);
//            topAll.put("yardage",yardage);
//            topAll.put("priceArea",priceArea);
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
    @RequestMapping(value = "/all1", method = RequestMethod.GET)
    @ResponseBody
    public String getAllData1(ToolVO toolVO){
        logger.info("查询首页顶部All的数据");
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        JSONObject jo = new JSONObject();
        Map<String,String> params = new HashMap<String,String>();
        Org org = orgService.selectByPrimaryKey(toolVO.getToolOrgId());

        params.put("comId",org.getComId());
        params.put("orgId",toolVO.getToolOrgId());
        params.put("feetType",toolVO.getToolFeedType());
        params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
        params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
        //String eTotal=null;
        //String carbonTotal=null;
        //Map<String,String> costMap = new HashMap<String,String>();
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        //CostVo costVo =new CostVo();
        String yardage=null;
        String priceArea=null;
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        //Double costAll=0.00;
        try{

//            eTotal= frameService.selectTopEtotalByMap(params);
//            carbonTotal = frameService.selectCarbonTotalByMap(params);
            yardage = frameService.selectYardageByMap(params);
            priceArea = frameService.selectPriceAreaByMap(params);
//            Map<String, Object>  costVo = frameService.selectCostTotalByMap(params);
//            if(costVo==null||costVo.isEmpty()) {
//                logger.info("----------------------该成本没有值--------------------------");
//            }else {
//                if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
//                    device = Double.valueOf(costVo.get("device").toString());
//                }
//                if (costVo.get("energy") != null && !"".equals(costVo.get("energy"))) {
//                    energy = Double.valueOf(costVo.get("energy").toString());
//                }
//                if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
//                    labor = Double.valueOf(costVo.get("labor").toString());
//                }
//                if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
//                    manage = Double.valueOf(costVo.get("manage").toString());
//                }
//                if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
//                    other = Double.valueOf(costVo.get("other").toString());
//                }
//                costAll=device+energy+labor+manage+other;
//            }

//            topAll.put("eTotal",eTotal);
//            topAll.put("carbonTotal",carbonTotal);
            topAll.put("yardage",yardage);
            topAll.put("priceArea",priceArea);
//            topAll.put("costAll",costAll);
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
    public String getFeedData(ToolVO toolVO){
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        logger.info("查询首页顶部供热源的数据");
        JSONObject jo = new JSONObject();
        Map<String,String> params = new HashMap<String,String>();
        Org org = orgService.selectByPrimaryKey(toolVO.getToolOrgId());

        params.put("comId",org.getComId());
        params.put("orgId",toolVO.getToolOrgId());
        params.put("feetType",toolVO.getToolFeedType());
        params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
        params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
        String eTotal=null;
        String carbonTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        try{
            eTotal= frameService.selectFeedTotalByMap(params);
            //carbonTotal = frameService.selectTopFeedCarbonTotalByMap(params);
            Map<String, Object> costVo = frameService.selectFeedCostTotalByMap(params);
            if(costVo==null||costVo.isEmpty()) {
                logger.info("----------------------该成本没有值--------------------------");
            }else {
                if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
                    device = Double.valueOf(costVo.get("device").toString());
                }
                if (costVo.get("energy") != null && !"".equals(costVo.get("energy"))) {
                    energy = Double.valueOf(costVo.get("energy").toString());
                }
                if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
                    labor = Double.valueOf(costVo.get("labor").toString());
                }
                if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
                    manage = Double.valueOf(costVo.get("manage").toString());
                }
                if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
                    other = Double.valueOf(costVo.get("other").toString());
                }
               costAll=device+energy+labor+manage+other;
            }
            topAll.put("eTotal",eTotal);
            topAll.put("carbonTotal",carbonTotal);
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
     * 查询首页顶部管网的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/net", method = RequestMethod.GET)
    @ResponseBody
    public String getNetData(ToolVO toolVO){
        logger.info("查询首页顶部管网的数据");
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        JSONObject jo = new JSONObject();
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId",toolVO.getToolOrgId());
        params.put("feetType",toolVO.getToolFeedType());
        params.put("startTime",toolVO.getToolStartDate());
        params.put("endTime",toolVO.getToolEndDate());
        String netlen=null;
        String carbonTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();

        BigDecimal b1 = new BigDecimal(0.0);
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        try{
            netlen= frameService.selectGetNetLengh(params);
            Map<String, Object> costVo = frameService.selectGetNetCost(params);
            if(costVo==null||costVo.isEmpty()) {
                logger.info("----------------------该成本没有值--------------------------");
            }else {
                if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
                    device = Double.valueOf(costVo.get("device").toString());
                }
                if (costVo.get("energy") != null && !"".equals(costVo.get("energy"))) {
                    energy = Double.valueOf(costVo.get("energy").toString());
                }
                if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
                    labor = Double.valueOf(costVo.get("labor").toString());
                }
                if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
                    manage = Double.valueOf(costVo.get("manage").toString());
                }
                if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
                    other = Double.valueOf(costVo.get("other").toString());
                }
                costAll=device+energy+labor+manage+other;
            }

            topAll.put("netLen",netlen);
//            topAll.put("carbonTotal",Double.valueOf(carbonTotal));

            topAll.put("netCost",costAll);
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
     * 查询首页顶部换热站的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/station", method = RequestMethod.GET)
    @ResponseBody
    public String getStationData(ToolVO toolVO){
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        logger.info("查询首页顶部换热站的数据");
        JSONObject jo = new JSONObject();
        Org org = orgService.selectByPrimaryKey(toolVO.getToolOrgId());

        Map<String,String> params = new HashMap<String,String>();
        params.put("comId",org.getComId());
        params.put("orgId",toolVO.getToolOrgId());
        params.put("feetType",toolVO.getToolFeedType());
        params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
        params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
        String eTotal=null;
        String carbonTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        try{
            eTotal= frameService.selectTopStationTotalByMap(params);
            //carbonTotal = frameService.selectTopStationCarbonTotalByMap(params);
//            Map<String, Object> costVo = frameService.selectStationCostTotalByMap(params);
//            if(costVo==null||costVo.isEmpty()) {
//                logger.info("----------------------该成本没有值--------------------------");
//            }else {
//                if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
//                    device = Double.valueOf(costVo.get("device").toString());
//                }
//                if (costVo.get("energy") != null && !"".equals(costVo.get("energy"))) {
//                    energy = Double.valueOf(costVo.get("energy").toString());
//                }
//                if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
//                    labor = Double.valueOf(costVo.get("labor").toString());
//                }
//                if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
//                    manage = Double.valueOf(costVo.get("manage").toString());
//                }
//                if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
//                    other = Double.valueOf(costVo.get("other").toString());
//                }
//                costAll=device+energy+labor+manage+other;
//            }

            topAll.put("eTotal",eTotal);
            topAll.put("carbonTotal",carbonTotal);
            //topAll.put("costAll",costAll);
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
     * 查询首页顶部管线的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/line", method = RequestMethod.GET)
    @ResponseBody
    public String getLineData(ToolVO toolVO){
        logger.info("查询首页顶部管线的数据");
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        JSONObject jo = new JSONObject();
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId",toolVO.getToolOrgId());
        params.put("feetType",toolVO.getToolFeedType());
        params.put("startTime",toolVO.getToolStartDate());
        params.put("endTime",toolVO.getToolEndDate());
        String linelen=null;
        String carbonTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        try{
            linelen= frameService.selectGetLineLengh(params);
            Map<String, Object> costVo = frameService.selectGetLineCost(params);
            if(costVo==null||costVo.isEmpty()) {
                logger.info("----------------------该成本没有值--------------------------");
            }else {
                if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
                    device = Double.valueOf(costVo.get("device").toString());
                }
                if (costVo.get("energy") != null && !"".equals(costVo.get("energy"))) {
                    energy = Double.valueOf(costVo.get("energy").toString());
                }
                if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
                    labor = Double.valueOf(costVo.get("labor").toString());
                }
                if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
                    manage = Double.valueOf(costVo.get("manage").toString());
                }
                if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
                    other = Double.valueOf(costVo.get("other").toString());
                }
                costAll=device+energy+labor+manage+other;
            }
            topAll.put("lineCost",costAll);
            topAll.put("lineLen",linelen);
//            topAll.put("carbonTotal",Double.valueOf(carbonTotal));
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
     * 查询首页顶部民户的数据
     * @param
     * @return
     */
    @RequestMapping(value = "/room", method = RequestMethod.GET)
    @ResponseBody
    public String getRoomData(ToolVO toolVO){
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        logger.info("查询首页顶部民户的数据");
        JSONObject jo = new JSONObject();
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId",toolVO.getToolOrgId());
        params.put("feetType",toolVO.getToolFeedType());
        params.put("startTime",toolVO.getToolStartDate());
        params.put("endTime",toolVO.getToolEndDate());
        String rTotal=null;
        String hgl=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        Double costAll=0.00;
        try{
            //rTotal= frameService.selectTopRoomTotalByMap(params);
           //hgl = frameService.selectTopRoomHglByMap(params);
//            Map<String, Object> costVo = frameService.getTopRoomCostByMap(params);
//            if(costVo==null||costVo.isEmpty()) {
//                logger.info("----------------------该成本没有值--------------------------");
//            }else {
//                if (costVo.get("device") != null && !"".equals(costVo.get("device"))) {
//                    device = Double.valueOf(costVo.get("device").toString());
//                }
//                if (costVo.get("energy") != null && !"".equals(costVo.get("energy"))) {
//                    energy = Double.valueOf(costVo.get("energy").toString());
//                }
//                if (costVo.get("labor") != null && !"".equals(costVo.get("labor"))) {
//                    labor = Double.valueOf(costVo.get("labor").toString());
//                }
//                if (costVo.get("manage") != null && !"".equals(costVo.get("manage"))) {
//                    manage = Double.valueOf(costVo.get("manage").toString());
//                }
//                if (costVo.get("other") != null && !"".equals(costVo.get("other"))) {
//                    other = Double.valueOf(costVo.get("other").toString());
//                }
//                costAll=device+energy+labor+manage+other;
//            }
            topAll.put("roomCost",costAll);
            topAll.put("rTotal",rTotal);
            topAll.put("hgl",hgl);
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

    public static void  main (String[] args){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("EEC","234");
        map.put("MMN","");
//        if(map.get("MMN")==null||"".equals(map.get("MMN"))){
//            System.out.print("1为空");
//        }
//        if(map.get("EEC")==null||"".equals(map.get("EEC"))){
//            System.out.print("2为空");
//        }
          if(map.isEmpty()){
              System.out.print("是空的啊");
          }else {
              System.out.print("不是空的啊");
          }
//        System.out.print("是这个值吗---"+map.get("MMN"));
//        System.out.print("是这个值吗---"+map.get("EEC"));

    }

}
