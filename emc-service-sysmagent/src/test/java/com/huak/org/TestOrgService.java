package com.huak.org;


import com.huak.base.BaseTest;
import com.huak.base.dao.DateDao;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.vo.NodeVo;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

import java.util.*;

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
public class TestOrgService extends BaseTest {
    @Resource
    private NodeService nodeService;
    @Resource
    private DateDao dateDao;

    //@Test
    @Rollback
    public void testDeleteByPrimaryKey() {
        nodeService.deleteByPrimaryKey("1");
    }

    //@Test
    @Rollback
    public void testInsertSelective() {

    }

    @Test
    @Rollback
    public void testSelectByPrimaryKey() {
       NodeVo vo = nodeService.selectVoById("192d30fa0924427398ca433adbe9a44b");
        System.out.println(vo.getOrgName());
    }

    @Test
    @Rollback
    public void testUpdateByPrimaryKeySelective() {
        NodeVo vo = new NodeVo();
        vo.setId("083a5016044f498cb1089b5bf3f06d47");
        vo.setOrgCode("98888");
        vo.setOrgName("测试");
        vo.setManageTypeId("b634bf7a2a38401290c59fd5486ed370");
        vo.setProvinceId("110000000000");
        vo.setCityId("110100000000");
        vo.setCountyId("110102000000");
        vo.setTownId("110102001000");
        vo.setAddr("22222");
        vo.setDwellArea(11.0);
        vo.setPublicArea(12.0);
        nodeService.update(vo);
    }

    @Test
    @Rollback
    public void testQueryByPage() {
        Map paramsMap = new HashMap<String, Object>();
        paramsMap.put("pOrgId","pOrgId=c758f969c0a4471eb95ce90617013e92");
        paramsMap.put("pageSize","10");

        PageResult<NodeVo> nodes= nodeService.queryByPage(paramsMap,new Page());
        for (NodeVo vo : nodes.getList()) {
            System.err.println(vo.getOrgName());
        }
    }

    @Test
    @Rollback
    public void testExportRoles() {
        Map paramsMap = new HashMap<String, Object>();

    }

}
