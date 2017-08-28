package com.huak.mdc;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.mdc.dao.MeterCollectDao;
import com.huak.mdc.model.MeterCollect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class MeterCollectServiceImpl implements MeterCollectService {

    @Resource
    MeterCollectDao meterCollectDao;
    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insertSelective(MeterCollect record) {
        return meterCollectDao.insertSelective(record);
    }

    @Override
    public int insert(MeterCollect record) {
        return meterCollectDao.insert(record);
    }

    @Override
    public MeterCollect selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(MeterCollect record) {
        return 0;
    }

    @Override
    public PageResult<MeterCollect> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(meterCollectDao.selectPageByMap(paramsMap));
    }
}
