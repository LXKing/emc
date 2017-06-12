package com.huak.home;

import com.huak.org.dao.TopAllDao;
import org.springframework.stereotype.Service;
import com.huak.org.model.vo.CostVo;
import javax.annotation.Resource;

import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/25<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Service
public class FrameServiceImpl implements FrameService {

    @Resource
    private TopAllDao topAllDao;

    @Override
    public String selectTopEtotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopEtotalByMap(paramsMap);
    }

    @Override
    public String selectCarbonTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectCarbonTotalByMap(paramsMap);
    }

    @Override
    public CostVo selectCostTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectCostTotalByMap(paramsMap);
    }

    @Override
    public String selectYardageByMap(Map<String, String> paramsMap) {
        return topAllDao.selectYardageByMap(paramsMap);
    }

    @Override
    public String selectPriceAreaByMap(Map<String, String> paramsMap) {
        return topAllDao.selectPriceAreaByMap(paramsMap);
    }

    @Override
    public String selectFeedTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopFeedTotalByMap(paramsMap);
    }

    @Override
    public String selectTopFeedCarbonTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectTopFeedCarbonTotalByMap(paramsMap);
    }

    @Override
    public CostVo selectFeedCostTotalByMap(Map<String, String> paramsMap) {
        return topAllDao.selectFeedCostTotalByMap(paramsMap);
    }
}
