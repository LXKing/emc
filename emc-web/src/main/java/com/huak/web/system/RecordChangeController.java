package com.huak.web.system;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.page.Page;
import com.huak.prst.ChangeService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
 * Date: 2017/8/31<BR>
 * Description:   换表记录  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/record/change")
public class RecordChangeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ChangeService changeService;

    @RequestMapping(method = RequestMethod.GET)
    public String page(){
        logger.info("打开换表记录页");
        return "system/change/list";
    }
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap,Page page) {
        logger.info("记录表页分页查询");
        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, changeService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("记录列表页分页查询异常" + e.getMessage());
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
            cellValues = changeService.exportChange(paramsMap);
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
