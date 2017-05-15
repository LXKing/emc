
package com.huak.auth;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.Menu;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/listTree", method = RequestMethod.GET)
    @ResponseBody
    public  List<Map<String,Object>> list(@RequestParam Map<String, String> paramsMap) {
        logger.info("菜单列表页查询");
        Page page = new Page();
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> data = null;
        try {
            data = menuService.selectTree(paramsMap);

        } catch (Exception e) {
            logger.error("菜单列表页查询异常" + e.getMessage());
        }
        return data;
    }

    /**
     *转至系统菜添加页面
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam Map<String, Object> paramsMap, Model model) {
        model.addAttribute("pId",paramsMap.get("pId"));
        model.addAttribute("menuType",paramsMap.get("menuType"));
        logger.info("转至系统菜单列表页");
        return "auth/menu/add";
    }


    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String checkUnique(@RequestParam Map<String, String> paramsMap) {
        logger.info("菜单名称唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int num = menuService.checkMenuName(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("菜单名称唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Menu menu,HttpServletRequest request) {
        logger.info("添加菜单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();
            Map<String,String> user = (Map<String, String>) session.getAttribute(Constants.SESSION_KEY);
            menu.setCreator(user.get("1"));
            menu.setId(UUIDGenerator.getUUID());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date();
            String nowTime = sdf.format(date);
            menu.setCreatTime(nowTime);
            menuService.insert(menu);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加菜单成功");
        } catch (Exception e) {
            logger.error("添加菜单异常" + e.getMessage());
            jo.put(Constants.MSG, "添加菜单失败");
        }
        return jo.toJSONString();
    }

}