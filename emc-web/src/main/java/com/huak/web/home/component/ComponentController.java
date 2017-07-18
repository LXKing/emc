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
    @RequestMapping(value = "/energyDetail", method = RequestMethod.POST)
    @ResponseBody
    public String energyDetail(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("能耗明细查询");
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
            logger.error("能耗明细查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *组件 成本明细
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/costDetail", method = RequestMethod.POST)
    @ResponseBody
    public String costDetail(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("成本明细查询");
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
            logger.error("成本明细查询查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     *组件 单耗趋势
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/energycomparison", method = RequestMethod.POST)
    @ResponseBody
    public String energycomparison(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("单耗趋势");
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
            logger.error("单耗趋势查询查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 天气组件
     * @param paramsMap
     * @param request
     * @return
     */
    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    @ResponseBody
    public String weather(@RequestParam Map<String, Object> paramsMap,HttpServletRequest request) {
        logger.info("单耗趋势");
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
            logger.error("天气查询查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

}
