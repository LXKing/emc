package com.huak.auth;

import com.github.pagehelper.PageHelper;
import com.huak.auth.dao.RoleDao;
import com.huak.auth.model.Role;
import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

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
    @Resource
    private DateDao dateDao;

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(String id) {
        return roleDao.deleteByPrimaryKey(id);
    }

    /**
     * 插入
     * @param record
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int insertSelective(Role record) {
        record.setCreateTime(dateDao.getTime());
        return roleDao.insertSelective(record);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Role selectByPrimaryKey(String id) {
        return roleDao.selectByPrimaryKey(id);
    }

    /**
     * 根据id修改
     * @param record
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Role record) {
        return roleDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 角色分页查询
     * @param paramsMap
     * @param page
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public PageResult<Role> queryByPage(Map<String,Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        return Convert.convert(roleDao.selectPageByMap(paramsMap));
    }
}
