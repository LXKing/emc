package com.huak.mdc;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.utils.MultipartFileParam;
import com.huak.common.utils.MultipartFileUploadUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 计量仪器管理
 * Created by MR-BIN on 2017/8/28.
 */
@RequestMapping("/meterCollect")
@Controller
public class MeterCollectController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static AtomicLong counter = new AtomicLong(0L);
    private  String finalDirPath = "/upload";
    @RequestMapping(method = RequestMethod.POST, value = "upload", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String add(HttpServletRequest request,HttpServletResponse response) {
        logger.info("后台-计量器具导入开始");
        String prefix = "req_count:" + counter.incrementAndGet() + ":";
        System.out.println(prefix + "start !!!");
        JSONObject jo = new JSONObject();
        MultipartFileParam param = null;
        try {
            param = MultipartFileUploadUtil.parse(request);
            if (param.isMultipart()) {
                File confFile = new File(finalDirPath, param.getFileName());
                File tmpDir = new File(finalDirPath);
                if (!tmpDir.exists()) {
                    tmpDir.mkdirs();
                }
                RandomAccessFile accessConfFile = new RandomAccessFile(confFile, "rw");
                accessConfFile.write(param.getFileItem().get());
                //completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
                byte[] completeList = FileUtils.readFileToByteArray(confFile);
                byte isComplete = Byte.MAX_VALUE;
                for (int i = 0; i < completeList.length && isComplete==Byte.MAX_VALUE; i++) {
                    //与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
                    isComplete = (byte)(isComplete & completeList[i]);
                }
                if (isComplete == Byte.MAX_VALUE) {
                    System.out.println(prefix + "upload complete !!");
                }
                accessConfFile.close();
                jo.put("flag",1);
            }
        } catch (Exception e) {
            logger.info("后台-计量器具导入出错"+ e);
            jo.put("flag",2);
        }
        return jo.toJSONString();
    }
}
