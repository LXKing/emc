package com.huak.prst;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.mdc.dao.RecordPrestoreDao;
import com.huak.mdc.model.RecordPrestore;
import com.huak.mdc.vo.PrestoreA;

import javax.annotation.Resource;
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

    @Resource
    private RecordPrestoreDao prestoreDao;
    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(RecordPrestore record) {
        return 0;
    }

    @Override
    public int insertSelective(RecordPrestore record) {
        return 0;
    }

    @Override
    public RecordPrestore selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RecordPrestore record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RecordPrestore record) {
        return 0;
    }

    @Override
    public PageResult<PrestoreA> queryByPage(Map<String, Object> paramsMap,Page page) {

        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(prestoreDao.selectPageByMap(paramsMap));
    }

}
