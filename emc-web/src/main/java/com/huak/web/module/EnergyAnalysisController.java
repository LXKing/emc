package com.huak.web.module;

import com.alibaba.fastjson.JSONObject;
import com.huak.energy.EnergyMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.module<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/24<BR>
 * Description:   能耗分析模块  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/energy/analysis")
public class EnergyAnalysisController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private EnergyMonitorService eaService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(Model model){
        logger.info("跳转能耗分析首页");
        Map<String,String> params = new HashMap<String,String>();
        params.put("yearDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Map<String,Object> result = eaService.groupEnergy2Day(params);
        model.addAttribute("result", result);
        return "energy/analysis/index";
    }
    
    @RequestMapping(value = "/groupEnergy", method = RequestMethod.GET)
    @ResponseBody
    public String groupEnergy(){
        logger.info("查询集团能耗数据");
        JSONObject jo = new JSONObject();
        jo.put("success", true);
        jo.put("message", "查询集团能耗数据成功！");
        Map<String,Object> retMap = eaService.groupEnergy();
        jo.put("data", retMap);
        return jo.toJSONString();
    }
}
