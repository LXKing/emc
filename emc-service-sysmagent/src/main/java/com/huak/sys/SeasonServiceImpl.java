package com.huak.sys;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.season.dao.SeasonDao;
import com.huak.season.model.Season;
import com.huak.sys.SeasonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.reason.impl<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/4/24<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class SeasonServiceImpl implements SeasonService {
    @Resource
    private SeasonDao seasonDao;

    public Season selectByPrimaryKey(String id){
       return seasonDao.selectByPrimaryKey(id);
    }

    public int delete(String id){
        return seasonDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(Season season) {
        return seasonDao.insert(season);
    }

//    @Override
//    public PageResult<Season> queryByPage(String name, Page page) {
//        PageHelper.startPage(page.getPageNumber(),page.getPageSize());
//        return Convert.convert(seasonDao.selectPageByName(name));
//    }
    @Override
    @Transactional(readOnly = true)
    public PageResult<Season> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(seasonDao.selectPageByMap(paramsMap));
    }

}
