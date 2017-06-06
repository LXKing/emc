package com.huak.home;

import java.util.Map;

public interface EnergyMonitorService {

	/**
	 * 查询折线数据
	 * @param params
	 * @return
	 */
	Map<String, Object> groupEnergyLine(Map<String, String> params);

	/**
	 * 跳转到此页面前查询相关数据
	 * @return
	 */
	Map<String, Object> groupEnergy2Day();

}
