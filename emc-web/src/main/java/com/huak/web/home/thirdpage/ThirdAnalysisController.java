package com.huak.web.home.thirdpage;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.home.thiredpage.ThirdAnalysisService;
import com.huak.home.type.ToolVO;
import com.huak.org.model.Company;
import com.huak.web.home.BaseController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/2<BR>
 * Description:   三级单耗  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/third/analysis")
public class ThirdAnalysisController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ThirdAnalysisService thirdAnalysisService;
    /**
     * 跳转三级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/page/{type}", method = RequestMethod.GET)
    public String secondEconPage(Model model,HttpServletRequest request,@PathVariable("type")String type){
        logger.info("跳转三级单耗页面");
        model.addAttribute("type", type);
        return "third/analysis";
    }

    /**
     * 跳转分公司三级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/fgs/{id}", method = RequestMethod.GET)
    public String fgsPage(Model model,HttpServletRequest request,@PathVariable("id")String id){
        logger.info("跳转分公司三级单耗页面");
        model.addAttribute("id",id);
        return "third/analysis-fgs";
    }

    /**
     * 跳转用能单位类型三级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/unit/{type}", method = RequestMethod.GET)
    public String unitPage(Model model,HttpServletRequest request,@PathVariable("type")String type){
        logger.info("跳转用能单位类型三级单耗页面");
        model.addAttribute("type",type);
        return "third/analysis-unit";
    }

    /**
     * 水单耗明细
     */
    @RequestMapping(value = "/water/detail/{type}", method = RequestMethod.GET)
    @ResponseBody
    public String getAllData1(ToolVO toolVO, HttpServletRequest request,@PathVariable("type")String type){
        logger.info("计算水单耗");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        params.put("type",type);
        try {
            List<Map<String, Object>> list = thirdAnalysisService.getWaterDhDetail(params);
            Map<String, Object> reMap =thirdAnalysisService.getWaterDhAndTQ(params);
            //时间数据
            List<String> dateLine = new ArrayList<>();
            List<String> newDate = new ArrayList<>();
            List<String> oldDate = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map =list.get(i);
                String dh = map.get("DH").toString();
                newDate.add(dh);
                String dhTq = map.get("DHTQ").toString();
                oldDate.add(dhTq);
                String date = map.get("TIME").toString();
                dateLine.add(date);
            }

            jo.put(Constants.XAXIS, dateLine);
            jo.put("newDate", newDate);
            jo.put("oldDate", oldDate);
            jo.put("reMap",reMap);
        }catch (Exception e){
            logger.error("水单耗分析" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 水单耗 (源，网，站，线，户)
     */
    @RequestMapping(value = "/water/org/{type}", method = RequestMethod.GET)
    @ResponseBody
    public String getOrgAll(ToolVO toolVO, HttpServletRequest request,@PathVariable("type")String type){
        logger.info("计算源网站线户的水单耗");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        params.put("type",type);
        try {
            Map<String,Object>  reMap1 = thirdAnalysisService.getWaterDhOrg(params);
            Map<String,Object> reMap2=thirdAnalysisService.getWaterOrgDhAndTQ(params);
            jo.put("resultData", reMap1);
            jo.put("TotalTq", reMap2);
        }catch (Exception e){
            logger.error("组织机构的水单耗" + e.getMessage());
        }
        return jo.toJSONString();
    }
    /**
     * 热源的水单耗排名
     */
    @RequestMapping(value = "/water/feed-dh", method = RequestMethod.GET)
    @ResponseBody
    public String getFeedDh(ToolVO toolVO, HttpServletRequest request){
        logger.info("计算热源的水单耗排名");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        List<String> mapName = new ArrayList<String>();
        List<String> mapValue = new ArrayList<String>();

        try {
            List<Map<String,Object>>  list = thirdAnalysisService.getFeedDh(params);
            for (int i = 0; i <list.size() ; i++) {
                mapName.add(list.get(i).get("NAME").toString());
                mapValue.add(list.get(i).get("VALUE").toString());
            }
            jo.put("mapName", mapName);
            jo.put("mapValue", mapValue);
        }catch (Exception e){
            logger.error("热源的水单耗排名catch" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 换热站的水单耗排名
     */
    @RequestMapping(value = "/water/station-dh", method = RequestMethod.GET)
    @ResponseBody
    public String getStationDh(ToolVO toolVO, HttpServletRequest request){
        logger.info("计算换热站的水单耗排名");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        List<String> mapName = new ArrayList<String>();
        List<String> mapValue = new ArrayList<String>();

        try {
            List<Map<String,Object>>  list = thirdAnalysisService.getStationDh(params);
            for (int i = 0; i <list.size() ; i++) {
                mapName.add(list.get(i).get("NAME").toString());
                mapValue.add(list.get(i).get("VALUE").toString());
            }
            jo.put("mapName", mapName);
            jo.put("mapValue", mapValue);
        }catch (Exception e){
            logger.error("换热站的水单耗排名catch" + e.getMessage());
        }
        return jo.toJSONString();
    }
    /**
     *三级页面-换热站列表显示图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/table-list", method = RequestMethod.POST)
    @ResponseBody
    public String getTables(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-表单数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Map paramsMap = paramsPackageOrg(toolVO, request);
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            paramsMap.put("comId",company.getId());
            paramsMap.put("type",type);
            Map<String, Object> map =  thirdAnalysisService.getTable(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-表单数据加载加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
    /**
     * 水单耗明细
     */
    @RequestMapping(value = "/fgs/detail/{orgId}", method = RequestMethod.GET)
    @ResponseBody
    public String getFgsData(ToolVO toolVO, HttpServletRequest request,@PathVariable("orgId")String orgId){
        logger.info("计算分公司能耗折线图和同比");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        params.put("orgId",orgId);
        try {
            List<Map<String, Object>> list = thirdAnalysisService.getFgsDhDetail(params);
            Map<String, Object> reMap =thirdAnalysisService.getFgsDhAndTQ(params);
            //时间数据
            List<String> dateLine = new ArrayList<>();
            List<String> newDate = new ArrayList<>();
            List<String> oldDate = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map =list.get(i);
                String dh = map.get("BQDH").toString();
                newDate.add(dh);
                String dhTq = map.get("TQDH").toString();
                oldDate.add(dhTq);
                String date = map.get("TIME").toString();
                dateLine.add(date);
            }

            jo.put(Constants.XAXIS, dateLine);
            jo.put("newDate", newDate);
            jo.put("oldDate", oldDate);
            jo.put("reMap",reMap);
        }catch (Exception e){
            logger.error("计算分公司能耗折线图和同比" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *热源的水单耗排名
     */
    @RequestMapping(value = "/fgs/feed-dh/{type}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getFgsFeedDhPm(ToolVO toolVO, HttpServletRequest request,
                                    @PathVariable("type")String type,
                                    @PathVariable("id")String id){
        logger.info("热源的水单耗排名");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        List<String> mapName = new ArrayList<String>();
        List<String> mapValue = new ArrayList<String>();

        if(type==null||"".equals(type)){
            type="1";
            params.put("eType",type);
        }else {
            params.put("eType",type);
        }
        params.put("id",id);
        try {
            List<Map<String,Object>>  list = thirdAnalysisService.getFgsFeedDh(params);
            for (int i = 0; i <list.size() ; i++) {
                mapName.add(list.get(i).get("NAME").toString());
                mapValue.add(list.get(i).get("VALUE").toString());
            }
            jo.put("mapName", mapName);
            jo.put("mapValue", mapValue);
        }catch (Exception e){
            logger.error("热源的水单耗排名catch" + e.getMessage());
        }
        return jo.toJSONString();
    }
    /**
     *换热站的水单耗排名
     */
    @RequestMapping(value = "/fgs/station-dh/{type}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getFgsStationDhPm(ToolVO toolVO, HttpServletRequest request,
                                    @PathVariable("type")String type,
                                    @PathVariable("id")String id){
        logger.info("换热站的水单耗排名");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        List<String> mapName = new ArrayList<String>();
        List<String> mapValue = new ArrayList<String>();

        if(type==null||"".equals(type)){
            type="1";
            params.put("eType",type);
        }else {
            params.put("eType",type);
        }
        params.put("id",id);
        try {
            List<Map<String,Object>>  list = thirdAnalysisService.getFgsStationDh(params);
            for (int i = 0; i <list.size() ; i++) {
                mapName.add(list.get(i).get("NAME").toString());
                mapValue.add(list.get(i).get("VALUE").toString());
            }
            jo.put("mapName", mapName);
            jo.put("mapValue", mapValue);
        }catch (Exception e){
            logger.error("换热站的水单耗排名catch" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-分公司 表单
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/fgs/table-list", method = RequestMethod.POST)
    @ResponseBody
    public String getFgsTables(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-表单数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Map paramsMap = paramsPackageOrg(toolVO, request);
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            paramsMap.put("comId",company.getId());
            paramsMap.put("type",type);
            Map<String, Object> map =  thirdAnalysisService.getTable(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-表单数据加载加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 水单耗 (源，网，站，线，户)
     */
    @RequestMapping(value = "/fgs/org/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getFgsOrgAll(ToolVO toolVO, HttpServletRequest request,@PathVariable("id")String id){
        logger.info("计算源网站线户的单耗同比");
        JSONObject jo = new JSONObject();
        Map params = paramsPackageOrg(toolVO, request);
        params.put("id",id);
        try {
            Map<String,Object>  reMap1 = thirdAnalysisService.getFgsOrgDh(params);
            Map<String,Object> reMap2=thirdAnalysisService.getFgsOrgDhAndTQ(params);
            jo.put("resultData", reMap1);
            jo.put("TotalTq", reMap2);
        }catch (Exception e){
            logger.error("计算源网站线户的单耗同比" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/energy/export/{type}", method = RequestMethod.GET)
    public void exportEnergy(ToolVO toolVO, HttpServletResponse response, HttpServletRequest request,@PathVariable("type")String type) {
        logger.info("导出能源类型单耗明细列表EXCEL");

        String workBookName = "能源类型单耗明细列表";//文件名
        OutputStream out = null;
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            params.put("type",type);

            Map<String, Object> map =  thirdAnalysisService.getTable(params);

            HSSFWorkbook wb = CommonExcelExport.excelExportThird(map);
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
            logger.error("导出能源类型单耗明细列表EXCEL异常" + e.getMessage());
        }
    }
}
