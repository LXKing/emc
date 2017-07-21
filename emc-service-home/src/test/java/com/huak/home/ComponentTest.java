package com.huak.home;

import com.huak.base.BaseTest;
import com.huak.base.dao.DateDao;
import com.huak.common.utils.DateUtils;
import com.huak.home.component.ComponentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/7/17.
 */
public class ComponentTest  extends BaseTest {
    @Autowired
    private ComponentService componentService;
    @Resource
    private DateDao dateDao;
    @Test
    public void execute(){
        Map<String,Object> params = new HashMap<>();
        params.put("code","101030100");
        params.put("status","0");
        Map<String,Object> map =  componentService.weatherForcast(params);
    }

    @Test
    public void testComponent(){
        Map<String,Object> params = new HashMap<>();
        params.put("comId","a3e5e868e7844c349e5cf51c5e6bc37d");
        params.put("min",18);
        params.put("max",22);
        Map<String,Object> map =  componentService.roomTemperature(params);
    }

    @Test
    public void testTcon() throws ParseException {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("orgId",38);
        paramsMap.put("comId","74ee3b6752254435b724b6672f9fde8d");
        String date = dateDao.getDate();
        String yesterday = DateUtils.getDay(date, -1);
        String towdayago = DateUtils.getDay(date,-2);
        String treedayago = DateUtils.getDay(date,-3);
        paramsMap.put("today",date);
        paramsMap.put("yesterday",yesterday);
        paramsMap.put("towdayago",towdayago);
        paramsMap.put("treedayago",treedayago);
        List<Map<String,Object>> result = componentService.selectrecentDetail(paramsMap);
    }
}
