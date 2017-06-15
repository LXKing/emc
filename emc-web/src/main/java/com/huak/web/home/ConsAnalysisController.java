package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CollectionUtil;
import com.huak.common.Constants;
import com.huak.home.ConsAnalysisService;
import com.huak.home.type.ToolVO;
import com.huak.org.OrgService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Date: 2017/6/5<BR>
 * Description:  首页-二级页面- 单耗分析   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/cons/analysis")
public class ConsAnalysisController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ConsAnalysisService consAnalysisService;
    @Resource
    private OrgService orgService;

    /**
     * 跳转二级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/tsec", method = RequestMethod.GET)
    public String secondEconPage(Model model,HttpServletRequest request){
        logger.info("跳转二级单耗页面");
        Company company = (Company)request.getSession().getAttribute(Constants.SESSION_COM_KEY);

        model.addAttribute("company",company);
        return "second/ucon";
    }

    @RequestMapping(value = "/fgs/list", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyList(ToolVO toolVO){
        logger.info("分公司单耗详细");

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
            params.put("orgType",toolVO.getToolOrgType());

            jo.put(Constants.LIST, consAnalysisService.findAssessmentIndicators(params));
        } catch (Exception e) {
            logger.error("分公司单耗详细异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/ratio", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyRatio(ToolVO toolVO){
        logger.info("分公司单耗占比分布图");

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
            params.put("orgType",toolVO.getToolOrgType());

            List<Map<String,Object>> energySecondList = consAnalysisService.fgsEnergyRatio(params);
            jo.put(Constants.LIST, energySecondList);
        } catch (Exception e) {
            logger.error("分公司单耗占比分布图异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/trend", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyTrend(ToolVO toolVO){
        logger.info("分公司单耗趋势对比图");

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
            params.put("orgType",toolVO.getToolOrgType());

            List<Map<String,Object>> trendList = consAnalysisService.fgsEnergyTrend(params);

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
                map.get("BQDH").toString();
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
                        dataList.add(Double.valueOf(map.get("BQDH").toString()));
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
            logger.error("分公司单耗趋势对比图异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/ranking", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyRanking(ToolVO toolVO){
        logger.info("分公司单耗排名");

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
            params.put("orgType",toolVO.getToolOrgType());

            List<Map<String,Object>> energySecondList = consAnalysisService.fgsEnergyRanking(params);
            List<String> xAxis = new ArrayList<>();
            List<String> datas = new ArrayList<>();
            for(Map<String,Object> map:energySecondList){
                xAxis.add(map.get("NAME").toString());
                datas.add(map.get("VALUE").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put(Constants.LIST, datas);
        } catch (Exception e) {
            logger.error("分公司单耗排名异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/an", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyAn(ToolVO toolVO){
        logger.info("分公司单耗同比");

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
            params.put("orgType",toolVO.getToolOrgType());

            List<Map<String,Object>> energySecondList = consAnalysisService.fgsEnergyAn(params);
            List<String> xAxis = new ArrayList<>();
            List<String> bqs = new ArrayList<>();
            List<String> tqs = new ArrayList<>();
            for(Map<String,Object> map:energySecondList){
                bqs.add(map.get("BQDH").toString());
                xAxis.add(map.get("ORGNAME").toString());
                tqs.add(map.get("TQDH").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put("tq", tqs);
            jo.put("bq", bqs);
        } catch (Exception e) {
            logger.error("分公司单耗同比异常" + e.getMessage());
        }
        return jo.toJSONString();

    }
}
