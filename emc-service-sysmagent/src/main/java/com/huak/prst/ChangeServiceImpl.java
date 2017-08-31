package com.huak.prst;

import com.alibaba.dubbo.config.annotation.Service;
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

@Service
public class ChangeServiceImpl implements ChangeService {

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Change record) {
        return 0;
    }

    @Override
    public int insertSelective(Change record) {
        return 0;
    }

    @Override
    public Change selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Change record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Change record) {
        return 0;
    }
}
