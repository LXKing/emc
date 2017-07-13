package com.huak.weather;

import java.util.Map;

/**
 * Created by MR-BIN on 2017/7/11.
 */
public interface WeatherTaskService {
    /**
     * 实时数据
     * @param params
     */
    void executeWeatherTask(Map<String,Object> params);

    /**
     * 7d预测
     * @param params
     */
    void executeWeather7dTask(Map<String, Object> params);
}
