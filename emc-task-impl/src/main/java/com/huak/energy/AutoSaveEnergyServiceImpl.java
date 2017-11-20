package com.huak.energy;

import com.huak.base.dao.DateDao;
import com.huak.common.StringUtils;
import com.huak.task.dao.AutoSaveDataDao;
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
 * File name:  com.huak.energy<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-16<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class AutoSaveEnergyServiceImpl implements AutoSaveEnergyService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DateDao dateDao;
    @Resource
    private AutoSaveDataDao autoSaveDataDao;
    /**
     * 自动保存采集数据到能耗
     */
    @Override
    @Transactional(readOnly = false)
    public List<Map<String,Object>> autoSaveData() {
        //当前数据库时间
        String time = dateDao.getTime();
        // 获取采集数据最大时间yyyy-MM-dd HH
        Map<String,Object> params = new HashMap<>();
        params.put("time", time);
        String maxTime = autoSaveDataDao.getMaxDataTime(params);
        if(StringUtils.isEmpty(maxTime)){
            logger.info("--------time：【" + time + "】没有最新的采集数据--------");
            return null;
        } else {
            // 查询自动采集的最大时间批次的数据
            params.clear();
            params.put("maxTime", maxTime);
           return autoSaveDataDao.selectAutoDataByTime(params);

        }
    }
}
