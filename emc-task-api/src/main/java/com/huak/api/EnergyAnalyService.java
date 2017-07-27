package com.huak.api;

import com.huak.task.model.EnergyAnalySisdata;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/13<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface EnergyAnalyService {


    /**
     * 查询能源数据是否存在
     */
    public boolean selectEnergyAnalySisdata(String unitid);

    /**
     * 向数据库插入能数据
     *
     */
    void insetEnergyAnalySisdata(EnergyAnalySisdata record);

    /**
     * 计算水的每小时用量
     */
    public void selectInsertIntoFinalDataHourById(String id);

    /**
     * 计算电的每小时用量
     * @param id
     */
    public void selectPowerInsertFinalDataById(String id);

    /**
     * 计算每小时热的用量
     */
    public void selectHeatInsertFinalDataById(String id);

    /**
     * 计算每小时气的热量
     */
    public void selectQiInsertFinalDataById(String id);

}