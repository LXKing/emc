package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.EnergyMonitorService;
import com.huak.home.model.EnergySecond;
import com.huak.home.type.ToolVO;
import com.huak.org.CompanyService;
import com.huak.org.OrgService;
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
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String secondEconPage(Model model,ToolVO toolVO){
        logger.info("跳转二级能耗页面");
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

            List<EnergySecond> energySecondList = eaService.findAssessmentIndicators(params);
            model.addAttribute("fgsList",energySecondList);

            model.addAttribute("company",companyService.selectByPrimaryKey(org.getComId()));
        } catch (ParseException e) {
            logger.error("跳转二级能耗页面异常："+e.getMessage());
        }
        return "second/econ";
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

            List<EnergySecond> energySecondList = eaService.findAssessmentIndicators(params);
            jo.put(Constants.LIST, energySecondList);
        } catch (Exception e) {
            logger.error("分公司能耗占比分布图异常" + e.getMessage());
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
