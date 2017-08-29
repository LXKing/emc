package com.huak.mdc;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.Constants;
import com.huak.common.page.Page;
import com.huak.common.utils.MultipartFileParam;
import com.huak.common.utils.MultipartFileUploadUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 计量仪器管理
 * Created by MR-BIN on 2017/8/28.
 */
@RequestMapping("/meterCollect")
@Controller
public class MeterCollectController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MeterCollectService meterCollectService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String listPage(Model model) {
        logger.info("转至系统计量器具列表页");
//        String code = "pipeType";
//        List<SysDic> dic = orgService.selectSysDicAll(code);
//        model.addAttribute("sysdic",dic);
        return "/sys/mdc/list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "upload", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String add(HttpServletRequest request,HttpServletResponse response) {
        logger.info("后台-计量器具导入开始");
        JSONObject jo = new JSONObject();
        meterCollectService.excelupload(request);
        return jo.toJSONString();
    }
    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("计量器具列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, meterCollectService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("计量器具列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

}
