package com.huak.prst;

import com.huak.mdc.model.Prestore;
import com.huak.mdc.vo.PrestoreA;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.prst<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/31<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface PrestoreService {
    int deleteByPrimaryKey(String id);

    int insert(Prestore record);

    int insertSelective(Prestore record);

    Prestore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Prestore record);

    int updateByPrimaryKey(Prestore record);

    List<PrestoreA> selectPageByMap(Map<String,Object> paramsMap);

}
