package com.huak.mdc;

import com.huak.base.BaseTest;
import com.huak.mdc.model.MeterCollect;
import com.huak.org.CompanyService;
import com.huak.org.model.Company;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class TestEnergyData extends BaseTest {
    @Resource
    private EnergyDataHisService energyDataHisService;
    @Resource
    private CompanyService companyService;
    @Resource
    private MeterCollectService meterCollectService;

//    @Test
//    @Rollback
//    public void testAddFiast() {
//        List<EnergyDataHis> dataHisList = new ArrayList<>();
//        EnergyDataHis energyDataHis = new EnergyDataHis();
//        energyDataHis.setCollectNum(10d);
//        energyDataHis.setCollectId("3");
//        energyDataHis.setCollectTime("2017-07-01 08:00:00");
//        energyDataHis.setIsprestore((byte) 0);
//        energyDataHis.setIschange((byte) 0);
//        dataHisList.add(energyDataHis);
//
//        Company company = companyService.selectByPrimaryKey("a3e5e868e7844c349e5cf51c5e6bc37d");
//        boolean isTrue = energyDataHisService.saveEnergyDatas(dataHisList, company);
//        System.err.println("-------------[isTrue = " + isTrue + "]----------");
//    }

//    @Test
//    @Rollback
//    public void testAddHq() {
//        List<EnergyDataHis> dataHisList = new ArrayList<>();
//        EnergyDataHis energyDataHis = new EnergyDataHis();
//        energyDataHis.setCollectNum(153d);
//        energyDataHis.setCollectId("3");
//        energyDataHis.setCollectTime("2017-07-03 08:00:00");
//        energyDataHis.setIsprestore((byte) 0);
//        energyDataHis.setIschange((byte) 0);
//        dataHisList.add(energyDataHis);
//
//        Company company = companyService.selectByPrimaryKey("a3e5e868e7844c349e5cf51c5e6bc37d");
//        boolean isTrue = energyDataHisService.saveEnergyDatas(dataHisList, company);
//        System.err.println("-------------[isTrue = " + isTrue + "]----------");
//    }

//    @Test
//    @Rollback
//    public void testHb() {
//        List<EnergyDataHis> dataHisList = new ArrayList<>();
//        EnergyDataHis energyDataHis = new EnergyDataHis();
//        energyDataHis.setCollectNum(85d);
//        energyDataHis.setCollectId("5");
//        energyDataHis.setCollectTime("2017-07-05 08:00:00");
//        energyDataHis.setIsprestore((byte) 0);
//        energyDataHis.setIschange((byte) 0);
////        energyDataHis.setPrestoreNum(10d);
////        energyDataHis.setChangeNum(100d);
//        dataHisList.add(energyDataHis);
//
//        Company company = companyService.selectByPrimaryKey("a3e5e868e7844c349e5cf51c5e6bc37d");
//        boolean isTrue = energyDataHisService.saveEnergyDatas(dataHisList, company);
//        System.err.println("-------------[isTrue = " + isTrue + "]----------");
//    }

    @Test
    @Rollback
    public void testXBNHSJ() {
        List<MeterCollect> meterCollects = new ArrayList<>();
        MeterCollect meterCollect = meterCollectService.selectByPrimaryKey("6");
        meterCollects.add(meterCollect);

        Company company = companyService.selectByPrimaryKey("a3e5e868e7844c349e5cf51c5e6bc37d");
        boolean isTrue = energyDataHisService.saveVirtualDatas(meterCollects,"2017-07-02 08:00:00", company);
        System.err.println("-------------[isTrue = " + isTrue + "]----------");
    }
}
