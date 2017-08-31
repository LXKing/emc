package com.huak.prst;

import com.huak.mdc.model.RecordChange;

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
public interface ChangeService {
    int deleteByPrimaryKey(String id);

    int insert(RecordChange record);

    int insertSelective(RecordChange record);

    RecordChange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RecordChange record);

    int updateByPrimaryKey(RecordChange record);
}
