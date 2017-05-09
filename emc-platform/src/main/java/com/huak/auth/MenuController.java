
package com.huak.auth;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.common.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
 * Date: 2016/8/31<BR>
 * Description:  系统菜单   <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/menu")
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MenuService menuService;
//    @Resource
//    private FunctionInitBean functionInitBean;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统菜单列表页");
        return "auth/menu/list";
    }


    /**
     * 初始化
     *
     * @param paramsMap
     * @return
     */

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String list(@RequestParam Map<String, String> paramsMap) {
        logger.info("菜单列表页查询");
        Page page = new Page();
        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, menuService.queryByPage(page));
        } catch (Exception e) {
            logger.error("菜单列表页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

//    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
//    public String editPage(Model model, @PathVariable("id") Long id){
//        logger.info("跳转菜单编辑页");
//        try {
//            Map<String,String> menu = menuService.getMenu(id);
//            model.addAttribute("menu",menu);
//            model.addAttribute("pmenu",menuService.getMenu(Long.valueOf(menu.get("p_menu_id"))));
//        } catch (Exception e) {
//            logger.error("跳转菜单编辑页异常" + e.getMessage());
//        }
//        return "/auth/menu/menu_edit";
//    }
//
//    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
//    @ResponseBody
//    public String edit(@RequestParam Map<String,String> paramsMap){
//        logger.info("修改菜单");
//
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG ,false);
//        try {
//            menuService.editMenu(paramsMap);
//            jo.put(Constants.DATA,menuService.getMenu(Long.valueOf(paramsMap.get("menuId"))));
//            jo.put(Constants.FLAG ,true);
//            jo.put(Constants.MSG ,"修改菜单成功");
//            //重新加载缓存
//            functionInitBean.afterPropertiesSet();
//        } catch (Exception e) {
//            logger.error("修改菜单异常" + e.getMessage());
//            jo.put(Constants.MSG ,"修改菜单失败");
//        }
//        return jo.toJSONString();
//    }
//
//    @RequestMapping(value = "/add",method = RequestMethod.GET)
//    public String addPage(Long pid, Model model) {
//        logger.info("跳转菜单添加页");
//        try {
//            model.addAttribute("pmenu",menuService.getMenu(pid));
//        } catch (Exception e) {
//            logger.error("跳转菜单添加页异常" + e.getMessage());
//        }
//        return "/auth/menu/menu_add";
//    }
//
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    @ResponseBody
//    public String add(@RequestParam Map<String,String> paramsMap){
//        logger.info("添加菜单");
//
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG ,false);
//        try {
//            Long id = menuService.addMenu(paramsMap);
//            jo.put(Constants.DATA,menuService.getMenu(id));
//            jo.put(Constants.FLAG ,true);
//            jo.put(Constants.MSG ,"添加菜单成功");
//            //重新加载缓存
//            functionInitBean.afterPropertiesSet();
//        } catch (Exception e) {
//            logger.info("添加菜单异常" + e.getMessage());
//            jo.put(Constants.MSG ,"添加菜单失败");
//        }
//        return jo.toJSONString();
//    }


/**
 * @param id
 * @return
 *//*

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMenu(@PathVariable("id") Long id){
        logger.info("删除菜单");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            menuService.deleteMenues(id.toString());
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"删除菜单成功");
            //重新加载缓存
            functionInitBean.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("删除菜单异常" + e.getMessage());
            jo.put(Constants.MSG ,"删除菜单失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    @ResponseBody
    public String checkUnique(@RequestParam Map<String,String> paramsMap){
        logger.info("菜单唯一标识唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            Long num = menuService.checkMenuName(paramsMap);
            if(num==0){
                jo.put(Constants.FLAG ,true);
            }
        }catch (Exception e){
            logger.error("菜单唯一标识唯一性校验异常"+e.getMessage());
        }
        return jo.toJSONString();
    }
}
*/
}