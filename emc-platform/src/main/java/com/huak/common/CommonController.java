package com.huak.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huak.org.OrgService;
import com.huak.org.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/17.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private OrgService orgService;


    @ResponseBody
    @RequestMapping(value = "/org/tree", method = RequestMethod.POST)
    public Object orgtree(Model model,HttpServletResponse response){
        logger.info("查询组织机构树");
        int i=0;
        System.out.println("--------------"+i++);
        List<Org> as = orgService.selectOrgAll();
        return JSON.toJSON(as);
    }


    /**
     *名称唯一性校验
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String checkUnique(@RequestParam Map<String, Object> paramsMap) {
        logger.info("组织机构名称唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            List<Map<String,Object>> data= orgService.selectOrgByMap(paramsMap);
            if (data.size() == 0) {
                jo.put(Constants.FLAG, true);
            }else
                jo.put(Constants.FLAG,false);
        } catch (Exception e) {
            jo.put(Constants.FLAG,false);
            logger.error("组织机构名称唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
}
