package com.huak.home.dao.thiredpage;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home.dao.thiredpage<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/11<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Repository
public interface ThirdAnalysisDao {

    List<Map<String,Object>> getWaterDhDetail(Map<String,Object> map);

    List<Map<String,Object>> getWaterDhOrg(Map<String,Object> map);

    Map<String,Object> getTotalAndTq(Map<String,Object> map);

    Map<String,Object> getTotalOrgAndTq(Map<String,Object> map);

    List<Map<String,Object>> getFeedDh(Map<String,Object> map);

    List<Map<String,Object>> getStationDh(Map<String,Object> map);

    List<Map<String,Object>> getTables(Map<String, Object> params);

    List<Map<String,Object>> getTotal(Map<String, Object> params);

    List<Map<String,Object>> getFgsDh(Map<String,Object> map);

    Map<String,Object> getFgsZdhAndTq(Map<String,Object> map);
}
