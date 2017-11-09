package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.Employee;
import com.huak.auth.model.Role;
import com.huak.common.Constants;
import com.huak.common.page.Page;
import com.huak.home.workorder.WorkOrderInfoService;
import com.huak.home.workorder.WorkOrderRecordService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.workorder.model.WorkOrderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
* Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
* ProjectName:emc<BR>
* File name:  com.huak.web.system<BR>
* Author:  Administrator  <BR>
* Project:emc    <BR>
* Version: v 1.0      <BR>
* Date: 2017-10-26<BR>
* Description: 工单信息    <BR>
* Function List:  <BR>
*/
@Controller
@RequestMapping("/work/order/info")
public class WorkOrderInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private WorkOrderInfoService workOrderInfoService;
    @Resource
    private WorkOrderRecordService workOrderRecordService;

    @Value("${work.order.creator}")
    private String creator;
    @Value("${work.order.monitor}")
    private String monitor;
    @Value("${work.order.takor}")
    private String takor;

    private static final String COMPANY = "company";
    private static final String ROLE = "role";
    private static final String EMPLOYEE = "employee";

    @RequestMapping(method = RequestMethod.GET)
    public String page(HttpServletRequest request, Model model) {
        logger.info("打开工单管理页");
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        Role role = (Role) session.getAttribute(Constants.SESSION_ROLE_KEY);
        Employee employee = (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);

        model.addAttribute(COMPANY, company);
        model.addAttribute(ROLE, role);
        model.addAttribute(EMPLOYEE, employee);
        return "system/workorder/list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model, Page page) {
        logger.info("打开工单管理页");
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        Role role = (Role) session.getAttribute(Constants.SESSION_ROLE_KEY);
        Employee employee = (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);
        Map<String, Object> map = new HashMap<String, Object>();

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST,workOrderInfoService.selectWorkOrderInfo(map, page));
        } catch (Exception e) {
            logger.error("计量器具列表页分页查询异常" + e.getMessage());
        }
        jo.put(COMPANY, company);
        jo.put(ROLE, role);
        jo.put(EMPLOYEE, employee);
        return jo.toJSONString();
    }

    @RequestMapping(value = "/send",method = RequestMethod.GET)
    @ResponseBody
    public String sendOrder(HttpServletRequest request,Model model,
                            @RequestBody WorkOrderInfo info){
        logger.info("班长发送工单到接单员");
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
        WorkOrderInfo workOrderInfo = new WorkOrderInfo();
        int i =workOrderInfoService.sendABorCRecord(workOrderInfo);
        if(i>0){
            jo.put(Constants.FLAG,true);
            jo.put(Constants.MSG,"操作成功");
        }else {
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"操作失败");
        }
        return jo.toJSONString();
    }


    @RequestMapping(value = "/back",method = RequestMethod.GET)
    @ResponseBody
    public String backOrder(HttpServletRequest request,Model model,
                            @RequestBody WorkOrderInfo info){
        logger.info("接单员退回工单到派单员");
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
        WorkOrderInfo workOrderInfo = new WorkOrderInfo();
        int i = workOrderInfoService.backARecord(workOrderInfo);
        if(i>0){
            jo.put(Constants.FLAG,true);
            jo.put(Constants.MSG,"操作成功");
        }else {
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"操作失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/finish",method = RequestMethod.GET)
    @ResponseBody
    public String finishOrder(HttpServletRequest request,Model model,
                              @RequestBody WorkOrderInfo info){
        logger.info("接单员端确认并且完成");
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
        WorkOrderInfo workOrderInfo = new WorkOrderInfo();
        int i  = workOrderInfoService.finishCRecord(workOrderInfo);
        if(i>0){
            jo.put(Constants.FLAG,true);
            jo.put(Constants.MSG,"操作成功");
        }else {
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"操作失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/confirm",method = RequestMethod.GET)
    @ResponseBody
    public String confirmOrder(HttpServletRequest request,Model model,
                               @RequestBody WorkOrderInfo info){
        logger.info("接端确认并且完成等待派单员确认完成且派单员确认完成");
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
        WorkOrderInfo workOrderInfo = new WorkOrderInfo();
        int i = workOrderInfoService.confirmACRecord(workOrderInfo);
        if(i>0){
            jo.put(Constants.FLAG,true);
            jo.put(Constants.MSG,"操作成功");
        }else {
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"操作失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/resetBack",method = RequestMethod.GET)
    @ResponseBody
    public String resetBackOrder(HttpServletRequest request,Model model,
                                 @RequestBody WorkOrderInfo info){

        logger.info("派单员关闭退单，且派单员重新发送到班长");
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);
        WorkOrderInfo workOrderInfo = new WorkOrderInfo();
        int i = workOrderInfoService.resetBackABCRecord(workOrderInfo);
        if(i>0){
            jo.put(Constants.FLAG,true);
            jo.put(Constants.MSG,"操作成功");
        }else {
            jo.put(Constants.FLAG,false);
            jo.put(Constants.MSG,"操作失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(WorkOrderInfo workOrderInfo, HttpServletRequest request) {
        logger.info("添加工单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            HttpSession session = request.getSession();
            Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
            Employee emp = (Employee)session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);
            workOrderInfo.setComid(company.getId());
            workOrderInfo.setCreator(emp.getId());
            workOrderInfoService.saveA(workOrderInfo);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加工单成功");
        } catch (Exception e) {
            logger.error("添加工单异常" + e.getMessage());
            jo.put(Constants.MSG, "添加工单失败");
        }
        return jo.toJSONString();
    }
}
