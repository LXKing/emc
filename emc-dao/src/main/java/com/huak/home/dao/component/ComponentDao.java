package com.huak.home.dao.component;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ComponentDao {

	/**
	 * 根据查询条件，查询相应数据，返回List<Map>格式
	 * @param params
	 * @return
	 */
	Map<String, Object> energyDetail(Map<String, Object> params);

    /**
     * 根据参数查询能耗计划用量
     * @param params
     * @return
     */
    Map<String,Object> getplan(Map<String, Object> params);

    /**
     * 获取本采暖季的时间
     * @param paramsMap
     * @return
     */
    Map<String,Object> getCurrentSeason(Map<String, Object> paramsMap);

    /**
     * 获取上个采暖季的时间
     * @param paramsMap
     * @return
     */
    List<Map<String,Object>> getPreSeason(Map<String, Object> paramsMap);
}
