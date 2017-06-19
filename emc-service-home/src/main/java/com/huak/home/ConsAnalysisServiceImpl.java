package com.huak.home;

import com.huak.home.dao.ConsAnalysisDao;
import com.huak.home.model.ConsSecond;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/5<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class ConsAnalysisServiceImpl implements ConsAnalysisService {
    @Resource
    private ConsAnalysisDao consAnalysisDao;

    /**
     * 查询分公司列表
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConsSecond> findAssessmentIndicators(Map<String, Object> params) {
        return consAnalysisDao.findAssessmentIndicators(params);
    }

    /**
     * 分公司单耗占比分布图
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> fgsEnergyRatio(Map<String, Object> params) {
        return consAnalysisDao.fgsEnergyRatio(params);
    }

    /**
     * 分公司单耗趋势对比图
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> fgsEnergyTrend(Map<String, Object> params) {
        return consAnalysisDao.fgsEnergyTrend(params);
    }

    /**
     * 分公司单耗同比
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> fgsEnergyAn(Map<String, Object> params) {
        return consAnalysisDao.fgsEnergyAn(params);
    }

    /**
     * 分公司单耗排名
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> fgsEnergyRanking(Map<String, Object> params) {
        return consAnalysisDao.fgsEnergyRanking(params);
    }

    /**
     * 导出公司单耗列表
     *
     * @param params
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConsSecond> exportAssessmentIndicators(Map<String, Object> params) {
        return consAnalysisDao.exportAssessmentIndicators(params);
    }
}
