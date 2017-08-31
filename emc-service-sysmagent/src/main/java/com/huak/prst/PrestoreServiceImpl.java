package com.huak.prst;

import com.alibaba.dubbo.config.annotation.Service;
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

@Service
public class PrestoreServiceImpl implements PrestoreService {
    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Prestore record) {
        return 0;
    }

    @Override
    public int insertSelective(Prestore record) {
        return 0;
    }

    @Override
    public Prestore selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Prestore record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Prestore record) {
        return 0;
    }

    @Override
    public List<PrestoreA> selectPageByMap(Map<String, Object> paramsMap) {
        return null;
    }
}
