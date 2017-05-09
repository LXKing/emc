package com.huak.auth;

import com.huak.auth.model.Role;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;

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
public interface RoleService {
    public PageResult<Role> queryByPage(String name, Page page);
}
