package com.huak.web.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.mdc.EnergyDataHisService;
import com.huak.mdc.MeterCollectService;
import com.huak.org.model.Company;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.system<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/4<BR>
 * Description:  数据填报   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/data/fill")
public class DataFillController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MeterCollectService meterCollectService;


    @RequestMapping(method = RequestMethod.GET)
    public String page(){
        logger.info("打开数据填报查询页");
        return "system/fill/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String pageAdd(HttpServletRequest request,Model model){
        logger.info("打开数据填报页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Map<String,Object> params = new HashMap<>();
        params.put("comId",company.getId());
        List<Map<String,Object>> data = meterCollectService.getUnitInfo(params);
        model.addAttribute("unit",data);
        return "system/fill/add";
    }

    /**
     * 安全与后台-数据填报
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST  )
    @ResponseBody
    public String add(HttpServletRequest request,@RequestBody List<Map<String,Object>> datas){
        logger.info("安全与后台-数据填报开始");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        JSONObject jo = new JSONObject();
        if(datas == null || datas.size() <= 0){ return "No any ID.中文"; }
        jo.put("data",datas);
        jo.put("comId",company.getId());
        boolean flag = meterCollectService.fillData(jo);
        if(flag){
            return "0";
        }
        return "1";
    }
}
