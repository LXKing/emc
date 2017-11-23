package com.huak.web.system.dataforms;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
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
 * Description:  数据分析   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/data/daily")
public class DataFormsController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MeterCollectService meterCollectService;
    @Resource
    EnergyDataHisService energyDataHisService;
    private final String COM_ID = "comId";

    @RequestMapping(method = RequestMethod.GET)
    public String page(HttpServletRequest request,Model model){
        logger.info("打开数据日报分析查询页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Map<String,Object> params = new HashMap<>();
        params.put(COM_ID,company.getId());
        List<Map<String,Object>> data = meterCollectService.getUnitInfo(params);
        model.addAttribute("unit",data);
        return "system/dataforms/list";
    }

}
