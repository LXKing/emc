package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.User;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.health.AlarmConfigService;
import com.huak.health.AlarmConfigTempService;
import com.huak.health.model.AlarmConfigTemp;
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
 * Description: 室温配置    <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/temp/config")
public class TempConfigController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String COMPANY = "company";
    private static final String ORG = "org";
    @Resource
    private AlarmConfigService alarmConfigService;
    @Resource
    private AlarmConfigTempService alarmConfigTempService;

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public String page(HttpServletRequest request,Model model){
        logger.info("打开室温配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(COMPANY,company);
        model.addAttribute(ORG,org);
        return "system/temp/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPage(HttpServletRequest request,Model model){
        logger.info("打开室温添加配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);
        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(COMPANY,company);
        model.addAttribute(ORG,org);
        return "system/temp/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(AlarmConfigTemp alarmConfigTemp, HttpServletRequest request) {
        logger.info("添加室温配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute(Constants.SESSION_KEY);

            alarmConfigTemp.setId(UUIDGenerator.getUUID());
            alarmConfigTempService.insertSelective(alarmConfigTemp);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加室温配置成功");
        } catch (Exception e) {
            logger.error("添加室温配置异常" + e.getMessage());
            jo.put(Constants.MSG, "添加室温配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("温度配置列表分页查询");
        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, alarmConfigTempService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("室温配置列表分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editPage(HttpServletRequest request,Model model,@PathVariable("id") String id){
        logger.info("打开修改室温配置页");
        HttpSession session = request.getSession();
        Company company = (Company)session.getAttribute(Constants.SESSION_COM_KEY);

        AlarmConfigTemp alarmConfigTemp = alarmConfigTempService.selectUpdateMap(id);

        Org org = (Org)session.getAttribute(Constants.SESSION_ORG_KEY);

        model.addAttribute(ORG,org);
        model.addAttribute("company",company);
        model.addAttribute("alarmConfig",alarmConfigTemp);
        return "system/temp/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(AlarmConfigTemp alarmConfig) {
        logger.info("修改室温配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            alarmConfigTempService.updateByPrimaryKeySelective(alarmConfig);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改室温配置成功");
        } catch (Exception e) {
            logger.error("修改室温配置异常" + e.getMessage());
            jo.put(Constants.MSG, "修改室温配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        logger.info("删除室温配置");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            alarmConfigTempService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除室温配置成功");
        } catch (Exception e) {
            logger.error("删除室温配置异常" + e.getMessage());
            jo.put(Constants.MSG, "删除室温配置失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出记录列表EXCEL");
        String workBookName = "预存记录列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)

        cellName.put("UNITNAME", "单位名称");
        cellName.put("NAME", "计量采集表名");
        cellName.put("CHANGE_TIME", "换表时间");
        cellName.put("USED_NUM", "旧表表底");
        cellName.put("NEW_NUM", "新表表底");
        cellName.put("USED_COEF", "旧表系数");
        cellName.put("NEW_COEF", "新表系数");
        cellName.put("CRESTOR", "创建人");
        cellName.put("CREATE_TIME", "创建时间");
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
            logger.error("导出记录列表EXCEL异常" + e.getMessage());
        }
    }
}
