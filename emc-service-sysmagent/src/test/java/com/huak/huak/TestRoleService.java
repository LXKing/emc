package com.huak.huak;

import com.huak.auth.RoleService;
import com.huak.auth.model.Role;
import com.huak.base.BaseTest;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.huak<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/9<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class TestRoleService extends BaseTest {
    @Resource
    private RoleService roleService;
    @Test
    @Rollback
    public void testSelectPage(){
        PageResult<Role> roles = roleService.queryByPage("采暖",new Page());
        for (Role role : roles.getList()){
            System.err.println(role.getRoleName());
        }
    }
}
