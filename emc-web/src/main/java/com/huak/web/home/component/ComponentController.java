package com.huak.web.home.component;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.component.ComponentService;
import com.huak.org.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/6/8.
 */
@Controller
@RequestMapping("/component")
public class ComponentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ComponentService componentService;


    /**
     *组件 能耗明细
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/energyDetail", method = RequestMethod.GET)
    @ResponseBody
    public String energyDetail(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("组件-能耗明细加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            HashMap<String,Object> params = new HashMap<>();
            params.put("orgId",paramsMap.get("toolOrgId"));
            params.put("feedType",paramsMap.get("toolFeedType"));
            params.put("startTime",paramsMap.get("toolStartDate"));
            params.put("endTime",paramsMap.get("toolEndDate"));
            params.put("type",paramsMap.get("toolOrgType"));
            params.put("comId",company.getId());
            Map<String,Object> map =  componentService.energyDetail(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else
                jo.put(Constants.FLAG,false);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("组件-能耗明细加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *组件 成本明细
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/costDetail", method = RequestMethod.GET)
    @ResponseBody
    public String costDetail(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("组件-成本明细加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("orgId",paramsMap.get("toolOrgId"));
            params.put("feedType",paramsMap.get("toolFeedType"));
            params.put("startTime",paramsMap.get("toolStartDate"));
            params.put("endTime",paramsMap.get("toolEndDate"));
            params.put("type",paramsMap.get("toolOrgType"));
            params.put("comId",company.getId());
            Map<String,Object> map =  componentService.costDetail(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else
                jo.put(Constants.FLAG,false);
        } catch (Exception e) {
            logger.error("组件-成本明细加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *组件 单耗趋势
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/energycomparison", method = RequestMethod.GET)
    @ResponseBody
    public String energycomparison(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("组件-单耗趋势加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("orgId",paramsMap.get("toolOrgId"));
            params.put("feedType",paramsMap.get("toolFeedType"));
            params.put("startTime",paramsMap.get("toolStartDate"));
            params.put("endTime",paramsMap.get("toolEndDate"));
            params.put("type",paramsMap.get("toolOrgType"));
            params.put("comId",company.getId());
            Map<String,Object> map =  componentService.energycomparison(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else
                jo.put(Constants.FLAG,false);
        } catch (Exception e) {
            logger.error("组件-单耗趋势加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 天气组件
     * @param paramsMap
     * @param request
     * @return
     */
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    @ResponseBody
    public String weather(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("组件-天气预报加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("code",company.getWcode());
            params.put("status","0");
            Map<String,Object> map =  componentService.weatherForcast(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else
                jo.put(Constants.FLAG,false);
        } catch (Exception e) {
            logger.error("组件-天气预报加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     * 室温散点组件
     * @param request
     * @return
     */
    @RequestMapping(value = "/roomtemperature", method = RequestMethod.GET)
    @ResponseBody
    public String roomtemperature(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("组件-室温散点加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);

        try {
            Map<String,Object> map =  componentService.roomTemperature(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else
                jo.put(Constants.FLAG,false);
        } catch (Exception e) {
            logger.error("组件-室温散点加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 组件-近期单耗详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/recentdetail", method = RequestMethod.GET)
    @ResponseBody
    public String recentdetail(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("组件-近期单耗详情加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("orgId",paramsMap.get("toolOrgId"));
            params.put("feedType",paramsMap.get("toolFeedType"));
            params.put("type",paramsMap.get("toolOrgType"));
            params.put("comId",company.getId());
            List<Map<String,Object>> map =  componentService.selectrecentDetail(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else
                jo.put(Constants.FLAG,false);
        } catch (Exception e) {
            logger.error("组件-近期单耗详情异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 组件-健康指数检测
     * @param request
     * @return
     */
    @RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
    @ResponseBody
    public String healthcheck(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("组件-健康指数检测加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        HttpSession session = request.getSession();
        Company company = (Company) session.getAttribute(Constants.SESSION_COM_KEY);
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("orgId",paramsMap.get("toolOrgId"));
            params.put("feedType",paramsMap.get("toolFeedType"));
            params.put("type",paramsMap.get("toolOrgType"));
            params.put("comId",company.getId());
            Map<String,Object> data = new HashMap<>();
            Map<String,Object> gkdata = new HashMap<>();
            gkdata.put("serious",135);
            gkdata.put("moderate",135);
            gkdata.put("mild",135);
            gkdata.put("css","b");
            Map<String,Object> jjdata = new HashMap<>();
            jjdata.put("serious",0);
            jjdata.put("moderate",28);
            jjdata.put("mild",360);
            jjdata.put("css","a");
            Map<String,Object> fwdata = new HashMap<>();
            fwdata.put("serious",0);
            fwdata.put("moderate",0);
            fwdata.put("mild",0);
            fwdata.put("css","a");
            Map<String,Object> zydata = new HashMap<>();
            zydata.put("serious",1000);
            zydata.put("moderate",380);
            zydata.put("mild",600);
            zydata.put("css","b");
            data.put("gkyx",gkdata);
            data.put("jjyx",jjdata);
            data.put("fwqk",fwdata);
            data.put("zygl",zydata);
            if (data!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, data);
            }else
                jo.put(Constants.FLAG,false);
        } catch (Exception e) {
            logger.error("组件-健康指数检测加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

}
