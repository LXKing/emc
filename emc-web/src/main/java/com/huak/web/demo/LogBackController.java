package com.huak.web.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by lichao on 2016/7/26.
 */
@Controller
@RequestMapping("/logback")
public class LogBackController {
    /** 日志实例 */
    private static final Logger logger = LoggerFactory.getLogger(LogBackController.class);

    @Resource
    //private UserService userService;

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String test(@PathVariable(value = "id")Long id){
        logger.info("查询用户");

        return "";
    }
}
