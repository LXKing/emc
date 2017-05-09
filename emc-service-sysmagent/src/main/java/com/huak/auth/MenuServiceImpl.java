package com.huak.auth;

import com.github.pagehelper.PageHelper;
import com.huak.auth.dao.MenuDao;
import com.huak.auth.model.Menu;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;

import javax.annotation.Resource;

/**
 * Created by MR-BIN on 2017/5/8.
 */
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuDao menuDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        return menuDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Menu record) {
        return menuDao.insert(record);
    }

    @Override
    public Menu selectByPrimaryKey(String id) {
        return menuDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return menuDao.updateByPrimaryKey(record);
    }

    @Override
    public PageResult<Menu> queryByPage( Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        return Convert.convert(menuDao.selectPageByName());
    }
}
