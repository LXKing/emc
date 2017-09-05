package com.huak.web.home.meterdata;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.home.type.ToolVO;
import com.huak.mdc.MeterCollectService;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/9/5.
 */
@Controller
@RequestMapping("/meterData")
public class MeterDataController extends BaseController{
     Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MeterCollectService meterCollectService;
    /**
     *安全与后台-数据填报
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String dataList(@RequestParam Map<String,Object> params,HttpServletRequest request) {
        logger.info("安全与后台-数据填报数据加载");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            List<Map<String,Object>> map =  meterCollectService.getMeterDatas(params);
            if (map!= null) {
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT, map);
            }else{
                jo.put(Constants.FLAG, true);
                jo.put(Constants.OBJECT,  new HashMap<>());
            }
        }catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("安全与后台-数据填报数据加载异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
