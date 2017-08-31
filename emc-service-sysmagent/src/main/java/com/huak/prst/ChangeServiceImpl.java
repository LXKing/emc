package com.huak.prst;

import com.alibaba.dubbo.config.annotation.Service;
import com.huak.mdc.dao.RecordChangeDao;
import com.huak.mdc.model.RecordChange;

import javax.annotation.Resource;

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
    @Resource
    RecordChangeDao recordChangeDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(RecordChange record) {
        return recordChangeDao.insertSelective(record);
    }

    @Override
    public int insertSelective(RecordChange record) {
        return 0;
    }

    @Override
    public RecordChange selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RecordChange record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RecordChange record) {
        return 0;
    }
}
