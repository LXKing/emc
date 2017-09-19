package com.huak.health;

import com.huak.health.model.MetaCompany;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.health<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/19<BR>
 * Description:   点名公司关系  <BR>
 * Function List:  <BR>
 */
public interface MetaCompanyService {
    int deleteByPrimaryKey(String tag);

    int insert(MetaCompany record);

    int insertSelective(MetaCompany record);

    MetaCompany selectByPrimaryKey(String tag);

    int updateByPrimaryKeySelective(MetaCompany record);

    int updateByPrimaryKey(MetaCompany record);
}
