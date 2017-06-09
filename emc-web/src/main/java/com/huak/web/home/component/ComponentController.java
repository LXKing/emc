package com.huak.web.home.component;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.component.ComponentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    public String energyDetail(@RequestParam Map<String, Object> paramsMap) {
        logger.info("能耗明细查询");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        paramsMap.put("startTime","2016-11-15");
        paramsMap.put("endTime","2017-03-15");
        try {
          Map<String,Object> map =  componentService.energyDetail(paramsMap);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else
                jo.put(Constants.FLAG,false);
        } catch (Exception e) {
            logger.error("能耗明细查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
