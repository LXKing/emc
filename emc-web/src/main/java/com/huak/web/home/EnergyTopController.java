package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.home.FrameService;
import com.huak.home.type.ToolVO;
import com.huak.org.model.vo.CostVo;
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
        params.put("orgId",toolVO.getToolOrgId());
        params.put("feetType",toolVO.getToolFeedType());
        params.put("startTime",toolVO.getToolStartDate());
        params.put("endTime",toolVO.getToolEndDate());
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
            topAll.put("yardage",df.format(Double.valueOf(yardage)));
            topAll.put("priceArea",Double.valueOf(priceArea));
            topAll.put("costAll",df.format(costAll));
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
        logger.info("查询首页顶部供热源的数据");
        JSONObject jo = new JSONObject();
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId",toolVO.getToolOrgId());
        params.put("feetType",toolVO.getToolFeedType());
        params.put("startTime",toolVO.getToolStartDate());
        params.put("endTime",toolVO.getToolEndDate());
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
        //CostVo costVo =new CostVo();
        BigDecimal b1 = new BigDecimal(0.0);
        String costVo;
        float f =0.01f;
        try{
            netlen= frameService.selectGetNetLengh(params);
            costVo = frameService.selectGetNetCost(params);
//            if(costVo.getDevice()!=null&&!"".equals(costVo.getDevice())){
//                  f + Double.valueOf(costVo.getDevice());
//            }else {
//
//            }
//            Double costAll= Double.valueOf(costVo.getDevice())
//                    +Double.valueOf(costVo.getLabor())+Double.valueOf(costVo.getManage())+Double.valueOf(costVo.getOther());
            topAll.put("netLen",Double.valueOf(netlen));
//            topAll.put("carbonTotal",Double.valueOf(carbonTotal));
            topAll.put("netCost",costVo);
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
        logger.info("查询首页顶部换热站的数据");
        JSONObject jo = new JSONObject();
        Map<String,String> params = new HashMap<String,String>();
        params.put("orgId",toolVO.getToolOrgId());
        params.put("feetType",toolVO.getToolFeedType());
        params.put("startTime",toolVO.getToolStartDate());
        params.put("endTime",toolVO.getToolEndDate());
        String eTotal=null;
        String carbonTotal=null;
        List<Map<String,String>> costList = new ArrayList<Map<String,String>>();
        Map<String,Object> topAll = new HashMap<String,Object>();
        CostVo costVo =new CostVo();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        try{
            eTotal= frameService.selectTopStationTotalByMap(params);
            carbonTotal = frameService.selectTopStationCarbonTotalByMap(params);
            costVo = frameService.selectStationCostTotalByMap(params);

              if(costVo.getDevice()!=null&&!"".equals(costVo.getDevice())){
                  energy=costVo.getDevice();
              }else if(costVo.getEnergy()!=null&&!"".equals(costVo.getEnergy())){
                  energy=costVo.getEnergy();
              }else if(costVo.getLabor()!=null&&!"".equals(costVo.getLabor())){
                  labor=costVo.getLabor();
              }else if (costVo.getManage()!=null&&!"".equals(costVo.getManage())){
                  manage= costVo.getManage();
              }else if (costVo.getOther()!=null&&!"".equals(costVo.getOther())){
                  other=costVo.getOther();
              }
            Double costAll=device+energy+labor+manage+other;
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
        CostVo costVo =new CostVo();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        try{
            linelen= frameService.selectGetLineLengh(params);
            costVo = frameService.selectGetLineCost(params);
            topAll.put("lineLen",Double.valueOf(linelen));
            if(costVo.getDevice()!=null&&!"".equals(costVo.getDevice())){
                energy=costVo.getDevice();
            }else if(costVo.getEnergy()!=null&&!"".equals(costVo.getEnergy())){
                energy=costVo.getEnergy();
            }else if(costVo.getLabor()!=null&&!"".equals(costVo.getLabor())){
                labor=costVo.getLabor();
            }else if (costVo.getManage()!=null&&!"".equals(costVo.getManage())){
                manage= costVo.getManage();
            }else if (costVo.getOther()!=null&&!"".equals(costVo.getOther())){
                other=costVo.getOther();
            }
            Double costAll=device+energy+labor+manage+other;
            topAll.put("lineCost",costAll);
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
        CostVo costVo =new CostVo();
        Double device=0.00;
        Double energy=0.00;
        Double labor =0.00;
        Double manage=0.00;
        Double other= 0.00;
        try{
            rTotal= frameService.selectTopRoomTotalByMap(params);
            hgl = frameService.selectTopRoomHglByMap(params);
            costVo = frameService.getTopRoomCostByMap(params);
            if(costVo.getDevice()!=null&&!"".equals(costVo.getDevice())){
                energy=costVo.getDevice();
            }else if(costVo.getEnergy()!=null&&!"".equals(costVo.getEnergy())){
                energy=costVo.getEnergy();
            }else if(costVo.getLabor()!=null&&!"".equals(costVo.getLabor())){
                labor=costVo.getLabor();
            }else if (costVo.getManage()!=null&&!"".equals(costVo.getManage())){
                manage= costVo.getManage();
            }else if (costVo.getOther()!=null&&!"".equals(costVo.getOther())){
                other=costVo.getOther();
            }
            Double costAll=device+energy+labor+manage+other;
            topAll.put("roomCost",costAll);
            topAll.put("rTotal",Double.valueOf(rTotal));
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
        BigDecimal b1 = new BigDecimal("0.3");
        CostVo costVo =new CostVo();
        costVo.setDevice(0.55);

            System.out.print(b1.add(new BigDecimal(costVo.getDevice())).doubleValue());
    }

}
