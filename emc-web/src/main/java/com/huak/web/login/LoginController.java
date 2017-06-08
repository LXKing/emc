package com.huak.web.login;

import com.huak.auth.UserService;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.org.CompanyService;
import com.huak.org.OrgService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.login<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/23<BR>
 * Description:  登录   <BR>
 * Function List:  <BR>
 */
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;
    @Resource
    private CompanyService companyService;
    @Resource
    private OrgService orgService;

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(HttpServletRequest request){
        logger.info("跳转首页");

        //todo 放一个假用户
        //todo 放一个假公司
        //todo 放一个假组织
        String zhangsan = "a9e246fa035d42b6adcbedccb9cbbf1d";


        try {
            User user = userService.getUser(zhangsan);
            Org org = orgService.selectByPrimaryKey(user.getOrgId());
            Company company = companyService.selectByPrimaryKey(org.getComId());
            request.getSession().setAttribute(Constants.SESSION_KEY, user);
            request.getSession().setAttribute(Constants.SESSION_ORG_KEY, org);
            request.getSession().setAttribute(Constants.SESSION_COM_KEY, company);
        } catch (Exception e) {
            logger.error("跳转首页异常");
        }
        return "index";
    }
}
