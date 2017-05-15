
package com.huak.auth;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/9/29<BR>
 * Description:  功能控制器   <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/func")
public class FuncController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MenuService menuService;
    @Resource
    private FuncService funcService;

/*
    @Resource
    private FunctionInitBean functionInitBean;*/

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listPage(){
        logger.info("转至系统功能列表页");

        return "/auth/func/list";
    }


/**
     * 初始化
     * @param paramsMap
     * @return
     */

    @RequestMapping(value = "/list",method = RequestMethod.PATCH)
    @ResponseBody
    public String list(Map<String,Object> paramsMap){
        logger.info("功能列表页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, funcService.selectAllByMap(paramsMap));
        } catch (Exception e) {
            logger.error("功能列表页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") Long id){
        logger.info("跳转功能编辑页");
        try {
           // Map<String,String> function = functionService.getFunction(id);
            //model.addAttribute("function",function);
           // model.addAttribute("menu",menuService.getMenu(Long.valueOf(function.get("menu_id"))));
        } catch (Exception e) {
            logger.error("跳转功能编辑页异常" + e.getMessage());
        }
        return "/auth/func/edit";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @ResponseBody
    public String edit(@RequestParam Map<String,String> paramsMap){
        logger.info("修改功能");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
           // functionService.editFunction(paramsMap);
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"修改功能成功");
            //重新加载缓存
           // functionInitBean.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("修改功能异常" + e.getMessage());
            jo.put(Constants.MSG ,"修改功能失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPage(Long menuId, Model model) {
        logger.info("跳转功能添加页");
        try {
            //model.addAttribute("menu",menuService.getMenu(menuId));
        } catch (Exception e) {
            logger.error("跳转功能添加页异常" + e.getMessage());
        }
        return "/auth/func/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestParam Map<String,String> paramsMap){
        logger.info("添加功能");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
           // functionService.addFunction(paramsMap);
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"添加功能成功");
            //重新加载缓存
            //functionInitBean.afterPropertiesSet();
        } catch (Exception e) {
            logger.info("添加功能异常" + e.getMessage());
            jo.put(Constants.MSG ,"添加功能失败");
        }
        return jo.toJSONString();
    }


/**
     * @param ids
     * @return
     */

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public String deleteRoles(String ids){
        logger.info("批量删除功能");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
           // functionService.deleteFunctions(ids);
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"删除功能成功");
            //重新加载缓存
            //functionInitBean.afterPropertiesSet();
        } catch (Exception e) {
            logger.info("删除功能异常" + e.getMessage());
            jo.put(Constants.MSG ,"删除功能失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/uname",method = RequestMethod.POST)
    @ResponseBody
    public String checkUnique(@RequestParam Map<String,String> paramsMap){
        logger.info("菜单唯一标识唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            //Long num = functionService.checkUName(paramsMap);
            //if(num==0){
                jo.put(Constants.FLAG ,true);
            //}
        }catch (Exception e){
            logger.error("菜单唯一标识唯一性校验异常"+e.getMessage());
        }
        return jo.toJSONString();
    }
}

