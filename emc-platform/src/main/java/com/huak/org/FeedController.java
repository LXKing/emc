package com.huak.org;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.common.page.Page;
import com.huak.org.model.Feed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/16<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/feed")
public class FeedController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private FeedService feedService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统热源列表页");
        return "/org/feed/list";
    }

    @RequestMapping(value = "/listpage", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("热源列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, feedService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("热力站列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage() {
        return "/org/feed/add";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改热源页");
        try {
            Feed feed = feedService.selectByPrimaryKey(id);
            model.addAttribute("feed", feed);
        } catch (Exception e) {
            logger.error("跳转修改热源页异常" + e.getMessage());
        }
        return "/org/feed/edit";
    }

    @RequestMapping(value = "/addvalue", method = RequestMethod.POST)
    @ResponseBody
    public String add(Feed feed, HttpServletRequest request) {
        logger.info("添加热源");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();

            feedService.insertSelective(feed);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加热源成功");
        } catch (Exception e) {
            logger.error("添加热源异常" + e.getMessage());
            jo.put(Constants.MSG, "添加热源失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/editvalue", method = RequestMethod.POST)
    @ResponseBody
    public String editValue(Feed feed) {
        logger.info("修改热源");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int i = feedService.updateByPrimaryKeySelective(feed);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改热源成功");
        } catch (Exception e) {
            logger.error("修改热源异常" + e.getMessage());
            jo.put(Constants.MSG, "修改热源失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFeed(@PathVariable("id") String id) {
        logger.info("删除热源");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            feedService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除热源成功");
        } catch (Exception e) {
            logger.error("删除热源异常" + e.getMessage());
            jo.put(Constants.MSG, "删除热源失败");
        }
        return jo.toJSONString();
    }
}
