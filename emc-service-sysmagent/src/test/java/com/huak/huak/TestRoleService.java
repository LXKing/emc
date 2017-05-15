package com.huak.huak;

import com.huak.auth.RoleService;
import com.huak.auth.model.Role;
import com.huak.base.BaseTest;
import com.huak.base.dao.DateDao;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private DateDao dateDao;

    //@Test
    @Rollback
    public void testDeleteByPrimaryKey() {
        roleService.deleteByPrimaryKey("1");
    }

    //@Test
    @Rollback
    public void testInsertSelective() {
        Role role = new Role();
        role.setId(UUIDGenerator.getUUID());
        role.setRoleName("系统管理员");
        role.setRoleDes("喂喂喂额问问");
        role.setMemo("这是说明");
        role.setCreator("1");
        role.setCreateTime(dateDao.getTime());
        roleService.insertSelective(role);
    }

    //@Test
    @Rollback
    public void testSelectByPrimaryKey() {
        Role role = roleService.selectByPrimaryKey("1");
        System.err.println(role.getRoleName());
    }

    //@Test
    @Rollback
    public void testUpdateByPrimaryKeySelective() {
        Role role = new Role();
        role.setId("1");
        role.setRoleDes("修改的");
        roleService.updateByPrimaryKeySelective(role);
    }

    //@Test
    @Rollback
    public void testQueryByPage() {
        Map paramsMap = new HashMap<String, Object>();
        Page page = new Page();
        page.setPageNumber(2);

        PageResult<Role> roles = roleService.queryByPage(paramsMap, page);
        for (Role role : roles.getList()) {
            System.err.println(role.getRoleName());
        }
    }

    @Test
    @Rollback
    public void testExportRoles() {
        Map paramsMap = new HashMap<String, Object>();

        List<Map<String, Object>> roles = roleService.exportRoles(paramsMap);
        for (Map<String, Object> role : roles) {
            System.err.println(role.get("role_name"));
        }
    }

}
