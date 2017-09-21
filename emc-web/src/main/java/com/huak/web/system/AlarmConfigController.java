package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.health.AlarmConfigService;
import com.huak.health.model.AlarmConfig;
import com.huak.health.vo.AlarmConfigVO;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.web.home.BaseController;
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
 * File name:  com.huak.web.system<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/8<BR>
 * Description: 报警配置    <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/alarm/config")
public class AlarmConfigController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String COMPANY = "company";
    private static final String ORG = "org";
    @Resource
    private AlarmConfigService alarmConfigService;

    @RequestMapping(method = RequestMethod.GET)
    public String page(HttpServletRequest request,Model model){
        logger.info("打开报警配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(COMPANY,company);
        model.addAttribute(ORG,org);
        return "system/alarm/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPage(HttpServletRequest request,Model model){
        logger.info("打开添加报警配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(COMPANY,company);
        model.addAttribute(ORG,org);
        return "system/alarm/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(AlarmConfig alarmConfig, HttpServletRequest request) {
        logger.info("添加报警配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute(Constants.SESSION_KEY);

            alarmConfig.setId(UUIDGenerator.getUUID());
            alarmConfigService.insertSelective(alarmConfig);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加报警配置成功");
        } catch (Exception e) {
            logger.error("添加报警配置异常" + e.getMessage());
            jo.put(Constants.MSG, "添加报警配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("报警配置列表分页查询");
        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, alarmConfigService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("报警配置列表分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editPage(HttpServletRequest request,Model model,@PathVariable("id") String id){
        logger.info("打开修改报警配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);

        AlarmConfigVO alarmConfig = alarmConfigService.selectUpdateMap(id);
        model.addAttribute("company",company);
        model.addAttribute("alarmConfig",alarmConfig);
        return "system/alarm/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(AlarmConfig alarmConfig) {
        logger.info("修改报警配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            alarmConfigService.updateByPrimaryKeySelective(alarmConfig);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改报警配置成功");
        } catch (Exception e) {
            logger.error("修改报警配置异常" + e.getMessage());
            jo.put(Constants.MSG, "修改报警配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        logger.info("删除报警配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            alarmConfigService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除报警配置成功");
        } catch (Exception e) {
            logger.error("删除报警配置异常" + e.getMessage());
            jo.put(Constants.MSG, "删除报警配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/type", method = RequestMethod.POST)
    @ResponseBody
    public String checkType(@RequestParam Map<String, Object> paramsMap) {
        logger.info("同一用能单位报警类型唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
//            Long num = alarmConfigService.checkType(paramsMap);
//            if (num == 0) {
//                jo.put(Constants.FLAG, true);
//            }
        } catch (Exception e) {
            logger.error("同一用能单位报警类型唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
