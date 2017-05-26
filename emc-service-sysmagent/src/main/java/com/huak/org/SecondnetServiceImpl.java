package com.huak.org;

import com.github.pagehelper.PageHelper;
import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.dao.OncenetDao;
import com.huak.org.dao.SecondnetDao;
import com.huak.org.model.vo.Secondnet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Service
public class SecondnetServiceImpl implements SecondnetService {

    @Resource
    private SecondnetDao secondnetDao;

    @Resource
    private DateDao dateDao;

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return secondnetDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(Secondnet record) {
        return secondnetDao.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Secondnet record) {
        return secondnetDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public Secondnet selectByPrimaryKey(String id) {
        return secondnetDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<Secondnet> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(secondnetDao.selectPageByMap(paramsMap));
    }

    @Override
    public List<Map<String, Object>> exportFeeds(Map<String, Object> paramsMap) {
        return null;
    }
}