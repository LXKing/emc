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
	Map<String, Object> groupEnergyLine(Map<String, String> params) throws Exception;

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
    public List<Map<String, Object>> fgsEnergyRatio(Map<String, Object> params);

    /**
     * 分公司能耗趋势对比图
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyTrend(Map<String, Object> params);

    /**
     * 分公司能耗同比
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyAn(Map<String, Object> params);

    /**
     * 分公司能耗排名
     * @param params
     * @return
     */
    public List<Map<String, Object>> fgsEnergyRanking(Map<String, Object> params);

    /**
     * 导出分公司列表
     * @param params
     * @return
     */
    public List<EnergySecond> exportAssessmentIndicators(Map<String, Object> params);

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
	Map<String,Object> energyFlowLine(Map<String, String> params) throws Exception;

	/**
     * 查询能源流同比
     * @param params
     * @return
     */
	Map<String, Object> energyFlowBar(Map<String, String> params);

	/**
	 * 导出能源流明细
	 * @return
	 */
	List<Map<String, Object>> exportEnergyFlowDetail(Map<String,String> params);

}
