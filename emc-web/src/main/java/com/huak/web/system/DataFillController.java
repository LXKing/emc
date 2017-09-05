package com.huak.web.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class DataFillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String page(){
        logger.info("打开数据填报查询页");
        return "system/fill/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String pageAdd(){
        logger.info("打开数据填报页");
        return "system/fill/add";
    }
}
