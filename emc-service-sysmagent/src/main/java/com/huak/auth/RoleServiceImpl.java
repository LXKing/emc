package com.huak.auth;

import com.github.pagehelper.PageHelper;
import com.huak.auth.dao.RoleDao;
import com.huak.auth.model.Role;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.season.dao.SeasonDao;
import com.huak.season.model.Season;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/8<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    @Transactional(readOnly = true)
    public PageResult<Role> queryByPage(String roleName, Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        return Convert.convert(roleDao.selectPageByName(roleName));
    }
}
