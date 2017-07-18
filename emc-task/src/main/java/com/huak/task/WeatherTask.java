package com.huak.task;

import com.huak.weather.WeatherTaskService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/11<BR>
 * Description:   定时任务测试  <BR>
 * Function List:  <BR>
 */
@Component("weatherTask")
public class WeatherTask {
    @Resource
    private WeatherTaskService weatherTaskService;

    public void currentWeather(){
        System.out.println("-----------------ceshi");
        List<Map<String,Object>> params = weatherTaskService.getParams();
        weatherTaskService.executeWeatherTask(params);
    }
    public void forcastWeather7d(){
        List<Map<String,Object>> params =  weatherTaskService.getParams();
        weatherTaskService.executeWeather7dTask(params);
    }
}
