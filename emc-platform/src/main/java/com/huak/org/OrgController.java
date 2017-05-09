package com.huak.org;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import com.huak.org.model.Administrative;
/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/3<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/org")
public class OrgController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        System.out.println("");
        logger.info("");
        return "index";
    }

    @Resource
    private OrgService orgService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/ztree", method = RequestMethod.GET)
    public String ztree(){
        System.out.print("-------------------controller----------------------------");
        Administrative as =orgService.selectAdministrator();
        return "org/orgtree/list";
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feed(){
        return "org/feed/list";
    }

    @RequestMapping(value = "/node", method = RequestMethod.GET)
    public String node(){
        return "org/node/list";
    }
}
