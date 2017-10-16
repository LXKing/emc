package com.huak.home;

import com.huak.base.BaseTest;
import com.huak.base.dao.DateDao;
import com.huak.common.utils.DateUtils;
import com.huak.health.model.HealthScoreRecord;
import com.huak.home.component.ComponentService;
import com.huak.home.component.HealthScoreRecordService;
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
    @Autowired
    private HealthScoreRecordService healthScoreRecordService;
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
    @Test
    public void testjkzs(){
        Map<String,Object> params = new  HashMap<String,Object>();
        params.put("orgId",74);
        params.put("userid","88cd8b26b6234610a0b92cc38b834304");
        HealthScoreRecord h = healthScoreRecordService.getRecordById(params);
        System.out.println(h.getScore());
    }
//    @Test
//    public void testAdd(){
//        HealthScoreRecord h = new HealthScoreRecord();
//        h.setId("6");
//        h.setCreateTime(dateDao.getTime());
//        h.setOrgId("74");
//        h.setScore(85);
//        h.setUserid("88cd8b26b6234610a0b92cc38b834304");
//        int i =healthScoreRecordService.insertSelective(h);
//        System.out.println(i);
//    }
//    @Test
//    public void testjkzsKSJC() throws InterruptedException {
//        Map<String,Object> params = new  HashMap<String,Object>();
//        params.put("orgId",74);
//        params.put("comId","a3e5e868e7844c349e5cf51c5e6bc37d");
//
//        params.put("name","电单耗");
//
//        //List<PollingMessage> list = healthScoreRecordService.getIndexData(params);
//        for (int i = 0; i <list.size() ; i++) {
//            System.out.println(list.get(i).getValue());
//        }
//    }
//    @Test
//    public void testTemp() throws ParseException {
//        Map<String,Object> paramsMap = new HashMap<>();
//
//        paramsMap.put("comId","a3e5e868e7844c349e5cf51c5e6bc37d");
//        paramsMap.put("startTime","2016-11-15 00:00:00");
//        paramsMap.put("endTime","2017-03-15 23:59:59");
//        List<PollingMessage> list = healthScoreRecordService.getIndexTemp(paramsMap);
//        for (int i = 0; i <list.size() ; i++) {
//            System.out.println(list.get(i).getValue());
//        }
//
//    }
}
