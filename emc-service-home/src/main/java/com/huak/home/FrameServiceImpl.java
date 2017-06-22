package com.huak.home;

import com.huak.home.dao.EnergyDetailDao;
import com.huak.home.model.EnergyDetail;
import com.huak.org.dao.TopAllDao;
import com.huak.org.model.vo.CostVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/25<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Service
public class FrameServiceImpl implements FrameService {

    @Resource
    private TopAllDao topAllDao;
    @Resource
    private EnergyDetailDao detailDao;
    @Override
    public String selectTopEtotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopEtotalByMap(paramsMap);
    }

    @Override
    public String selectCarbonTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectCarbonTotalByMap(paramsMap);
    }

    @Override
    public CostVo selectCostTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectCostTotalByMap(paramsMap);
    }

    @Override
    public String selectYardageByMap(Map<String, String> paramsMap) {
        return topAllDao.selectYardageByMap(paramsMap);
    }

    @Override
    public String selectPriceAreaByMap(Map<String, String> paramsMap) {
        return topAllDao.selectPriceAreaByMap(paramsMap);
    }

    @Override
    public String selectFeedTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopFeedTotalByMap(paramsMap);
    }

    @Override
    public String selectTopFeedCarbonTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopFeedCarbonTotalByMap(paramsMap);
    }

    @Override
    public CostVo selectFeedCostTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectFeedCostTotalByMap(paramsMap);
    }

    @Override
    public String selectGetNetLengh(Map<String, String> paramsMap) {
        return topAllDao.selectGetNetLengh(paramsMap);
    }

    @Override
    public String selectGetNetCost(Map<String, String> paramsMap) {
        return topAllDao.selectGetNetCost(paramsMap);
    }

    @Override
    public String selectTopStationTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopStationTotalByMap(paramsMap);
    }

    @Override
    public String selectTopStationCarbonTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopStationCarbonTotalByMap(paramsMap);
    }

    @Override
    public CostVo selectStationCostTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectStationCostTotalByMap(paramsMap);
    }

    @Override
    public String selectGetLineLengh(Map<String, String> paramsMap) {
        return topAllDao.selectGetLineLengh(paramsMap);
    }

    @Override
    public CostVo selectGetLineCost(Map<String, String> paramsMap) {
        return topAllDao.selectGetLineCost(paramsMap);
    }

    @Override
    public String selectTopRoomHglByMap(Map<String, String> paramsMap) {
        Float f=new Float(0.00);
        String total = topAllDao.selectTopRoomTotalHglByMap(paramsMap);
        String num1 = topAllDao.selectTopRoomNum1HglByMap(paramsMap);
        if(total!=null&&num1!=null){
             f = (float)Integer.valueOf(num1)/(float)Integer.valueOf(total)*100;
        }
        return f+"%";
    }

    @Override
    public String selectTopRoomTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopRoomTotalByMap(paramsMap);
    }

    @Override
    public CostVo getTopRoomCostByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopRoomCostByMap(paramsMap);
    }

    @Override
    public List<EnergyDetail> selectEnergyDetail(Map<String, Object> paramsMap) {
        return detailDao.selectEnergyDetail(paramsMap);
    }

    @Override
    public List<Map<String, Object>> selectEnergyProportion(Map<String, Object> params) {
        return detailDao.selectEnergyProportion(params);
    }

    @Override
    public List<Map<String, Object>> selectEnergyTrend(Map<String, Object> params) {
        return detailDao.selectEnergyTrend(params);
    }

    @Override
    public List<Map<String, Object>> selectEnergyTong(Map<String, Object> params) {
        return detailDao.selectEnergyTong(params);
    }

    @Override
    public List<EnergyDetail> exportEnergyDetail(Map<String, Object> params) {
        return detailDao.exportEnergyDetail(params);
    }
}
