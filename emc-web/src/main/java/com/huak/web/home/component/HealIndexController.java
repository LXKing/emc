package com.huak.web.home.component;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.component.ComponentService;
import com.huak.home.type.ToolVO;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/9/26.
 */
@Controller
@RequestMapping("/healthcheck")
public class HealIndexController   extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ComponentService componentService;
    /**
     * 组件-健康指数检测
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
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
                    jjdata.put("css","m");
                }else{
                    jjdata.put("css","a");
                }

            }else{
                jjdata.put("serious",0);
                jjdata.put("moderate",0);
                jjdata.put("mild",0);
                jjdata.put("css","a");
            }
            Map<String,Object> data = new HashMap<>();
            Map<String,Object> gkdata = new HashMap<>();
            gkdata.put("serious",135);
            gkdata.put("moderate",135);
            gkdata.put("mild",135);
            gkdata.put("css","m");
            Map<String,Object> fwdata = new HashMap<>();
            fwdata.put("serious",0);
            fwdata.put("moderate",0);
            fwdata.put("mild",0);
            fwdata.put("css","a");
            Map<String,Object> zydata = new HashMap<>();
            zydata.put("serious",1000);
            zydata.put("moderate",380);
            zydata.put("mild",600);
            zydata.put("css","m");
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
