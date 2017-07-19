package com.huak.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huak.common.UUIDGenerator;
import com.huak.org.model.Node;
import com.huak.task.model.EmcOrgInter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.api<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/station")
public class StationApi {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoomTempService roomTempService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object exportOrgEcc( HttpServletRequest request ,String json) throws IOException {
        //接收请求参数
        InputStreamReader reader=new InputStreamReader(request.getInputStream(),"UTF-8");
        BufferedReader buffer=new BufferedReader(reader);
        String data=buffer.readLine();
        logger.info("导入数据的入参："+data);
        JSONObject jb = JSON.parseObject(data);
        Object o =jb.get("json");
        Map<String,Object> map = (Map<String,Object>) JSON.parse(o.toString());
        map.put("unitType","2");
        JSONObject jsonObj = new JSONObject();
        List<EmcOrgInter> list = roomTempService.isExsistInter(map);
        if(list.size()>0){
            jsonObj.put("status","0");
            jsonObj.put("msg","该数据已存在");
            return jsonObj;
        }else {
            Node eccNode = JSON.parseObject(o.toString(),Node.class);
            EmcOrgInter inter = new EmcOrgInter();
            String uuid = UUIDGenerator.getUUID();
            eccNode.setId(uuid);
            logger.info("---------------------开始导入数据---------------------");
            Map<String,Object> result = roomTempService.inserStation(eccNode);
            if(result.get("flag")==true){
                logger.info("----------建立关系表数据----------");
                inter.setComId(eccNode.getComId());
                inter.setEmcId(uuid);
                inter.setOrgId(eccNode.getOrgId().toString());
                inter.setUnitType("1");
                roomTempService.insertEmcOrgInter(inter);
                jsonObj.put("status","2");
                jsonObj.put("msg","数据导入成功");
                return jsonObj;
            }
        }
        jsonObj.put("status","2");
        jsonObj.put("msg","系统导入数据异常");
        logger.info("返回给客户端的jsonstr："+json.toString());
        return jsonObj;
    }
}
