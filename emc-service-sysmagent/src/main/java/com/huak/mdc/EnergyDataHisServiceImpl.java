package com.huak.mdc;

import com.huak.mdc.dao.EnergyDataHisDao;
import com.huak.mdc.model.EnergyDataHis;
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


    /**
     * 保存能耗数据
     *
     * @param energyDataHis
     */
    private boolean saveEnergyData(EnergyDataHis energyDataHis) {
        //todo 查询本期历史

        //todo 查询前期历史

        //todo 查询后期历史

        //todo 如果本期存在，则修改，否则添加

        //todo 如果后期历史存在，则修改本期到后期时间段的能耗数据

        //todo 如果前期历史存在，则修改本期到前期时间段的能耗数据

        return true;

    }

    /**
     * 批量保存能耗数据
     *
     * @param dataHisList
     */
    @Override
    @Transactional(readOnly = false)
    public boolean saveEnergyDatas(List<EnergyDataHis> dataHisList) {
        try{
            // 校验数据是否同期
            String time = "";
            for(int i=0;i<dataHisList.size();i++){
                EnergyDataHis energyDataHis = dataHisList.get(i);
                if(i == 0){
                    time = energyDataHis.getCollectTime();
                    continue;//第一次赋值
                }
                if(!time.equals(energyDataHis.getCollectTime())){
                    throw new UniformityException("批量数据不是同期数据 " + energyDataHis.toString() + " time:{" + time + "}");
                }
            }
            for(EnergyDataHis energyDataHis:dataHisList){
                //保存能耗数据
                boolean isSuccess = saveEnergyData(energyDataHis);
                //校验是否成功，保持数据完整性
                if(!isSuccess){
                    throw new UniformityException("批量数据保存部分异常 " + energyDataHis.toString());
                }
            }

        }catch (Exception e){
            logger.error("批量保存能耗数据失败:" + e.getMessage());
            return false;
        }
        return true;

    }
}
