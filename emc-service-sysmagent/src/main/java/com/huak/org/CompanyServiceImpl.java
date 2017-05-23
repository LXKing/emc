package com.huak.org;

import com.github.pagehelper.PageHelper;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.dao.CompanyDao;
import com.huak.org.model.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.org<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/23<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyDao companyDao;

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return companyDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertSelective(Company record) {
        return companyDao.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public Company selectByPrimaryKey(String id) {
        return companyDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Company record) {
        return companyDao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<Company> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(companyDao.selectPageByMap(paramsMap));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> exportCompanys(Map<String, Object> paramsMap) {
        return companyDao.exportCompanys(paramsMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> selectAllByMap(Map<String, Object> paramsMap) {
        return companyDao.selectAllByMap(paramsMap);
    }
}
