package com.huak.mdc;

import com.huak.mdc.dao.EnergyDataHisDao;
import com.huak.mdc.model.EnergyDataHis;
import com.huak.org.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
            addOrUpdate = energyDataHisDao.updateByPrimaryKeySelective(energyDataHis);
        } else {
            addOrUpdate = energyDataHisDao.insertSelective(energyDataHis);
        }
        if (addOrUpdate <= 0) {
            return false;
        }
        //todo 如果后期历史存在，则修改本期到后期时间段的能耗数据
        if (null != hqData && null != hqData.getId()) {
            boolean isSave = finalDataHourService.saveDataHour(energyDataHis,hqData,company);
            if(!isSave){
                return false;
            }
        }

        //todo 如果前期历史存在，则修改本期到前期时间段的能耗数据
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
            return false;
        }
        return true;

    }



}
