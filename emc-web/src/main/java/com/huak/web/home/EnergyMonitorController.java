package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.home.EnergyMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 跳转二级能耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/tsec", method = RequestMethod.GET)
    public String secondEconPage(Model model){
        logger.info("跳转二级能耗页面");
        return "second/econ";
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
