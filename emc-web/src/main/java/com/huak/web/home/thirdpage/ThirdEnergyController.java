package com.huak.web.home.thirdpage;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.thiredpage.ThiredpageEnergyService;
import com.huak.home.type.ToolVO;
import com.huak.org.model.Company;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
 * Date: 2017/8/2<BR>
 * Description:   三级能耗  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/third/energy")
public class ThirdEnergyController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ThiredpageEnergyService thiredpageEnergyService;
    
    private static  String ENERGY_TYPE = "energytype";
    /**
     * 跳转三级单耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/page/{type}", method = RequestMethod.GET)
    public String secondEconPage(Model model,HttpServletRequest request,@PathVariable("type")String type){
        logger.info("跳转能源类型三级能耗页面");
        model.addAttribute("type",type);
        return "third/energy";
    }

    /**
     * 跳转用能单位类型三级能耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/unit/{type}", method = RequestMethod.GET)
    public String unitPage(Model model,HttpServletRequest request,@PathVariable("type")String type){
        logger.info("跳转用能单位类型三级能耗页面");
        model.addAttribute("type",type);
        return "third/energy-unit";
    }

    /**
     *三级页面-集团能源类型的能耗趋势图数据加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/energyTotalDetail", method = RequestMethod.POST)
    @ResponseBody
    public String energyTotalDetail(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-集团能源类型的能耗趋势图数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ENERGY_TYPE,type);
            Map<String,Object> map =  thiredpageEnergyService.getDatasAll(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-集团能源类型的能耗趋势图数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-源、网、站、线、户的各个能源类型的能耗趋势图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/energyDetail", method = RequestMethod.POST)
    @ResponseBody
    public String energyDetail(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-源、网、站、线、户的各个能源类型的能耗趋势图数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ENERGY_TYPE,type);
            Map<String,Object> map =  thiredpageEnergyService.getDatas(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-源、网、站、线、户的各个能源类型的能耗趋势图数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-换热站排名趋势图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/assessment", method = RequestMethod.POST)
    @ResponseBody
    public String assessment(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-换热站排名趋势图数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ENERGY_TYPE,type);
            Map<String,Object> map =  thiredpageEnergyService.getassessment(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-换热站排名趋势图数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     *三级页面-表单数据加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/tablelist", method = RequestMethod.POST)
    @ResponseBody
    public String getTables(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-表单数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put(ENERGY_TYPE,type);
            Map<String, Object> map =  thiredpageEnergyService.getTable(paramsMap);
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
     *三级页面-用能单位类型-能源对比数据加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/energyDetail", method = RequestMethod.POST)
    @ResponseBody
    public String unitEnergyDetail(ToolVO toolVO,@RequestParam String type,@RequestParam String energyType,HttpServletRequest request){
        logger.info("三级页面-用能单位类型-能源对比数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put("orgType",type);
            paramsMap.put(ENERGY_TYPE,energyType);
            Map<String,Object> map =  thiredpageEnergyService.getUnitEnergyDetail(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-用能单位类型-能源对比数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     *三级页面-用能单位类型-能源能耗排名加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/unitAssessment", method = RequestMethod.POST)
    @ResponseBody
    public String unitAssessment(ToolVO toolVO,@RequestParam String type,@RequestParam String energyType,HttpServletRequest request) {
        logger.info("三级页面-用能单位类型-能源能耗排名加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put("orgType",type);
            paramsMap.put(ENERGY_TYPE,energyType);
            Map<String,Object> map =  thiredpageEnergyService.getUnitAssessments(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        }catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-用能单位类型-个能源能耗排名加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     *三级页面-用能单位类型-个能源能耗趋势加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/unitAllAssessment", method = RequestMethod.POST)
    @ResponseBody
    public String unitAllAssessment(ToolVO toolVO,@RequestParam String type,@RequestParam String energyType,HttpServletRequest request) {
        logger.info("三级页面-用能单位类型-个能源能耗趋势加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put("orgType",type);
            paramsMap.put(ENERGY_TYPE,energyType);
            Map<String,Object> map =  thiredpageEnergyService.getUnitAllAssessment(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        }catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-用能单位类型-个能源能耗趋势加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *三级页面-用能单位类型-能源类型表格加载
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/unit/unitTableList", method = RequestMethod.POST)
    @ResponseBody
    public String unitTableList(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-用能单位类型-能源类型表格加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        Map paramsMap = paramsPackageOrg(toolVO, request);
        try {
            paramsMap.put("orgType",type);
            Map<String,Object> map =  thiredpageEnergyService.getThirdTables(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        }catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("三级页面-用能单位类型-能源类型表格加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
