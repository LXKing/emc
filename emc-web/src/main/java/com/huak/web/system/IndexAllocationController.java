package com.huak.web.system;

import com.huak.common.Constants;
import com.huak.health.IndexRecordService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.sys.IndexTypeService;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.system<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/8<BR>
 * Description: 指标配置    <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/index/allocation")
public class IndexAllocationController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private IndexRecordService indexRecordService;
    @Resource
    private IndexTypeService indexTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public String page(){
        logger.info("打开指标配置页");
        return "system/allocation/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPage(HttpServletRequest request,Model model){
        logger.info("打开添加指标配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute("company",company);
        model.addAttribute("org",org);
        return "system/allocation/add";
    }
}
