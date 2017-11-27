package com.huak.home;


import com.huak.data.dao.DataStatisticsDao;
import com.huak.data.vo.LookupTableTime;
import com.huak.data.vo.Weather;
import com.huak.weather.model.HTSYWeather;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  liuhe  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/11/21<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Service
public class DataStatisticsServiceImpl implements DataStatisticsService {

    @Resource
    private DataStatisticsDao dataStatisticsDao;


    /**
     * 查询航天三院天气数据接口
     *
     * @param params params.comId 公司主键
     *               params.startDate 开始日期 2017-01-01
     *               params.endDate 结束日期 2017-01-20
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<HTSYWeather> getHTSYWeather(Map<String, Object> params) {
        return null;
    }

    /*
        * 查表时间
        *
        * */
    @Override
    @Transactional(readOnly = true)
    public LookupTableTime getLookupTableTime(Map<String, Object> params) {
       return dataStatisticsDao.selectTableTimeByMap(params);
    }

    /*
   * 天气预报
   *
   * */
    @Override
    @Transactional(readOnly = true)
    public List<Weather> getWeatherByDate(Map<String, Object> params) {
        return dataStatisticsDao.selectWeatherByMap(params);
    }
}
