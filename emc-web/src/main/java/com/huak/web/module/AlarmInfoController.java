package com.huak.web.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.module<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/24<BR>
 * Description:  报警模块   <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/alarm/info")
public class AlarmInfoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(){
        logger.info("跳转报警管理首页");
        return "alarm/info/index";
    }
}
