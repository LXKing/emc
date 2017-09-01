package com.huak.prst;

import com.alibaba.dubbo.config.annotation.Service;
import com.huak.base.dao.DateDao;
import com.huak.common.UUIDGenerator;
import com.huak.mdc.EnergyDataHisService;
import com.huak.mdc.UniformityException;
import com.huak.mdc.dao.MeterCollectDao;
import com.huak.mdc.dao.RecordChangeDao;
import com.huak.mdc.dao.RecordPrestoreDao;
import com.huak.mdc.model.EnergyDataHis;
import com.huak.mdc.model.MeterCollect;
import com.huak.mdc.model.RecordChange;
import com.huak.mdc.model.RecordPrestore;
import com.huak.org.dao.CompanyDao;
import com.huak.org.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.prst<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/31<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Service
public class ChangeServiceImpl implements ChangeService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private  RecordChangeDao recordChangeDao;
    @Resource
    private RecordPrestoreDao recordPrestoreDao;
    @Resource
    private DateDao dateDao;
    @Resource
    private MeterCollectDao meterCollectDao;
    @Resource
    private CompanyDao companyDao;
    @Resource
    private EnergyDataHisService energyDataHisService;
    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    /**
     * 计量器具-换表
     * @param record
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int insert(RecordChange record) {
        int flag = 0 ;
        try {
            record.setId(UUIDGenerator.getUUID());
            String date = dateDao.getTime();
            record.setCreateTime(date);
            MeterCollect meterCollect = meterCollectDao.selectByPrimaryKey(record.getCollectId());
            Company company = companyDao.selectByPrimaryKey(meterCollect.getComId());
            meterCollect.setCoef(record.getNewCoef());
            if(meterCollectDao.updateByPrimaryKey(meterCollect)>0){
                flag = recordChangeDao.insertSelective(record);
            }
            if(flag >0){
                List<EnergyDataHis> datalist = new ArrayList<>();
                EnergyDataHis data = new EnergyDataHis();
                data.setCollectId(record.getCollectId());
                data.setCollectTime(record.getChangeTime());
                data.setIschange((byte) 1);
                data.setChangeNum(record.getNewNum());
                data.setCollectNum(record.getUsedNum());
                datalist.add(data);
                energyDataHisService.saveEnergyDatas(datalist,company);
            }
        }catch (Exception e){
            logger.error("换表出错："+e);
            throw new UniformityException("换表出错： " + e.getMessage());
        }
        return flag;
    }



    @Override
    public int insertSelective(RecordChange record) {
        return 0;
    }

    @Override
    public RecordChange selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RecordChange record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RecordChange record) {
        return 0;
    }
}
