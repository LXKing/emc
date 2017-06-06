package com.huak.home.dao;

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

}
