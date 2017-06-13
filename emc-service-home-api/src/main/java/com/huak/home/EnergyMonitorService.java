package com.huak.home;

import com.huak.home.model.EnergyMonitor;
import com.huak.home.model.EnergySecond;

import java.util.List;
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

    /**
     * 添加测试数据
     * @param energyMonitor
     */
    void insertByPrimaryKeySelective(EnergyMonitor energyMonitor);


    /**
     * 查询分公司列表
     * @param params
     * @return
     */
    public List<EnergySecond> findAssessmentIndicators(Map<String, Object> params);

    /**
     * 分公司能耗占比分布图
     * @param params
     * @return
     */
    public List<EnergySecond> fgsEnergyRatio(Map<String, Object> params);

}
