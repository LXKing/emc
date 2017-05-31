package com.huak.org;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.sys.model.SysDic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak<BR>
 * Author:  liurm  <BR>
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
        return "org/orgtree/list";
    }
    @ResponseBody
    @RequestMapping(value = "/checknode", method = RequestMethod.POST)
    public String checkNode(@RequestParam  String orgName ){

        JSONObject jo = new JSONObject();
        boolean  flag =   orgService.checkOrgName(orgName);

          if(flag){
              jo.put(Constants.FLAG,false);
          }else{
              jo.put(Constants.FLAG, true);
          }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/addnode/{id}", method = RequestMethod.GET)
    public String addNode(Model model, @PathVariable("id") String id){
        String code = "orgType";
        List<Company> company = orgService.selectCompanyAll();
        List<SysDic> dic = orgService.selectSysDicAll(code);
        model.addAttribute("id",id);
        model.addAttribute("company",company);
        model.addAttribute("sysdic",dic);
        return "org/orgtree/add";
    }
    @RequestMapping(value = "/editnode/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改机构页");
        try {
            String code = "orgType";
            List<Company> company = orgService.selectCompanyAll();
            List<SysDic> dic = orgService.selectSysDicAll(code);
            model.addAttribute("company",company);
            model.addAttribute("sysdic",dic);
            model.addAttribute("org", orgService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("跳转修改机构页异常" + e.getMessage());
        }
        return "/org/orgtree/edit";
    }
    @ResponseBody
    @RequestMapping(value = "/tree/edit", method = RequestMethod.POST)
    public String edit(Org org , HttpServletRequest request) {
        logger.info("修改机构");

        JSONObject jo = new JSONObject();
        try {
            boolean flag = orgService.updateOrg(org);
            if(flag){
                jo.put(Constants.FLAG, true);
                jo.put(Constants.MSG, "修改机构成功");
            }else{
                jo.put(Constants.FLAG, false);
                jo.put(Constants.MSG, "修改机构失败");
            }
        } catch (Exception e) {
            logger.error("修改机构异常" + e.getMessage());
            jo.put(Constants.MSG, "修改机构失败");
        }
        return jo.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/addnodevalue", method = RequestMethod.POST)
    public String addNodeValue(Org org,HttpServletRequest request){
        logger.info("添加机构");
        com.alibaba.fastjson.JSONObject jo = new com.alibaba.fastjson.JSONObject();
        jo.put(Constants.FLAG, false);
            try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();
           User user =  (User)session.getAttribute(Constants.SESSION_KEY);
            org.setCreator(user.getId());
            boolean flag = orgService.insertOrg(org);
            jo.put(Constants.FLAG, flag);
            jo.put(Constants.MSG, "添加机构成功");
        } catch (Exception e) {
            logger.error("添加机构异常" + e.getMessage());
            jo.put(Constants.MSG, "添加机构失败");
        }
        return jo.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/ztreeValue", method = RequestMethod.GET)
    public Object ztree(Model model,HttpServletResponse response){
        System.out.print("-------------------controller----------------------------");
        List<Org> as = orgService.selectOrgAll();
        for (int i=0;i<as.size();i++) {

            System.out.println(as.get(i).getOrgName());

        }
        return JSON.toJSON(as);
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feed(){
        return "org/feed/list";
    }

    @RequestMapping(value = "/node", method = RequestMethod.GET)
    public String node(){
        return "org/node/list";
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteNode(@RequestParam  String ids ){
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {

            boolean  flag =   orgService.delete(ids);
              if(flag){
                  jo.put(Constants.FLAG, flag);
                  jo.put(Constants.MSG, "删除机构成功");
              }else{
                  jo.put(Constants.FLAG, flag);
                  jo.put(Constants.MSG, "删除机构失败");
              }

        } catch (Exception e) {
            logger.error("删除机构异常" + e.getMessage());
            jo.put(Constants.MSG, "删除机构失败");
        }
        return jo.toJSONString();
    }


}
