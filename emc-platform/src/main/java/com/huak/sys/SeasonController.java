package com.huak.sys;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Org;
import com.huak.sys.SeasonService;
import com.huak.season.model.Season;
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
 * Date: 2017/5/18<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/season")
public class SeasonController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
      @Resource
      private SeasonService seasonService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统采暖季列表页");
        return "/sys/season/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("采暖季列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            PageResult<Season> result = seasonService.queryByPage(paramsMap, page);
            jo.put(Constants.LIST, result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("采暖季列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage() {
        return "/sys/season/add";
    }

    @ResponseBody
    @RequestMapping(value = "/checkname", method = RequestMethod.POST)
    public String checkNode(@RequestParam  String name ){

        JSONObject jo = new JSONObject();
        boolean  flag =   seasonService.checkName(name);

        if(flag){
            jo.put(Constants.FLAG,false);
        }else{
            jo.put(Constants.FLAG, true);
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/addvalue", method = RequestMethod.POST)
    public String addNodeValue(@RequestParam  String name,
                               @RequestParam  String sdate,
                               @RequestParam  String edate, HttpServletRequest request){
        logger.info("添加采暖");
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();
            Season season = new Season();
            season.setId(UUIDGenerator.getUUID());
            season.setComid("012");
            season.setName(name);
            season.setSdate(sdate);
            season.setEdate(edate);
            seasonService.insert(season);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加采暖成功");
        } catch (Exception e) {
            logger.error("添加采暖异常" + e.getMessage());
            jo.put(Constants.MSG, "添加采暖失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改采暖页");
        try {
            model.addAttribute("season", seasonService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("跳转修改采暖页异常" + e.getMessage());
        }
        return "/sys/season/edit";
    }
    @ResponseBody
    @RequestMapping(value = "/editvalue", method = RequestMethod.POST)
    public String editValue(@RequestParam  String name,
                            @RequestParam  String sdate,
                            @RequestParam  String edate,
                            @RequestParam  String id,
                            HttpServletRequest request){
        logger.info("修改采暖");
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();
            Season season = new Season();
            season.setId(id);
            season.setComid("013");
            season.setName(name);
            season.setSdate(sdate);
            season.setEdate(edate);
            seasonService.updateByPrimaryKeySelective(season);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改采暖成功");
        } catch (Exception e) {
            logger.error("修改采暖异常" + e.getMessage());
            jo.put(Constants.MSG, "修改采暖失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFeed(@PathVariable("id") String id) {
        logger.info("删除采暖");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            seasonService.delete(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除采暖成功");
        } catch (Exception e) {
            logger.error("删除采暖异常" + e.getMessage());
            jo.put(Constants.MSG, "删除采暖失败");
        }
        return jo.toJSONString();
    }
}
