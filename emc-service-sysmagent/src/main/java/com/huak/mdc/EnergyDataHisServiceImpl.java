package com.huak.mdc;

import com.huak.common.StringUtils;
import com.huak.common.UUIDGenerator;
import com.huak.common.utils.DateUtils;
import com.huak.mdc.dao.EnergyDataHisDao;
import com.huak.mdc.dao.MeterCollectDao;
import com.huak.mdc.model.EnergyDataHis;
import com.huak.mdc.model.MeterCollect;
import com.huak.org.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description: 能耗历史    <BR>
 * Function List:  <BR>
 */
@Service
public class EnergyDataHisServiceImpl implements EnergyDataHisService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EnergyDataHisDao energyDataHisDao;
    @Resource
    private FinalDataHourService finalDataHourService;
    @Resource
    private MeterCollectDao meterCollectDao;


    /**
     * 保存能耗数据
     * 流程：
     * 1.判断本期，本期存在，则修改，否则添加
     * 2.如果后期历史存在，则修改本期到后期时间段的能耗数据
     * 3.如果前期历史存在，则修改本期到前期时间段的能耗数据
     * 修改数据流程：
     * 1.计算两期数据时间差中的每小时
     * 2.两期数据表底差值/时间差小时个数 = 每小时能耗
     * 3.查询标煤系数
     * 4.查询能源单价
     * 5.查询单位面积
     * 6.查询碳排放公式
     * 7.查询温度
     * 8.计算折算温度
     * 9.查询室温
     * 10.计算折算室温
     * 11.组装FinalDataHour保存或修改
     * @param energyDataHis
     * @param company
     */
    @Transactional(readOnly = false)
    private boolean saveEnergyData(EnergyDataHis energyDataHis, Company company) throws Exception {

        // 查询本期历史
        EnergyDataHis bqData = energyDataHisDao.findBqHis(energyDataHis);
        // 查询前期历史
        EnergyDataHis qqData = energyDataHisDao.findQqHis(energyDataHis);
        // 查询后期历史
        EnergyDataHis hqData = energyDataHisDao.findHqHis(energyDataHis);
        // 如果本期存在，则修改，否则添加
        int addOrUpdate = 0;
        if (null != bqData && null != bqData.getId()) {
            energyDataHis.setId(bqData.getId());
            addOrUpdate = energyDataHisDao.updateByPrimaryKeySelective(energyDataHis);
        } else {
            energyDataHis.setId(UUIDGenerator.getUUID());
            addOrUpdate = energyDataHisDao.insertSelective(energyDataHis);
        }
        if (addOrUpdate <= 0) {
            return false;
        }
        // 如果后期历史存在，则修改本期到后期时间段的能耗数据
        if (null != hqData && null != hqData.getId()) {
            boolean isSave = finalDataHourService.saveDataHour(energyDataHis,hqData,company);
            if(!isSave){
                return false;
            }
        }

        // 如果前期历史存在，则修改本期到前期时间段的能耗数据
        if (null != qqData && null != qqData.getId()) {
            boolean isSave = finalDataHourService.saveDataHour(energyDataHis,qqData,company);
            if(!isSave){
                return false;
            }
        }

        return true;

    }

    /**
     * 批量保存能耗数据
     *
     * @param dataHisList 能耗数据集合
     * @param company     公司
     */
    @Override
    @Transactional(readOnly = false)
    public boolean saveEnergyDatas(List<EnergyDataHis> dataHisList, Company company) {
        try {
            // 校验数据是否同期
            String time = "";
            for (int i = 0; i < dataHisList.size(); i++) {
                EnergyDataHis energyDataHis = dataHisList.get(i);
                if (i == 0) {
                    time = energyDataHis.getCollectTime();
                    continue;//第一次赋值
                }
                if (!time.equals(energyDataHis.getCollectTime())) {
                    throw new UniformityException("批量数据不是同期数据 " + energyDataHis.toString() + " time:{" + time + "}");
                }
            }
            for (EnergyDataHis energyDataHis : dataHisList) {
                //保存能耗数据
                boolean isSuccess = saveEnergyData(energyDataHis, company);
                //校验是否成功，保持数据完整性
                if (!isSuccess) {
                    throw new UniformityException("批量数据保存部分异常 " + energyDataHis.toString());
                }
            }

        } catch (Exception e) {
            logger.error("批量保存能耗数据失败:" + e.getMessage());
            e.printStackTrace();
        }
        return true;

    }

    /**
     * 批量保存虚表能耗数据
     *
     * @param meterCollectList
     * @param dateTime
     * @param company
     */
    @Override
    @Transactional(readOnly = false)
    public boolean saveVirtualDatas(List<MeterCollect> meterCollectList, String dateTime, Company company) {
        try {
            for (MeterCollect meterCollect : meterCollectList) {
                //保存虚表能耗数据
                boolean isSuccess = saveVirtualData(meterCollect,dateTime,company);
                //校验是否成功，保持虚表数据完整性
                if (!isSuccess) {
                    throw new UniformityException("批量数据保存部分异常 " + meterCollect.toString());
                }
            }
        } catch (Exception e) {
            logger.error("批量保存虚表能耗数据失败:" + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 每期数据都是必填
     * 1.根据dateTime查询前期数据
     * @param meterCollect
     * @param dateTime
     * @param company
     * @return
     */
    private boolean saveVirtualData(MeterCollect meterCollect, String dateTime, Company company) throws Exception {
        //获取公式
        String formula = meterCollect.getFormula();
        //获取第一个code
        List<String> codes = StringUtils.paresCodes(formula);
        if(codes.size()==0){
            return true;//没有公式不计算
        }
        String code = codes.get(0);
        //根据第一个code获取时间区间
        Map<String, Object> params = new HashMap<>();
        params.put("code",code);
        params.put("comId",company.getId());
        MeterCollect collect = meterCollectDao.checkCode(params).get(0);
        EnergyDataHis energyDataHis = new EnergyDataHis();
        energyDataHis.setCollectId(collect.getId());
        energyDataHis.setCollectTime(dateTime);
        // 查询本期历史
        EnergyDataHis bqData = energyDataHisDao.findBqHis(energyDataHis);
        // 查询前期历史
        EnergyDataHis qqData = energyDataHisDao.findQqHis(energyDataHis);
        // 查询后期历史
        EnergyDataHis hqData = energyDataHisDao.findHqHis(energyDataHis);

        String start = "";
        String end = "";
        if(qqData!=null&&hqData!=null){
            start = qqData.getCollectTime();
            end = hqData.getCollectTime();
        }else if(qqData!=null&&hqData==null){
            start = qqData.getCollectTime();
            end = bqData.getCollectTime();
        }else if(qqData==null&&hqData!=null){
            start = bqData.getCollectTime();
            end = hqData.getCollectTime();
        }else{
            return true;//前期后期数据不存在  不用计算能耗
        }
        //时间段每小时集合
        List<String> dateTimes = DateUtils.getDateTimes(start, end);

        finalDataHourService.saveVirtualDataHour(meterCollect,dateTimes,codes,company);

        return true;
    }



}
