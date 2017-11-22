package com.huak.data.dao;

import com.huak.data.vo.LookupTableTime;
import com.huak.data.vo.Weather;
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
* 查询天气
*
* */
    List<Weather> selectWeatherByMap(Map<String,Object> map);
}
