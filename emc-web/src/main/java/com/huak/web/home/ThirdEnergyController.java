package com.huak.web.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/2<BR>
 * Description:   三级能耗  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/third/energy")
public class ThirdEnergyController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 跳转三级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/page/{type}", method = RequestMethod.GET)
    public String secondEconPage(Model model,HttpServletRequest request){
        logger.info("跳转三级能耗页面");
        return "third/energy";
    }
}
