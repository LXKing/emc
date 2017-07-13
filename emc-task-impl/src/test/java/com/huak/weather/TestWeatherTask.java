package com.huak.weather;

import com.huak.base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by MR-BIN on 2017/7/11.
 */
public class TestWeatherTask extends BaseTest {

    @Autowired private  WeatherTaskService weatherTaskService;

    @Test
    public void testWeather(){
        Map<String,Object> params = new HashMap<>();
        params.put("weatherId","101060201");
        params.put("status",0);
        weatherTaskService.executeWeatherTask(params);
    }
}
