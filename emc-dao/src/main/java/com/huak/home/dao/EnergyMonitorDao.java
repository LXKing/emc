package com.huak.home.dao;

import com.huak.home.model.EnergyMonitor;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnergyMonitorDao {

	/**
	 * 根据查询条件，查询相应数据，返回List<Map>格式
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> groupEnergy(Map<String, String> params);

    void insertByPrimaryKeySelective(EnergyMonitor energyMonitor);

    /**
     * 查询能源流明细
     * @param params
     * @return
     */
	List<Map<String, Object>> energyFlowTable(Map<String, String> params);

	/**
	 * 查询能源流占比分布图
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> energyFlowPie(Map<String, String> params);

	/**
	 * 查询能源流趋势对比图
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> energyFlowLine(Map<String, String> params);

	/**
	 * 查询能源流同比
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> energyFlowBar(Map<String, String> params);

}
