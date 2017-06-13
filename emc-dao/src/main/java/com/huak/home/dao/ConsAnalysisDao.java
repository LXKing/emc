package com.huak.home.dao;

import com.huak.home.model.EnergySecond;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.energy.dao<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/26<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Repository
public interface ConsAnalysisDao {
    /**
     * 查询分公司能耗明细
     * @param params
     * @return
     */
    public List<EnergySecond> findFilialeDetail(Map<String, Object> params);

    /**
     * 查询能源流明细
     * @param params
     * @return
     */
    public List<EnergySecond> findFolwDetail(Map<String, Object> params);

    /**
     * 分公司能耗占比分布图
     * @param params
     * @return
     */
    public Map<String, Object> findFgsRatio(Map<String, Object> params);

    /**
     * 分公司能耗趋势对比图
     * @param params
     * @return
     */
    public Map<String, Object> findFgsTrend(Map<String, Object> params);

    /**
     * 分公司能耗同比
     * @param params
     * @return
     */
    public Map<String, Object> findFgsAn(Map<String, Object> params);

    /**
     * 分公司能耗排名
     * @param params
     * @return
     */
    public Map<String, Object> findFgsRanking(Map<String, Object> params);

    /**
     * 能源流能耗占比分布图
     * @param params
     * @return
     */
    public Map<String, Object> findNylRatio(Map<String, Object> params);

    /**
     * 能源流能耗趋势对比图
     * @param params
     * @return
     */
    public Map<String, Object> findNylTrend(Map<String, Object> params);

    /**
     * 能源流能耗同比
     * @param params
     * @return
     */
    public Map<String, Object> findNylAn(Map<String, Object> params);
}
