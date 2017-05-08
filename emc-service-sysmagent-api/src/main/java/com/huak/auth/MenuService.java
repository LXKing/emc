package com.huak.auth;

import com.huak.auth.model.Menu;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;

/**
 * Created by MR-BIN on 2017/5/8.
 */
public interface MenuService {

   public int deleteByPrimaryKey(String id);

   public int insert(Menu record);

   public Menu selectByPrimaryKey(String id);

   public int updateByPrimaryKey(Menu record);
   public PageResult<Menu> queryByPage(String name, Page page);
}
