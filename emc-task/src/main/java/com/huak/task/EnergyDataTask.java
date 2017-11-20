package com.huak.task;

import com.huak.energy.AutoSaveEnergyService;
import com.huak.mdc.EnergyDataHisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-16<BR>
 * Description:   自动采集数据自动转存为每小时能耗数据  <BR>
 * Function List:  <BR>
 */
@Component("energyDataTask")
public class EnergyDataTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private AutoSaveEnergyService autoSaveEnergyService;
    private EnergyDataHisService energyDataHisService;

    public void autoSaveEnergyData(){
        logger.info("-------------定时自动填报自动采集数据----------------");
        //查询自动采集数据
        List<Map<String,Object>> datas = autoSaveEnergyService.autoSaveData();
        for(Map<String,Object> map:datas){
            String collId = map.get("ID").toString();//采集计量表主键
            String collTime = map.get("TIME").toString();//时间
            String collNum = map.get("NUM").toString();//表底
            //todo 调用填报接口
            //energyDataHisService.saveEnergyDatas(datalist0,company);
        }



        //todo 修改自动采集数据状态为1转存
        logger.info("-------------自动填报自动采集数据完成----------------");
    }
}
