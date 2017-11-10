package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.UserService;
import com.huak.auth.model.Employee;
import com.huak.auth.model.Role;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.common.StringUtils;
import com.huak.common.page.Page;
import com.huak.home.workorder.WorkOrderInfoService;
import com.huak.home.workorder.WorkOrderRecordService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.workorder.model.WorkOrderInfo;
import com.huak.workorder.type.WorkOrderStatus;
import com.huak.workorder.vo.WorkOrderInfoDetail;
import com.huak.workorder.vo.WorkOrderInfoRel;
import com.huak.workorder.vo.WorkOrderRecordA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
    @Resource
    private UserService userService;

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
        Employee employee = (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);
        model.addAttribute(COMPANY, company);
        model.addAttribute(EMPLOYEE, employee);
        User user = (User)session.getAttribute(Constants.SESSION_KEY);
        Role role=userService.getRole(user.getId());
            model.addAttribute("employee_id",employee.getId());
            if(creator.equals(role.getId())){
                model.addAttribute("roleType",1);
            }else if(monitor.equals(role.getId())){
                model.addAttribute("roleType",2);
            }else if(takor.equals(role.getId())){
                model.addAttribute("roleType",3);
            }
        return "system/workorder/list";
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public String list(HttpServletRequest request, Page page) {
        logger.info("工单分页查询");
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        User user = (User)session.getAttribute(Constants.SESSION_KEY);
        Employee employee = (Employee) session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);
        Role role=userService.getRole(user.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject jo = new JSONObject();
        try {
            map.put("employee_id",employee.getId());
            if(creator.equals(role.getId())){
                jo.put(Constants.LIST, workOrderInfoService.selectWorkOrderInfoByCreator(map, page));
            }else if(monitor.equals(role.getId())){
                jo.put(Constants.LIST, workOrderInfoService.selectWorkOrderInfoByMonitor(map, page));
            }else if(takor.equals(role.getId())){
                jo.put(Constants.LIST, workOrderInfoService.selectWorkOrderInfoByTakor(map, page));
            }
        } catch (Exception e) {
            logger.error("工单分页查询异常" + e.getMessage());
        }
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
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPage1(HttpServletRequest request,Model model){
        logger.info("打开室温添加配置页");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("monitor",monitor);
        map.put("receiver",takor);
        List<Map<String,Object>> list = workOrderInfoService.getEmployee(map);
        model.addAttribute("list",list);
        return "system/workorder/add";
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


    @RequestMapping(value = "/detail/{type}/{code}",method = RequestMethod.GET)
    public String detailPage(HttpServletRequest request, Model model,@PathVariable("code") String code,@PathVariable("type") Integer type) {
        logger.info("打开工单详情页");
        //根据code查询工单信息
        WorkOrderInfoDetail detail = workOrderInfoService.getWorkInfoByCode(code);

        model.addAttribute("detail", detail);
        model.addAttribute("roleType", type);

        return "system/workorder/detail";
    }

    @RequestMapping(value = "/record/{code}", method = RequestMethod.POST)
    @ResponseBody
    public String getRecord(@PathVariable("code") String code) {
        logger.info("查询工单操作记录");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            //根据code查询工单操作记录
            List<WorkOrderRecordA> records = workOrderRecordService.selectAllRecord(code);
            jo.put(Constants.LIST,records);
            jo.put(Constants.FLAG, true);
        } catch (Exception e) {
            logger.error("查询工单操作记录异常" + e.getMessage());
            jo.put(Constants.MSG, "查询工单操作记录失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/rel/{code}", method = RequestMethod.POST)
    @ResponseBody
    public String getRel(@PathVariable("code") String code) {
        logger.info("查询工单关联记录");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            List<WorkOrderInfoRel> rels = null;
            //根据code查询关联工单信息列表
            String pCode = workOrderInfoService.selectByCode(code);
            if(!StringUtils.isEmpty(pCode)){
                rels = workOrderInfoService.selectWorkRelByCode(pCode);
            }
            jo.put(Constants.LIST, rels);
            jo.put(Constants.FLAG, true);
        } catch (Exception e) {
            logger.error("查询工单关联记录异常" + e.getMessage());
            jo.put(Constants.MSG, "查询工单关联记录失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    @ResponseBody
    public String close(WorkOrderInfo workOrderInfo, HttpServletRequest request) {
        logger.info("关闭工单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            //查询工单当前信息
            workOrderInfo = workOrderInfoService.selectByPrimaryKey(workOrderInfo.getId());
            if(WorkOrderStatus.B212.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.closeAB(workOrderInfo);
            }else if(WorkOrderStatus.C312.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.closeABC(workOrderInfo);
            }else if(WorkOrderStatus.C321.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.closeAC(workOrderInfo);
            }else{
                throw new IllegalArgumentException("没有此流程");
            }

            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "关闭工单成功");
        } catch (Exception e) {
            logger.error("关闭工单异常" + e.getMessage());
            jo.put(Constants.MSG, "关闭工单失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public String confirm(WorkOrderInfo workOrderInfo, HttpServletRequest request) {
        logger.info("确认工单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            //查询工单当前信息
            workOrderInfo = workOrderInfoService.selectByPrimaryKey(workOrderInfo.getId());
            if(WorkOrderStatus.B213.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmAB(workOrderInfo);
            }else if(WorkOrderStatus.C311.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmACRecord(workOrderInfo);
            }else if(WorkOrderStatus.C323.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmAC(workOrderInfo);
            }else{
                throw new IllegalArgumentException("没有此流程");
            }

            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "确认工单成功");
        } catch (Exception e) {
            logger.error("确认工单异常" + e.getMessage());
            jo.put(Constants.MSG, "确认工单失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public String reset(WorkOrderInfo workOrderInfo, HttpServletRequest request) {
        logger.info("重新派送工单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            if(WorkOrderStatus.C323.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmAB(workOrderInfo);
            }else if(WorkOrderStatus.C311.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmACRecord(workOrderInfo);
            }else if(WorkOrderStatus.C323.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmAC(workOrderInfo);
            }else if(WorkOrderStatus.C311.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmACRecord(workOrderInfo);
            }else if(WorkOrderStatus.C311.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmACRecord(workOrderInfo);
            }else if(WorkOrderStatus.C311.getKey() == workOrderInfo.getStatus()){
                workOrderInfoService.confirmACRecord(workOrderInfo);
            }else{
                throw new IllegalArgumentException("没有此流程");
            }

            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "重新派送工单成功");
        } catch (Exception e) {
            logger.error("重新派送工单异常" + e.getMessage());
            jo.put(Constants.MSG, "重新派送工单失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(WorkOrderInfo workOrderInfo, HttpServletRequest request,
                      @RequestParam  String urlType) {
        logger.info("添加工单");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        String[]  ss = workOrderInfo.getTakor().split(",");
        if(urlType.equals("0")){
            try {
                HttpSession session = request.getSession();
                Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
                Employee emp = (Employee)session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);
                workOrderInfo.setComid(company.getId());
                workOrderInfo.setCreator(emp.getId());
                if(monitor.equals(ss[1])){
                    //班长
                    workOrderInfo.setMonitor(ss[0]);
                }
                if(takor.equals(ss[1])){
                    //接单员
                    workOrderInfo.setTakor(ss[0]);
                }
                workOrderInfo.setTakor(ss[0]);
                workOrderInfoService.saveA(workOrderInfo);
                jo.put(Constants.FLAG, true);
                jo.put(Constants.MSG, "添加工单成功");
            } catch (Exception e) {
                logger.error("添加工单异常" + e.getMessage());
                jo.put(Constants.MSG, "添加工单失败");
            }
        }else if(urlType.equals("1")){
            try {
                HttpSession session = request.getSession();
                Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
                Employee emp = (Employee)session.getAttribute(Constants.SESSION_EMPLOYEE_KEY);
                workOrderInfo.setComid(company.getId());
                workOrderInfo.setCreator(emp.getId());
                if(monitor.equals(ss[1])){
                    //班长
                    workOrderInfo.setMonitor(ss[0]);
                    workOrderInfoService.saveAndSendABorC(workOrderInfo);
                }
                if(takor.equals(ss[1])){
                    //接单员
                    workOrderInfo.setTakor(ss[0]);
                    workOrderInfoService.saveAndSendAC(workOrderInfo);
                }
                jo.put(Constants.FLAG, true);
                jo.put(Constants.MSG, "添加且发送工单成功");
            } catch (Exception e) {
                logger.error("添加且发送工单异常" + e.getMessage());
                jo.put(Constants.MSG, "添加且发送工单失败");
            }
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String addPage1(HttpServletRequest request,Model model,
                           @RequestParam("code")  String code,
                           @RequestParam("mid")  String mid,
                           @RequestParam("reid")  String reid){
        logger.info("派单并编辑当前订单");
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("monitor",monitor);
        map1.put("receiver",takor);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("monitor",monitor);
        map.put("receiver",takor);
        if(mid!=null&&mid!=""){
            map.put("id",mid);
        }
        if(reid!=null&&reid!=""){
            map.put("id",reid);
        }
        List<Map<String,Object>> list = workOrderInfoService.getEmployee(map1);

        List<Map<String,Object>> listemp =workOrderInfoService.getEmployeeById(map);

        WorkOrderInfoDetail detail = workOrderInfoService.getWorkInfoByCode(code);

        model.addAttribute("list",list);
        model.addAttribute("listemp",listemp);
        model.addAttribute("detail",detail);
        return "system/workorder/edit";
    }
}
