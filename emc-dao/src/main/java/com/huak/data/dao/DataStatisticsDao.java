package com.huak.data.dao;

import com.huak.data.vo.HistoryData;
import com.huak.data.vo.LookupTableTime;
import com.huak.weather.model.HTSYWeather;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.data.dao<BR>
 * Author:  liuhe  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/11/21<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Repository
public interface DataStatisticsDao {
/*
* 查表时间
*
* */
    LookupTableTime selectTableTimeByMap(Map<String,Object> map);




    /*
* 查询历史数据
*
* */
    List<HistoryData> selectHistoryDataByMap(Map<String,Object> map);

    /**
     * 查询航天三院天气数据接口
     *
     * @param params params.comId 公司主键
     *               params.startDate 开始日期 2017-01-01
     *               params.endDate 结束日期 2017-01-20
     *               params.tag 点表 研发区1号站.AI_out_T
     * @return
     */
    List<HTSYWeather> selectTempHTSY(Map<String,Object> params);
}
