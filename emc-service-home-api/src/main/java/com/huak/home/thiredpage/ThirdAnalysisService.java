package com.huak.home.thiredpage;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.thiredpage<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/11<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface ThirdAnalysisService {

    /**
     * 三级页面水能耗明细查询
     */
    public List<Map<String,Object>> getWaterDhDetail(Map<String,Object> map);
    /**
     * 三级页面水能耗明细查询 总值和同比
     */
    public Map<String,Object> getWaterDhAndTQ(Map<String,Object> map);

    /**
     * 三级页面水能耗 (源，网，站，线，户)
     */
    public Map<String,Object> getWaterDhOrg(Map<String,Object> map);
    /**
     * 三级页面水能耗 (源，网，站，线，户) 的总单耗和同比
     */
    public Map<String,Object> getWaterOrgDhAndTQ(Map<String,Object> map);
    /**
     * 热源的水单耗
     */
    public List<Map<String,Object>> getFeedDh(Map<String,Object> map);

    /**
     * 换热站的的水单耗
     */
    public List<Map<String,Object>> getStationDh(Map<String,Object> map);

    /**
     *三级页面-表单
     * sunbinbin
     * @return map
     */
    Map<String, Object> getTable(Map<String, Object> params) throws Exception;

    /**
     * 三级页面分公司耗明细查询
     */
    public List<Map<String,Object>> getFgsDhDetail(Map<String,Object> map);

    /**
     * 三级页面分公司能耗明细查询 总值和同比
     */
    public Map<String,Object> getFgsDhAndTQ(Map<String,Object> map);


}
