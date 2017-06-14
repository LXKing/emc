package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CollectionUtil;
import com.huak.common.Constants;
import com.huak.home.EnergyMonitorService;
import com.huak.home.type.ToolVO;
import com.huak.org.CompanyService;
import com.huak.org.OrgService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/26<BR>
 * Description:  首页-二级页面- 能耗分析  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/energy/monitor")
public class EnergyMonitorController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private EnergyMonitorService eaService;
    @Resource
    private OrgService orgService;
    @Resource
    private CompanyService companyService;

    /**
     * 跳转二级能耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/tsec", method = RequestMethod.GET)
    public String secondEconPage(Model model,HttpServletRequest request){
        logger.info("跳转二级能耗页面");
        try {
            Company company = (Company)request.getSession().getAttribute(Constants.SESSION_COM_KEY);

            model.addAttribute("company",company);
        } catch (Exception e) {
            logger.error("跳转二级能耗页面异常："+e.getMessage());
        }
        return "second/econ";
    }

    @RequestMapping(value = "/fgs/energy/list", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyList(ToolVO toolVO){
        logger.info("分公司能耗详细");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
            Org org = orgService.selectByPrimaryKey(toolVO.getToolOrgId());
            if(org.getpOrgId()==0){
                params.put("pOrgId",org.getId());
            }else{
                params.put("orgId",org.getId());
            }
            params.put("comId",org.getComId());
            params.put("feedType",toolVO.getToolFeedType());
            params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
            params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
            params.put("startTimeTq",toolVO.getToolStartDateTq()+" 00:00:00");
            params.put("endTimeTq",toolVO.getToolEndDateTq()+" 23:59:59");

            jo.put(Constants.LIST, eaService.findAssessmentIndicators(params));
        } catch (Exception e) {
            logger.error("分公司能耗详细异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/energy/ratio", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyRatio(ToolVO toolVO){
        logger.info("分公司能耗占比分布图");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
            Org org = orgService.selectByPrimaryKey(toolVO.getToolOrgId());
            if(org.getpOrgId()==0){
                params.put("pOrgId",org.getId());
            }else{
                params.put("orgId",org.getId());
            }
            params.put("comId",org.getComId());
            params.put("feedType",toolVO.getToolFeedType());
            params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
            params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
            params.put("startTimeTq",toolVO.getToolStartDateTq()+" 00:00:00");
            params.put("endTimeTq",toolVO.getToolEndDateTq()+" 23:59:59");

            List<Map<String,Object>> energySecondList = eaService.fgsEnergyRatio(params);
            jo.put(Constants.LIST, energySecondList);
        } catch (Exception e) {
            logger.error("分公司能耗占比分布图异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/energy/trend", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyTrend(ToolVO toolVO){
        logger.info("分公司能耗趋势对比图");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
            Org org = orgService.selectByPrimaryKey(toolVO.getToolOrgId());
            if(org.getpOrgId()==0){
                params.put("pOrgId",org.getId());
            }else{
                params.put("orgId",org.getId());
            }
            params.put("comId",org.getComId());
            params.put("feedType",toolVO.getToolFeedType());
            params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
            params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
            params.put("startTimeTq",toolVO.getToolStartDateTq()+" 00:00:00");
            params.put("endTimeTq",toolVO.getToolEndDateTq()+" 23:59:59");

            List<Map<String,Object>> trendList = eaService.fgsEnergyTrend(params);

            List<String> xAxis = new ArrayList<>();
            List<Map<String,Object>> series = new LinkedList<>();
            List<String> legends = new ArrayList<>();
            //先确定几条线和横坐标
            for(Map<String,Object> map : trendList){
                String fgsId = map.get("FGSID").toString();
                String name = map.get("NAME").toString();
                String date = map.get("DATE").toString();
                legends.add(name);
                xAxis.add(date);
                map.get("BQBM").toString();
            }
            //去重复
            xAxis = CollectionUtil.removeDuplicateWithOrder(xAxis);
            legends = CollectionUtil.removeDuplicateWithOrder(legends);
            //组装数据
            for(String gsName:legends){
                Map<String,Object> dataMap = new HashMap<>();
                List<Double> dataList = new ArrayList<>();
                for(Map<String,Object> map : trendList){
                    String name = map.get("NAME").toString();
                    if(name.equals(gsName)){
                        dataList.add(Double.valueOf(map.get("BQBM").toString()));
                    }
                }
                dataMap.put("name",gsName);
                dataMap.put("type","line");
                dataMap.put("symbol","circle");
                dataMap.put("smooth",false);
                dataMap.put("data",dataList);
                series.add(dataMap);
            }

            jo.put(Constants.XAXIS,xAxis);
            jo.put(Constants.LEGENDS,legends);
            jo.put(Constants.LIST, series);
        } catch (Exception e) {
            logger.error("分公司能耗趋势对比图异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/energy/ranking", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyRanking(ToolVO toolVO){
        logger.info("分公司能耗排名");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
            Org org = orgService.selectByPrimaryKey(toolVO.getToolOrgId());
            if(org.getpOrgId()==0){
                params.put("pOrgId",org.getId());
            }else{
                params.put("orgId",org.getId());
            }
            params.put("comId",org.getComId());
            params.put("feedType",toolVO.getToolFeedType());
            params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
            params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
            params.put("startTimeTq",toolVO.getToolStartDateTq()+" 00:00:00");
            params.put("endTimeTq",toolVO.getToolEndDateTq()+" 23:59:59");

            List<Map<String,Object>> energySecondList = eaService.fgsEnergyRanking(params);
            List<String> xAxis = new ArrayList<>();
            List<String> datas = new ArrayList<>();
            for(Map<String,Object> map:energySecondList){
                xAxis.add(map.get("NAME").toString());
                datas.add(map.get("VALUE").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put(Constants.LIST, datas);
        } catch (Exception e) {
            logger.error("分公司能耗排名异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/energy/an", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyAn(ToolVO toolVO){
        logger.info("分公司能耗同比");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
            Org org = orgService.selectByPrimaryKey(toolVO.getToolOrgId());
            if(org.getpOrgId()==0){
                params.put("pOrgId",org.getId());
            }else{
                params.put("orgId",org.getId());
            }
            params.put("comId",org.getComId());
            params.put("feedType",toolVO.getToolFeedType());
            params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
            params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
            params.put("startTimeTq",toolVO.getToolStartDateTq()+" 00:00:00");
            params.put("endTimeTq",toolVO.getToolEndDateTq()+" 23:59:59");

            List<Map<String,Object>> energySecondList = eaService.fgsEnergyAn(params);
            List<String> xAxis = new ArrayList<>();
            List<String> bqs = new ArrayList<>();
            List<String> tqs = new ArrayList<>();
            for(Map<String,Object> map:energySecondList){
                bqs.add(map.get("BQBM").toString());
                xAxis.add(map.get("ORGNAME").toString());
                tqs.add(map.get("TQBM").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put("tq", tqs);
            jo.put("bq", bqs);
        } catch (Exception e) {
            logger.error("分公司能耗同比异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    /**
     * 查询集团能耗数据
     * @param params
     * @return
     */
	@RequestMapping(value = "/groupEnergy", method = RequestMethod.GET)
    @ResponseBody
    public String groupEnergy(Map<String,String> params){
        logger.info("查询集团能耗数据");
        JSONObject jo = new JSONObject();
        jo.put("success", true);
        jo.put("message", "查询集团能耗数据成功！");
        //查询折线数据
        Map<String,Object> retMap = eaService.groupEnergyLine(params);
        jo.put("data", retMap);
        return jo.toJSONString();
    }
}
