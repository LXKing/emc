package com.huak.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huak.task.model.EnergyAnalySisdata;
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
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.api<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/24<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/data")
public class EnergyDataApi {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EnergyAnalyService energyAnalyService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object exportData( HttpServletRequest request ,String json){
        //接收请求参数
        String data=null;
        BufferedReader buffer=null;
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        try {
            inputStream=request.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            buffer = new BufferedReader(inputStreamReader);
            data = buffer.readLine();
            logger.info("导入数据的入参："+data);
        }catch (IOException e){
            logger.info("数据导入时异常"+e.getMessage());
        }finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException io) {
                    logger.info("流关闭异常"+io.getMessage());
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException io) {
                    logger.info("流关闭异常"+io.getMessage());
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException io) {
                    logger.info("流关闭异常"+io.getMessage());
                }
            }
        }
        JSONObject jsonObj = new JSONObject();
        JSONObject jb = JSON.parseObject(data);
        Object o =jb.get("json");
        EnergyAnalySisdata energyAnalySisdata = JSON.parseObject(o.toString(),EnergyAnalySisdata.class);
        boolean flag = energyAnalyService.selectEnergyAnalySisdata(energyAnalySisdata.getUnitid());
        if(flag){
            jsonObj.put("status","0");
            jsonObj.put("msg","该导入数据已存在");
            return jsonObj;
        }else {
            //保存导入数据
            energyAnalyService.insetEnergyAnalySisdata(energyAnalySisdata);
            //水的录入
            energyAnalyService.selectInsertIntoFinalDataHourById(energyAnalySisdata.getId());
            //电量的录入
            energyAnalyService.selectPowerInsertFinalDataById(energyAnalySisdata.getId());
            //Type=1热力站 =2热源
            if("1".equals(energyAnalySisdata.getType())){
                //热量的录入
                energyAnalyService.selectHeatInsertFinalDataById(energyAnalySisdata.getId());
            }
            if("2".equals(energyAnalySisdata.getType())){
                //天燃气的录入
                energyAnalyService.selectQiInsertFinalDataById(energyAnalySisdata.getId());
            }
            jsonObj.put("status","1");
            jsonObj.put("msg","组织机构数据导入成功");
            return jsonObj;
        }
    }
}
