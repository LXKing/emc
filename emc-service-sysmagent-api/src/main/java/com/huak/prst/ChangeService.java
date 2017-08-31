package com.huak.prst;

import com.huak.mdc.model.Change;

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

    int insert(Change record);

    int insertSelective(Change record);

    Change selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Change record);

    int updateByPrimaryKey(Change record);
}
