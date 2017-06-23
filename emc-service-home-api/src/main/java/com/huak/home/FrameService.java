package com.huak.home;

import com.huak.home.model.EnergyDetail;
import com.huak.org.model.vo.CostVo;

import java.util.List;
import java.util.Map;
/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/16<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface FrameService {


    public String selectTopEtotalByMap(Map<String, String> paramsMap);

    public String selectCarbonTotalByMap(Map<String, String> paramsMap);

    public CostVo selectCostTotalByMap(Map<String, String> paramsMap);

    public String selectYardageByMap(Map<String, String> paramsMap);

    public String selectPriceAreaByMap(Map<String, String> paramsMap);

    public String selectFeedTotalByMap(Map<String, String> paramsMap);

    public String selectTopFeedCarbonTotalByMap(Map<String, String> paramsMap);

    public CostVo selectFeedCostTotalByMap(Map<String, String> paramsMap);

    public String selectGetNetLengh(Map<String, String> paramsMap);

    public CostVo selectGetNetCost(Map<String, String> paramsMap);

    public String selectTopStationTotalByMap(Map<String, String> paramsMap);

    public String selectTopStationCarbonTotalByMap(Map<String, String> paramsMap);

    public CostVo selectStationCostTotalByMap(Map<String, String> paramsMap);

    public String selectGetLineLengh(Map<String, String> paramsMap);

    public CostVo selectGetLineCost(Map<String, String> paramsMap);

    public String selectTopRoomTotalByMap(Map<String, String> paramsMap);

    public String selectTopRoomHglByMap(Map<String, String> paramsMap);

    public CostVo getTopRoomCostByMap(Map<String, String> paramsMap);

    public List<EnergyDetail> selectEnergyDetail(Map<String, Object> paramsMap);

    public List<Map<String, Object>> selectEnergyProportion(Map<String, Object> params);

    public List<Map<String, Object>> selectEnergyTrend(Map<String, Object> params);

    public List<Map<String, Object>> selectEnergyTong(Map<String, Object> params);

    public List<EnergyDetail> exportEnergyDetail(Map<String, Object> params);


}
