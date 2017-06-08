package com.huak.web.home;

import com.huak.home.ConsAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

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

    /**
     * 跳转二级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/tsec", method = RequestMethod.GET)
    public String secondEconPage(Model model){
        logger.info("跳转二级能耗页面");
        return "second/ucon";
    }
}
