package com.huak.web.home.component;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.component.ComponentService;
import com.huak.home.type.RoomVO;
import com.huak.home.type.ToolVO;
import com.huak.org.model.Company;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class ComponentController extends BaseController{

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
    public String energyDetail(ToolVO toolVO, HttpServletRequest request) {
        logger.info("组件-能耗明细加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            Map<String,Object> map =  componentService.energyDetail(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-能耗明细加载异常" + e.getMessage());
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
    public String costDetail(ToolVO toolVO,HttpServletRequest request) {
        logger.info("组件-成本明细加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            Map<String,Object> map =  componentService.costDetail(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());

            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-成本明细加载异常" + e.getMessage());
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
    public String energycomparison(ToolVO toolVO,HttpServletRequest request) {
        logger.info("组件-单耗趋势加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            Map<String,Object> map =  componentService.energycomparison(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-单耗趋势加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 天气组件
     * @param
     * @param request
     * @return
     */
    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    @ResponseBody
    public String weather(HttpServletRequest request) {
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
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }

        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-天气预报加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     * 室温散点组件
     * @param request
     * @return
     */
    @RequestMapping(value = "/roomtemperature", method = RequestMethod.POST)
    @ResponseBody
    public String roomtemperature(ToolVO toolVO,HttpServletRequest request,RoomVO roomVO) {
        logger.info("组件-室温散点加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);

        try {
            /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            params.put("min",roomVO.getMin());
            params.put("max",roomVO.getMax());

            Map<String,Object> map =  componentService.roomTemperature(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-室温散点加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 组件-近期单耗详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/recentdetail", method = RequestMethod.POST)
    @ResponseBody
    public String recentdetail(ToolVO toolVO,HttpServletRequest request) {
        logger.info("组件-近期单耗详情加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
             /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);

            List<Map<String,Object>> map =  componentService.selectrecentDetail(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-近期单耗详情异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 组件-健康指数检测
     * @param request
     * @return
     */
    @RequestMapping(value = "/healthcheck", method = RequestMethod.POST)
    @ResponseBody
    public String healthcheck(ToolVO toolVO,HttpServletRequest request) {
        logger.info("组件-健康指数检测加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);

        try {
             /*封装条件*/
            Map params = paramsPackageOrg(toolVO, request);
            Map<String,Object> jjdata = new HashMap<>();
            Map<String,Object> datemp  = componentService.getAlarms(params);
            if((boolean)datemp.get("flag")){
                Map<String,Object> temp = (Map<String, Object>) datemp.get("data");

                jjdata.put("serious",null == temp.get("serious") ?0:temp.get("serious"));
                jjdata.put("moderate",null == temp.get("moderate") ?0:temp.get("moderate"));
                jjdata.put("mild",null == temp.get("mild") ?0:temp.get("mild"));
                if(null != temp.get("serious") && temp.get("serious")!= 0){
                    jjdata.put("css","b");
                }else{
                    jjdata.put("css","a");
                }

            }else{
                jjdata.put("serious",0);
                jjdata.put("moderate",0);
                jjdata.put("mild",0);
                jjdata.put("css","a");
            }
            jjdata.put("css","b");
            Map<String,Object> data = new HashMap<>();
            Map<String,Object> gkdata = new HashMap<>();
            gkdata.put("serious",135);
            gkdata.put("moderate",135);
            gkdata.put("mild",135);
            gkdata.put("css","b");
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
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组件-健康指数检测加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
