package com.huak.home.thiredpage;

import com.huak.base.dao.DateDao;
import com.huak.home.dao.thiredpage.ThirdAnalysisDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.thiredpage<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/11<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class ThirdAnalysisServiceImpl implements ThirdAnalysisService {

    private static String UNIT_TYPE = "unitType";
    private static String unitType = "unitType";
    private static String unitName = "unitName";
    private static String UNIT_NAME = "unitName";
    private static String[] TYPE_NAME = {"水","电","气","热","煤","油"};
    private static String[] UNITTYPE = {"1","2","3","4","5","6"};
    @Resource
    private ThirdAnalysisDao thirdAnalysisDao;
    @Resource
    private DateDao dateDao;
    @Override
    public List<Map<String, Object>> getWaterDhDetail(Map<String, Object> map) {
        return thirdAnalysisDao.getWaterDhDetail(map);
    }

    @Override
    public Map<String, Object> getWaterDhOrg(Map<String, Object> map) {
        List<Map<String, Object>> list = thirdAnalysisDao.getWaterDhOrg(map);
        //时间数据
        List<String> dateLine = new ArrayList<>();
        List<String> newFeedDate = new ArrayList<>();
        List<String> oldFeedDate = new ArrayList<>();
        List<String> newNetDate = new ArrayList<>();
        List<String> oldNetDate = new ArrayList<>();
        List<String> newStationDate = new ArrayList<>();
        List<String> oldStationDate = new ArrayList<>();
        List<String> newLineDate = new ArrayList<>();
        List<String> oldLineDate = new ArrayList<>();
        List<String> newRoomDate = new ArrayList<>();
        List<String> oldRoomDate = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> mapData =list.get(i);
            //源的数据
            String newFeedWater = mapData.get("newYwater").toString();
            newFeedDate.add(newFeedWater);
            String oldFeedWater = mapData.get("oldYwater").toString();
            oldFeedDate.add(oldFeedWater);
            //网的数据
            String newNetWater = mapData.get("newWwater").toString();
            newNetDate.add(newNetWater);
            String oldNetWater = mapData.get("oldWwater").toString();
            oldNetDate.add(oldNetWater);
            //站的数据
            String newStationWater = mapData.get("newZwater").toString();
            newStationDate.add(newStationWater);
            String oldStationWater = mapData.get("oldZwater").toString();
            oldStationDate.add(oldStationWater);
            //线的数据
            String newLineWater = mapData.get("newXwater").toString();
            newLineDate.add(newLineWater);
            String oldLineWater = mapData.get("oldXwater").toString();
            oldLineDate.add(oldLineWater);
            //户的数据
            String newRoomWater = mapData.get("newHwater").toString();
            newRoomDate.add(newRoomWater);
            String oldRoomWater = mapData.get("oldHwater").toString();
            oldRoomDate.add(oldRoomWater);

            String date = mapData.get("TIME").toString();
            dateLine.add(date);
        }

        Map<String,Object> resulMap=new  HashMap<String,Object>();
        resulMap.put("newFeed",newFeedDate);
        resulMap.put("oldFeed",oldFeedDate);
        resulMap.put("newNet",newNetDate);
        resulMap.put("oldNet",oldNetDate);
        resulMap.put("newStation",newStationDate);
        resulMap.put("oldStation",oldStationDate);
        resulMap.put("newLine",newLineDate);
        resulMap.put("oldLine",oldLineDate);
        resulMap.put("newRoom",newRoomDate);
        resulMap.put("oldRoom",oldRoomDate);
        resulMap.put("dateLine",dateLine);
        return resulMap;
    }

    @Override
    public Map<String, Object> getWaterDhAndTQ(Map<String, Object> map) {
        return thirdAnalysisDao.getTotalAndTq(map);
    }

    @Override
    public Map<String, Object> getWaterOrgDhAndTQ(Map<String, Object> map) {
        return thirdAnalysisDao.getTotalOrgAndTq(map);
    }

    @Override
    public List<Map<String, Object>> getFeedDh(Map<String, Object> map) {
        return thirdAnalysisDao.getFeedDh(map);
    }
    @Override
    public List<Map<String, Object>> getStationDh(Map<String, Object> map) {
        return thirdAnalysisDao.getStationDh(map);
    }

    /**
     *三级页面-表格数据加载
     * sunbinbin
     * @return map
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getTable(Map<String, Object> params) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<>();
        String type1 = "unitType";
        //所有的用能单位类型
        String[] unittype = {"1","2","3","4","5"};
        String  sDate = (null== params.get("startTime")||"".equals( params.get("startTime")))?getYearDate(null, Calendar.DATE, -5): params.get("startTime").toString().substring(0,10);//如果查询条件的开始时间为空，设置默认的开始时间
        String  eDate = getYearDate(sDate,Calendar.DATE,6);
        String lsDate = getYearDate(sDate,Calendar.YEAR, -1);
        String leDate = getYearDate(eDate,Calendar.YEAR, -1);
        //查询时间list
        List<String> clyearList = new ArrayList<String>();//今年日期列表
        List<String> lyearList = new ArrayList<String>();//去年日期列表
        List<String> yearList = new ArrayList<String>();//去年+今年日期列表
        while(!sDate.equals(eDate)){
            clyearList.add(sDate);
            sDate = getYearDate(sDate,Calendar.DATE,1);
        }
        while(!lsDate.equals(leDate)){
            lyearList.add(lsDate);
            lsDate = getYearDate(lsDate,Calendar.DATE,1);
        }
        clyearList.add(eDate);
        lyearList.add(leDate);
        yearList.addAll(lyearList);
        yearList.addAll(clyearList);
        params.put("currentYear",clyearList);
        params.put("lastYear",lyearList);
        params.put("endTime",eDate+" 23:59:59");
        params.put("endTimeTq",leDate+" 23:59:59");
        //根据查询条件，查询相应的数据
        List<Map<String,Object>> listMap = thirdAnalysisDao.getTables(params);
        List<Map<String,Object>> totalMap = thirdAnalysisDao.getTotal(params);

        //数据封装
        if(null !=totalMap && !totalMap.isEmpty()) {
            for (Map data1 : totalMap) {
                if ("1".equals(data1.get(type1))) {
                    result.put("feedTotal", data1);
                }
                if ("2".equals(data1.get(type1))) {
                    result.put("netTotal", data1);
                }
                if ("3".equals(data1.get(type1))) {
                    result.put("stationTotal", data1);
                }
                if ("4".equals(data1.get(type1))) {
                    result.put("lineTotal", data1);
                }
                if ("5".equals(data1.get(type1))) {
                    result.put("roomTotal", data1);
                }
            }
        }else{
            for (String type :unittype) {
                Map<String, Object> temap = new HashMap<>();

                for (int k = 0; k < clyearList.size(); k++) {
                    String key1 = "c" + k;
                    String key2 = "l" + k;
                    String key3 = "p" + k;
                    temap.put(key1,0);
                    temap.put(key2,0);
                    temap.put(key3,0);
                }
                if(type.equals("1")){
                    temap.put(unitName,"源");
                    temap.put(unitType,type);
                    result.put("feedTotal", temap);
                }
                if(type.equals("2")){
                    temap.put(unitName,"网");
                    temap.put(unitType,type);
                    result.put("netTotal", temap);
                }
                if(type.equals("3")){
                    temap.put(unitName,"站");
                    temap.put(unitType,type);
                    result.put("stationTotal", temap);
                }
                if(type.equals("4")){
                    temap.put(unitName,"线");
                    temap.put(unitType,type);
                    result.put("lineTotal", temap);
                }
                if(type.equals("5")){
                    temap.put(unitName,"户");
                    temap.put(unitType,type);
                    result.put("roomTotal", temap);
                }

            }

        }
        result.put("list",listMap);
        result.put("dates",clyearList);

        return result;
    }
    /**
     * 返回想要的日期
     * 例如：getYearDate（2017-01-01，Calendar.YEAR，-1），返回值为 2016-01-01
     *     getYearDate（2017-01-11，Calendar.DATE，-1），返回值为 2017-01-10
     * @param curDate 元数据，在curDate的基础上获取想要的具体日期str
     * @param type 类型，Calendar.YEAR,Calendar.MONTH...
     * @param num 操作数
     * @return
     */
    private String getYearDate(String curDate,int type,int num) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        curDate = (null == curDate||"".equals(curDate))?dateDao.getDate():curDate;
        Date date = sdf.parse(curDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, num);
        date = calendar.getTime();
        return sdf.format(date);
    }

    @Override
    public List<Map<String, Object>> getFgsDhDetail(Map<String, Object> map) {
        return thirdAnalysisDao.getFgsDh(map);
    }

    @Override
    public Map<String, Object> getFgsDhAndTQ(Map<String, Object> map) {
        return thirdAnalysisDao.getFgsZdhAndTq(map);
    }

    @Override
    public List<Map<String, Object>> getFgsFeedDh(Map<String, Object> map) {
        return thirdAnalysisDao.getFgsFeedDh(map);
    }

    @Override
    public List<Map<String, Object>> getFgsStationDh(Map<String, Object> map) {
        return thirdAnalysisDao.getFgsStationDh(map);
    }

    @Override
    public Map<String, Object> getFgsOrgDh(Map<String, Object> map) {
        List<Map<String, Object>> list = thirdAnalysisDao.getFgsOrgDh(map);
        //时间数据
        List<String> dateLine = new ArrayList<>();
        //水电气热煤的  封装数据

        List<String> waterBq = new ArrayList<>();
        List<String> waterTq = new ArrayList<>();
        List<String> electricBq = new ArrayList<>();
        List<String> electricTq = new ArrayList<>();
        List<String> gasBq = new ArrayList<>();
        List<String> gasTq = new ArrayList<>();
        List<String> hotBq = new ArrayList<>();
        List<String> hotTq = new ArrayList<>();
        List<String> coalBq = new ArrayList<>();
        List<String> coalTq = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> mapData =list.get(i);
            //水的数据
            String newWater = mapData.get("waterBq").toString();
            waterBq.add(newWater);
            String oldWater = mapData.get("waterTq").toString();
            waterTq.add(oldWater);
            //电的数据
            String newElectric = mapData.get("electricBq").toString();
            electricBq.add(newElectric);
            String oldElectric = mapData.get("electricTq").toString();
            electricTq.add(oldElectric);
            //气的数据
            String newGas = mapData.get("gasBq").toString();
            gasBq.add(newGas);
            String oldGas = mapData.get("gasTq").toString();
            gasTq.add(oldGas);
            //热的数据
            String newLineWater = mapData.get("hotBq").toString();
            hotBq.add(newLineWater);
            String oldLineWater = mapData.get("hotTq").toString();
            hotTq.add(oldLineWater);
            //煤的数据
            String newCoal = mapData.get("coalBq").toString();
            coalBq.add(newCoal);
            String oldCoal = mapData.get("coalTq").toString();
            coalTq.add(oldCoal);

            String date = mapData.get("TIME").toString();
            dateLine.add(date);
        }

        Map<String,Object> resulMap=new  HashMap<String,Object>();
        resulMap.put("waterBq",waterBq);
        resulMap.put("waterTq",waterTq);
        resulMap.put("electricBq",electricBq);
        resulMap.put("electricTq",electricTq);
        resulMap.put("gasBq",gasBq);
        resulMap.put("gasTq",gasTq);
        resulMap.put("hotBq",hotBq);
        resulMap.put("hotTq",hotTq);
        resulMap.put("coalBq",coalBq);
        resulMap.put("coalTq",coalTq);
        resulMap.put("dateLine",dateLine);
        return resulMap;
    }

    @Override
    public Map<String, Object> getFgsOrgDhAndTQ(Map<String, Object> map) {
        return thirdAnalysisDao.getFgsOrgAndTq(map);
    }


    /**
     *三级页面-用能单位类型-同期计划数据表格加载
     * sunbinbin
     * @return List
     */
    @Override
    public Map<String,Object> getThirdTables(Map paramsMap) {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> data = thirdAnalysisDao.getThirdTables(paramsMap);
        List<Map<String,Object>> totalMap = thirdAnalysisDao.getThirdTotals(paramsMap);
        //数据封装
        if(null !=totalMap && !totalMap.isEmpty()) {
            for (Map data1 : totalMap) {
                if ("1".equals(data1.get(UNIT_TYPE))) {
                    result.put("feedTotal", data1);
                }
                if ("2".equals(data1.get(UNIT_TYPE))) {
                    result.put("netTotal", data1);
                }
                if ("3".equals(data1.get(UNIT_TYPE))) {
                    result.put("stationTotal", data1);
                }
                if ("4".equals(data1.get(UNIT_TYPE))) {
                    result.put("lineTotal", data1);
                }
                if ("5".equals(data1.get(UNIT_TYPE))) {
                    result.put("roomTotal", data1);
                }
            }
        }else{
            Map<String, Object> temap = new HashMap<>();

            temap.put("w",0);
            temap.put("w1",0);
            temap.put("w_plan",0);
            temap.put("e",0);
            temap.put("e1",0);
            temap.put("e_plan",0);
            temap.put("g",0);
            temap.put("g1",0);
            temap.put("g_plan",0);
            temap.put("h",0);
            temap.put("h1",0);
            temap.put("h_plan",0);
            temap.put("c",0);
            temap.put("c1",0);
            temap.put("c_plan",0);
            temap.put("l",0);
            temap.put("l1",0);
            temap.put("l_plan",0);
            for (String type :UNITTYPE) {
                if(type.equals("1")){
                    temap.put(UNIT_NAME,"源");
                    temap.put(UNIT_TYPE,type);
                    result.put("feedTotal", temap);
                }
                if(type.equals("2")){
                    temap.put(UNIT_NAME,"网");
                    temap.put(UNIT_TYPE,type);
                    result.put("netTotal", temap);
                }
                if(type.equals("3")){
                    temap.put(UNIT_NAME,"站");
                    temap.put(UNIT_TYPE,type);
                    result.put("stationTotal", temap);
                }
                if(type.equals("4")){
                    temap.put(UNIT_NAME,"线");
                    temap.put(UNIT_TYPE,type);
                    result.put("lineTotal", temap);
                }
                if(type.equals("5")){
                    temap.put(UNIT_NAME,"户");
                    temap.put(UNIT_TYPE,type);
                    result.put("roomTotal", temap);
                }

            }

        }
        result.put("dates",TYPE_NAME);
        result.put("list", null==data?new ArrayList<Map<String, Object>>():data);
        return result;
    }
}
