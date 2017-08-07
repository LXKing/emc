package com.huak.web.home;

import com.huak.common.Constants;
import com.huak.home.type.ToolVO;
import com.huak.org.OrgService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/4<BR>
 * Description:  父控制器   <BR>
 * Function List:  <BR>
 */
@Controller
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private OrgService orgService;

    /**
     * 集团下分公司统一条件封装
     * @param toolVO
     * @param request
     * @return
     */
    protected Map<String, Object> paramsPackageFgs(ToolVO toolVO, HttpServletRequest request){
        /*封装条件*/
        Map params = new HashMap<String, Object>();
        HttpSession session = request.getSession();

        try{
            //能耗数据表名称
            if(null != session){
                Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
                params.put("tableName",company.getTableName());
            }
            //查询数据条件
            Org org = orgService.selectByPrimaryKey(toolVO.getToolOrgId());
            if(org.getpOrgId()==0){
                params.put("pOrgId",org.getId());
            }else{
                params.put("orgId",org.getId());
            }
            params.put("comId",org.getComId());
            params.put("feedType",toolVO.getToolFeedType());
            params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
            params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
            params.put("startTimeTq",toolVO.getToolStartDateTq()+" 00:00:00");
            params.put("endTimeTq",toolVO.getToolEndDateTq()+" 23:59:59");
            params.put("orgType",toolVO.getToolOrgType());
        }catch (Exception e){
            logger.error("条件封装异常" + e.getMessage());
        }
        return params;

    }

    /**
     * 组织机构统一条件封装
     * @param toolVO
     * @param request
     * @return
     */
    protected Map<String, Object> paramsPackageOrg(ToolVO toolVO, HttpServletRequest request){
        /*封装条件*/
        Map params = new HashMap<String, Object>();
        HttpSession session = request.getSession();

        try{
            //能耗数据表名称
            if(null != session){
                Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
                params.put("tableName",company.getTableName());
                params.put("comId",company.getId());
            }
            //查询数据条件
            params.put("orgId",toolVO.getToolOrgId());
            params.put("feedType",toolVO.getToolFeedType());
            params.put("startTime",toolVO.getToolStartDate()+" 00:00:00");
            params.put("endTime",toolVO.getToolEndDate()+" 23:59:59");
            params.put("startTimeTq",toolVO.getToolStartDateTq()+" 00:00:00");
            params.put("endTimeTq",toolVO.getToolEndDateTq()+" 23:59:59");
            params.put("orgType",toolVO.getToolOrgType());
        }catch (Exception e){
            logger.error("条件封装异常" + e.getMessage());
        }
        return params;

    }
}
