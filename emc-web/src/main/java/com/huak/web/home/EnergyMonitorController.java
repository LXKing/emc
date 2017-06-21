package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CollectionUtil;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.home.EnergyMonitorService;
import com.huak.home.model.EnergySecond;
import com.huak.home.type.ToolVO;
import com.huak.org.CompanyService;
import com.huak.org.OrgService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Date: 2017/5/26<BR>
 * Description:  首页-二级页面- 能耗分析  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/energy/monitor")
public class EnergyMonitorController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private EnergyMonitorService eaService;
    @Resource
    private OrgService orgService;
    @Resource
    private CompanyService companyService;

    /**
     * 跳转二级能耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/tsec", method = RequestMethod.GET)
    public String secondEconPage(Model model,HttpServletRequest request){
        logger.info("跳转二级能耗页面");
        try {
            Company company = (Company)request.getSession().getAttribute(Constants.SESSION_COM_KEY);

            model.addAttribute("company",company);
        } catch (Exception e) {
            logger.error("跳转二级能耗页面异常："+e.getMessage());
        }
        return "second/econ";
    }

    @RequestMapping(value = "/fgs/list", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyList(ToolVO toolVO){
        logger.info("分公司能耗详细");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
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

            jo.put(Constants.LIST, eaService.findAssessmentIndicators(params));
        } catch (Exception e) {
            logger.error("分公司能耗详细异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/ratio", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyRatio(ToolVO toolVO){
        logger.info("分公司能耗占比分布图");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
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

            List<Map<String,Object>> energySecondList = eaService.fgsEnergyRatio(params);
            jo.put(Constants.LIST, energySecondList);
        } catch (Exception e) {
            logger.error("分公司能耗占比分布图异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/trend", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyTrend(ToolVO toolVO){
        logger.info("分公司能耗趋势对比图");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
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

            List<Map<String,Object>> trendList = eaService.fgsEnergyTrend(params);

            List<String> xAxis = new ArrayList<>();
            List<Map<String,Object>> series = new LinkedList<>();
            List<String> legends = new ArrayList<>();
            //先确定几条线和横坐标
            for(Map<String,Object> map : trendList){
                String fgsId = map.get("FGSID").toString();
                String name = map.get("NAME").toString();
                String date = map.get("DATE").toString();
                legends.add(name);
                xAxis.add(date);
                map.get("BQBM").toString();
            }
            //去重复
            xAxis = CollectionUtil.removeDuplicateWithOrder(xAxis);
            legends = CollectionUtil.removeDuplicateWithOrder(legends);
            //组装数据
            for(String gsName:legends){
                Map<String,Object> dataMap = new HashMap<>();
                List<Double> dataList = new ArrayList<>();
                for(Map<String,Object> map : trendList){
                    String name = map.get("NAME").toString();
                    if(name.equals(gsName)){
                        dataList.add(Double.valueOf(map.get("BQBM").toString()));
                    }
                }
                dataMap.put("name",gsName);
                dataMap.put("type","line");
                dataMap.put("symbol","circle");
                dataMap.put("smooth",false);
                dataMap.put("data",dataList);
                series.add(dataMap);
            }

            jo.put(Constants.XAXIS,xAxis);
            jo.put(Constants.LEGENDS,legends);
            jo.put(Constants.LIST, series);
        } catch (Exception e) {
            logger.error("分公司能耗趋势对比图异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/ranking", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyRanking(ToolVO toolVO){
        logger.info("分公司能耗排名");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
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

            List<Map<String,Object>> energySecondList = eaService.fgsEnergyRanking(params);
            List<String> xAxis = new ArrayList<>();
            List<String> datas = new ArrayList<>();
            for(Map<String,Object> map:energySecondList){
                xAxis.add(map.get("NAME").toString());
                datas.add(map.get("VALUE").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put(Constants.LIST, datas);
        } catch (Exception e) {
            logger.error("分公司能耗排名异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/an", method = RequestMethod.POST)
    @ResponseBody
    public String fgsEnergyAn(ToolVO toolVO){
        logger.info("分公司能耗同比");

        JSONObject jo = new JSONObject();
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
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

            List<Map<String,Object>> energySecondList = eaService.fgsEnergyAn(params);
            List<String> xAxis = new ArrayList<>();
            List<String> bqs = new ArrayList<>();
            List<String> tqs = new ArrayList<>();
            for(Map<String,Object> map:energySecondList){
                bqs.add(map.get("BQBM").toString());
                xAxis.add(map.get("ORGNAME").toString());
                tqs.add(map.get("TQBM").toString());
            }
            jo.put(Constants.XAXIS, xAxis);
            jo.put("tq", tqs);
            jo.put("bq", bqs);
        } catch (Exception e) {
            logger.error("分公司能耗同比异常" + e.getMessage());
        }
        return jo.toJSONString();

    }

    @RequestMapping(value = "/fgs/export", method = RequestMethod.GET)
    public void export(ToolVO toolVO, HttpServletResponse response) {
        logger.info("导出分公司能耗列表EXCEL");

        String workBookName = "分公司能耗列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("ID", "组织主键");
        cellName.put("orgName", "组织名称");
        cellName.put("totalBq", "能耗总量本期");
        cellName.put("totalTq", "能耗总量同期");
        cellName.put("totalAn", "能耗总量同比");
        cellName.put("waterBq", "水能耗量本期");
        cellName.put("waterTq", "水能耗量同期");
        cellName.put("waterAn", "水能耗量同比");
        cellName.put("electricBq", "电能耗量本期");
        cellName.put("electricTq", "电能耗量同期");
        cellName.put("electricAn", "电能耗量同比");
        cellName.put("gasBq", "气能耗量本期");
        cellName.put("gasTq", "气能耗量同期");
        cellName.put("gasAn", "气能耗量同比");
        cellName.put("heatBq", "热能耗量本期");
        cellName.put("heatTq", "热能耗量同期");
        cellName.put("heatAn", "热能耗量同比");
        cellName.put("coalBq", "煤能耗量本期");
        cellName.put("coalTq", "煤能耗量同期");
        cellName.put("coalAn", "煤能耗量同比");
        cellName.put("oilBq", "油能耗量本期");
        cellName.put("oilTq", "油能耗量同期");
        cellName.put("oilAn", "油能耗量同比");
        List<Map<String, Object>> cellValues = new ArrayList<>();//列值
        OutputStream out = null;
        try {
            /*封装条件*/
            Map params = new HashMap<String, Object>();
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

            List<EnergySecond> energys = eaService.exportAssessmentIndicators(params);
            for(EnergySecond second: energys){
                cellValues.add(CollectionUtil.Obj2Map(second));
            }

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
            logger.error("导出分公司能耗列表EXCEL异常" + e.getMessage());
        }
    }

    /**
     * 查询集团能耗数据
     * @param params
     * @return
     */
	@RequestMapping(value = "/groupEnergy", method = RequestMethod.GET)
    @ResponseBody
    public String groupEnergy(@RequestParam Map<String,String> params){
        logger.info("查询集团能耗数据");
        JSONObject jo = new JSONObject();
        try{
        	jo.put("success", true);
            jo.put("message", "查询集团能耗数据成功！");
            //查询折线数据
            Map<String,Object> retMap = eaService.groupEnergyLine(params);
            jo.put("data", retMap);
        }catch(Exception e){
        	logger.error("查询集团能耗数据异常" + e.getMessage());
        	jo.put("success", false);
            jo.put("message", "查询能耗数据异常！");
        }
        return jo.toJSONString();
    }
	
	/**
     * 查询能源流明细
     * @param params
     * @return
     */
	@RequestMapping(value = "/energyFlowTable", method = RequestMethod.GET)
    @ResponseBody
	public String energyFlowTable(@RequestParam Map<String,String> params){
		logger.info("查询能源流明细");
        JSONObject jo = new JSONObject();
        try{
        	jo.put("success", true);
            jo.put("message", "查询能源流明细成功！");
            //查询折线数据
            List<Map<String,Object>> retMap = eaService.energyFlowTable(params);
            jo.put("data", retMap);
        }catch(Exception e){
        	logger.error("查询能源流明细异常" + e.getMessage());
        	jo.put("success", false);
            jo.put("message", "查询能源流明细异常！");
        }
        return jo.toJSONString();
	}
	
	/**
     * 查询能源流占比分布图
     * @param params
     * @return
     */
	@RequestMapping(value = "/energyFlowPie", method = RequestMethod.GET)
    @ResponseBody
	public String energyFlowPie(@RequestParam Map<String,String> params){
		logger.info("查询能源流占比分布图");
        JSONObject jo = new JSONObject();
        try{
        	jo.put("success", true);
            jo.put("message", "查询能源流占比分布图成功！");
            //查询折线数据
            List<Map<String,Object>> retMap = eaService.energyFlowPie(params);
            jo.put("data", retMap);
        }catch(Exception e){
        	logger.error("查询能源流占比分布图异常" + e.getMessage());
        	jo.put("success", false);
            jo.put("message", "查询能源流占比分布图异常！");
        }
        return jo.toJSONString();
	}
	
	/**
     * 查询能源流趋势对比图
     * @param params
     * @return
     */
	@RequestMapping(value = "/energyFlowLine", method = RequestMethod.GET)
    @ResponseBody
	public String energyFlowLine(@RequestParam Map<String,String> params){
		logger.info("查询能源流趋势对比图");
        JSONObject jo = new JSONObject();
        try{
        	jo.put("success", true);
            jo.put("message", "查询能源流趋势对比图成功！");
            //查询折线数据
            Map<String,Object> retMap = eaService.energyFlowLine(params);
            jo.put("data", retMap);
        }catch(Exception e){
        	logger.error("查询能源流趋势对比图异常" + e.getMessage());
        	jo.put("success", false);
            jo.put("message", "查询能源流趋势对比图异常！");
        }
        return jo.toJSONString();
	}
	
	/**
     * 查询能源流同比
     * @param params
     * @return
     */
	@RequestMapping(value = "/energyFlowBar", method = RequestMethod.GET)
    @ResponseBody
	public String energyFlowBar(@RequestParam Map<String,String> params){
		logger.info("查询能源流同比");
        JSONObject jo = new JSONObject();
        try{
        	jo.put("success", true);
            jo.put("message", "查询能源流同比成功！");
            //查询折线数据
            Map<String,Object> retMap = eaService.energyFlowBar(params);
            jo.put("data", retMap);
        }catch(Exception e){
        	logger.error("查询能源流同比异常" + e.getMessage());
        	jo.put("success", false);
            jo.put("message", "查询能源流同比异常！");
        }
        return jo.toJSONString();
	}
}
