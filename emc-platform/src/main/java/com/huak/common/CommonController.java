package com.huak.common;

import com.alibaba.fastjson.JSON;
import com.huak.org.OrgService;
import com.huak.org.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

}
