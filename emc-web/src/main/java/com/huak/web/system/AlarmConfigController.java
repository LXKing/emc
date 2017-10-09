package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.User;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.health.AlarmConfigService;
import com.huak.health.model.AlarmConfig;
import com.huak.health.vo.AlarmConfigVO;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import com.huak.web.home.BaseController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.system<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/8<BR>
 * Description: 报警配置    <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/alarm/config")
public class AlarmConfigController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String COMPANY = "company";
    private static final String ORG = "org";
    @Resource
    private AlarmConfigService alarmConfigService;

    @RequestMapping(method = RequestMethod.GET)
    public String page(HttpServletRequest request,Model model){
        logger.info("打开报警配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(COMPANY,company);
        model.addAttribute(ORG,org);
        return "system/alarm/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPage(HttpServletRequest request,Model model){
        logger.info("打开添加报警配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(COMPANY,company);
        model.addAttribute(ORG,org);
        return "system/alarm/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(AlarmConfig alarmConfig, HttpServletRequest request) {
        logger.info("添加报警配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute(Constants.SESSION_KEY);

            alarmConfig.setId(UUIDGenerator.getUUID());
            alarmConfigService.insertSelective(alarmConfig);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加报警配置成功");
        } catch (Exception e) {
            logger.error("添加报警配置异常" + e.getMessage());
            jo.put(Constants.MSG, "添加报警配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("报警配置列表分页查询");
        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, alarmConfigService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("报警配置列表分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editPage(HttpServletRequest request,Model model,@PathVariable("id") String id){
        logger.info("打开修改报警配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);

        AlarmConfigVO alarmConfig = alarmConfigService.selectUpdateMap(id);
        model.addAttribute("company",company);
        model.addAttribute("alarmConfig",alarmConfig);
        return "system/alarm/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(AlarmConfig alarmConfig) {
        logger.info("修改报警配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            alarmConfigService.updateByPrimaryKeySelective(alarmConfig);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改报警配置成功");
        } catch (Exception e) {
            logger.error("修改报警配置异常" + e.getMessage());
            jo.put(Constants.MSG, "修改报警配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        logger.info("删除报警配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            alarmConfigService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除报警配置成功");
        } catch (Exception e) {
            logger.error("删除报警配置异常" + e.getMessage());
            jo.put(Constants.MSG, "删除报警配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出工况报警配置列表EXCEL");
        String workBookName = "工况报警配置列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)

        cellName.put("TAG", "点名");
        cellName.put("ALARM_NAME", "描述");
        cellName.put("ALARM_TYPE", "类型0-开关1-模拟");
        cellName.put("ALARM_LEVEL", "等级0-一级1-二级2-三级3-四级");
        cellName.put("MODEL", "报警模式0-低低1-低2-高3-高高");
        cellName.put("NUM", "阈值");
        cellName.put("ISENABLE", "是否启用0-启用 1-停用");
        cellName.put("COM_ID", "公司主键");
        cellName.put("ORGID", "组织主键");
        cellName.put("ORGNAME", "组织名称");
        cellName.put("UNIT_ID", "单位主键");
        cellName.put("UNITNAME", "单位名称");
        cellName.put("UNIT_TYPE", "单位类型1-源2-网3-站4-线5-户");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = alarmConfigService.exportAlarmConfig(paramsMap);
            HSSFWorkbook wb = CommonExcelExport.excelExport(cellName, cellValues);
            //response输出流导出excel
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("导出工况报警配置列表EXCEL异常" + e.getMessage());
        }
    }
}
