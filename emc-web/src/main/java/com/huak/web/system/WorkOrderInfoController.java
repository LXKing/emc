package com.huak.web.system;

import com.huak.auth.model.Employee;
import com.huak.auth.model.Role;
import com.huak.common.Constants;
import com.huak.home.workorder.WorkOrderInfoService;
import com.huak.home.workorder.WorkOrderRecordService;
import com.huak.org.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


}
