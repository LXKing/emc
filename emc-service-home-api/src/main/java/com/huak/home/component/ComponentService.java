package com.huak.home.component;

import java.util.Map;

/**
 * Created by MR-BIN on 2017/6/8.
 */
public interface ComponentService {

    /**
     * 能耗明细查询
     */
    Map<String,Object> energyDetail(Map<String,Object> params);

    /**
     * 成本明细
     * @param params
     * @return
     */
    Map<String,Object> costDetail(Map<String, Object> params);

    /**
     * 单耗趋势
     * @param params
     * @return
     */
    Map<String,Object> energycomparison(Map<String, Object> params);

    /**
     * 天气预测和历史
     * @param params
     * @return
     */
    Map<String,Object> weatherForcast(Map<String, Object> params);

    /**
     * 室温散点图数据查询
     * @param params
     * @return
     */
    Map<String,Object> roomTemperature(Map<String, Object> params);

    /**
     * 近期单耗详情
     * @param paramsMap
     * @return
     */
    java.util.List<Map<String, Object>> selectrecentDetail(Map<String, Object> paramsMap);
}
