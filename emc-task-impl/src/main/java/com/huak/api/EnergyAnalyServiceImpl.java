package com.huak.api;

import com.huak.task.dao.EmcOrgInterDao;
import com.huak.task.dao.EnergyAnalySisdataDao;
import com.huak.task.model.EmcOrgInter;
import com.huak.task.model.EnergyAnalySisdata;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/13<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class EnergyAnalyServiceImpl implements EnergyAnalyService {

    @Resource
    EnergyAnalySisdataDao energyAnalySisdataDao;
    @Resource
    EmcOrgInterDao emcOrgInterDao;

    @Override
    public boolean selectEnergyAnalySisdata(String unitid) {
        boolean flag=false;
        EmcOrgInter eoi= new EmcOrgInter();
        eoi.setOrgId(unitid);
        List<EmcOrgInter> list = emcOrgInterDao.selectAllByMap(eoi);
        if(list.size()>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public void insetEnergyAnalySisdata(EnergyAnalySisdata record) {
        energyAnalySisdataDao.insertSelective(record);
    }

    @Override
    public void selectInsertIntoFinalDataHourById(String id) {
        energyAnalySisdataDao.selectFinalDataHourById(id);
    }

    @Override
    public void selectHeatInsertFinalDataById(String id) {
        energyAnalySisdataDao.selectHeatById(id);
    }

    @Override
    public void selectPowerInsertFinalDataById(String id) {
        energyAnalySisdataDao.selectPowerById(id);
    }
    @Override
    public void selectQiInsertFinalDataById(String id) {
        energyAnalySisdataDao.selectQiById(id);
    }
}

