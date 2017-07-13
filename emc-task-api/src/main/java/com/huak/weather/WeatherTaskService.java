package com.huak.weather;

import java.util.Map;

/**
 * Created by MR-BIN on 2017/7/11.
 */
public interface WeatherTaskService {
    void executeWeatherTask(Map<String,Object> params);
}
