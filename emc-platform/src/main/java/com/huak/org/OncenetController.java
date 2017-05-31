package com.huak.org;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.common.page.Page;
import com.huak.org.model.Oncenet;
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
 * Date: 2017/5/17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/oncenet")
public class OncenetController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private OncenetService  oncenetService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统管网列表页");
        return "/org/onenet/list";
    }
    @RequestMapping(value = "/listpage", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("一次管网列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, oncenetService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("一次管网列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage() {
        return "/org/onenet/add";
    }

    @RequestMapping(value = "/addvalue", method = RequestMethod.POST)
    @ResponseBody
    public String add(Oncenet oncenet, HttpServletRequest request) {
        logger.info("添加管网");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();
            oncenetService.insertSelective(oncenet);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加管网成功");
        } catch (Exception e) {
            logger.error("添加管网异常" + e.getMessage());
            jo.put(Constants.MSG, "添加管网失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改热源页");
        try {
            Oncenet oncenet = oncenetService.selectByPrimaryKey(id);
            model.addAttribute("oncenet", oncenet);
        } catch (Exception e) {
            logger.error("跳转修改热源页异常" + e.getMessage());
        }
        return "/org/onenet/edit";
    }

    @RequestMapping(value = "/editvalue", method = RequestMethod.POST)
    @ResponseBody
    public String editValue(Oncenet oncenet) {
        logger.info("修改管网");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int i = oncenetService.updateByPrimaryKeySelective(oncenet);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改管网成功");
        } catch (Exception e) {
            logger.error("修改管网异常" + e.getMessage());
            jo.put(Constants.MSG, "修改管网失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteOncenet(@PathVariable("id") String id) {
        logger.info("删除管网");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            oncenetService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除管网成功");
        } catch (Exception e) {
            logger.error("删除管网异常" + e.getMessage());
            jo.put(Constants.MSG, "删除管网失败");
        }
        return jo.toJSONString();
    }
}
