package com.huak.auth;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.service.RoleService;
import com.huak.common.Constants;
import com.huak.common.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
 * Date: 2016/8/30<BR>
 * Description:   角色  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listPage(ModelMap modelMap){
        logger.info("转至系统角色列表页");
        modelMap.put("demo","demo");
        return "/auth/role/role_list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String,String> paramsMap,Page page){
        logger.info("角色列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, roleService.findByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("角色列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") Long id){
        logger.info("跳转修改角色页");
        try {
            model.addAttribute("role",roleService.getRole(id));
        } catch (Exception e) {
            logger.error("跳转修改角色页异常" + e.getMessage());
        }
        return "/auth/role/role_edit";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @ResponseBody
    public String edit(@RequestParam Map<String,String> paramsMap){
        logger.info("修改角色");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            roleService.editRole(paramsMap);
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"修改角色成功");
        } catch (Exception e) {
            logger.error("修改角色异常" + e.getMessage());
            jo.put(Constants.MSG ,"修改角色失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPage() {
        return "/auth/role/role_add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestParam Map<String,String> paramsMap){
        logger.info("添加角色");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            roleService.addRole(paramsMap);
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"添加角色成功");
        } catch (Exception e) {
            logger.error("添加角色异常" + e.getMessage());
            jo.put(Constants.MSG ,"添加角色失败");
        }
        return jo.toJSONString();
    }

    /**
     * 为什么用POST请求
     * 因为DELETE请求接收不到数组 fuck
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public String deleteRoles(String ids){
        logger.info("批量删除角色");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            roleService.deleteRoles(ids);
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"删除角色成功");
        } catch (Exception e) {
            logger.error("删除角色异常" + e.getMessage());
            jo.put(Constants.MSG ,"删除角色失败");
        }
        return jo.toJSONString();
    }
    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteRole(@PathVariable("id") Long id){
        logger.info("删除角色");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            roleService.deleteRoles(id.toString());
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"删除角色成功");
        } catch (Exception e) {
            logger.error("删除角色异常" + e.getMessage());
            jo.put(Constants.MSG ,"删除角色失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    @ResponseBody
    public String checkUnique(@RequestParam Map<String,String> paramsMap){
        logger.info("角色名称唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);
        try {
            Long num = roleService.checkRoleName(paramsMap);
            if(num==0){
                jo.put(Constants.FLAG ,true);
            }
        }catch (Exception e){
            logger.error("角色名称唯一性校验异常"+e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    public String authPage(Model model,Long id){
        logger.info("跳转角色授权页");
        try {
            model.addAttribute("role",roleService.getRole(id));
            model.addAttribute("auth",roleService.getAuth(id));
        } catch (Exception e) {
            logger.error("跳转角色授权页异常"+e.getMessage());
        }
        return "/auth/role/role_auth";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public String auth(Long roleId, String funcIds){
        logger.info("角色授权");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG ,false);

        try{
            String[] fids = funcIds.split(",");
            roleService.updAuth(roleId, fids);
            jo.put(Constants.FLAG ,true);
            jo.put(Constants.MSG ,"角色授权成功");
        }catch (Exception e){
            logger.error("角色授权异常" + e.getMessage());
            jo.put(Constants.MSG ,"角色授权异常");
        }

        return jo.toJSONString();
    }


    @RequestMapping(value = "/user/select",method = RequestMethod.PATCH)
    @ResponseBody
    public String userSelect(@RequestParam Map<String,String> paramsMap,Page page){
        logger.info("选择角色列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, roleService.findSelectByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("选择角色列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
