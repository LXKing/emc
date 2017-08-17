package com.huak.web.home;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.thiredpage.ThiredpageEnergyService;
import com.huak.home.type.ToolVO;
import com.huak.org.model.Company;
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
     * 跳转分公司三级能耗页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/fgs/{id}", method = RequestMethod.GET)
    public String fgsPage(Model model,HttpServletRequest request,@PathVariable("id")String id){
        logger.info("跳转分公司三级能耗页面");
        model.addAttribute("id",id);
        return "third/energy-fgs";
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
     *三级页面-源、网、站、线、户的各个能源类型的能耗趋势图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/energyTotalDetail", method = RequestMethod.POST)
    @ResponseBody
    public String energyTotalDetail(ToolVO toolVO,@RequestParam String type,HttpServletRequest request) {
        logger.info("三级页面-集团能源类型的能耗趋势图数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Map paramsMap = paramsPackageOrg(toolVO, request);
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            paramsMap.put("comId",company.getId());
            paramsMap.put("energytype",type);
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
        HttpSession session = request.getSession();
        Map paramsMap = paramsPackageOrg(toolVO, request);
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            paramsMap.put("comId",company.getId());
            paramsMap.put("energytype",type);
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
        HttpSession session = request.getSession();
        Map paramsMap = paramsPackageOrg(toolVO, request);
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            paramsMap.put("comId",company.getId());
            paramsMap.put("energytype",type);
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
     *三级页面-换热站排名趋势图
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/tablelist", method = RequestMethod.POST)
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
            paramsMap.put("energytype",type);
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
}
