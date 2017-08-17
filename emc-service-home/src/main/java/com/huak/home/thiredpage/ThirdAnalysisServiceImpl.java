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
        String type = "unitType";
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

        for (Map data1 : totalMap) {
            if ("1".equals(data1.get(type))) {
                result.put("feedTotal", data1);
            }
            if ("2".equals(data1.get(type))) {
                result.put("netTotal", data1);
            }
            if ("3".equals(data1.get(type))) {
                result.put("stationTotal", data1);
            }
            if ("4".equals(data1.get(type))) {
                result.put("lineTotal", data1);
            }
            if ("5".equals(data1.get(type))) {
                result.put("roomTotal", data1);
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
}
