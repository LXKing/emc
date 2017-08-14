package com.huak.org;


import com.huak.base.BaseTest;
import com.huak.base.dao.DateDao;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Node;
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
public class TestOrgService extends BaseTest {
    @Resource
    private NodeService nodeService;
    @Resource
    private DateDao dateDao;

    @Resource
    private OrgService orgService;

    //@Test
    @Rollback
    public void testDeleteByPrimaryKey() {
        nodeService.deleteByPrimaryKey("1");
    }

    //@Test
    @Rollback
    public void testInsertSelective() {
        // Do nothing because of temporary use.
    }

    @Test
    @Rollback
    public void testSelectOrgByMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("comId",1);
        List<Map<String,Object>> dat = orgService.selectOrgTree(map);
        for(Map da : dat){
           System.out.println(da.get("orgName"));
        }
    }

    @Test
    @Rollback
    public void testSelectByPrimaryKey() {

       Node vo = nodeService.selectById("192d30fa0924427398ca433adbe9a44b");
        System.out.println(vo.getStationName());
    }

    @Test
    @Rollback
    public void testUpdateByPrimaryKeySelective() {
        Node vo = new Node();
        nodeService.update(vo);
    }

    @Test
    @Rollback
    public void testQueryByPage() {
        Map paramsMap = new HashMap<String, Object>();
        paramsMap.put("pOrgId","pOrgId=c758f969c0a4471eb95ce90617013e92");
        paramsMap.put("pageSize","10");

        PageResult<Node> nodes= nodeService.queryByPage(paramsMap,new Page());
        for (Node vo : nodes.getList()) {
            System.err.println(vo.getStationName());
        }
    }

    @Test
    @Rollback
    public void testExportRoles() {
        Map paramsMap = new HashMap<String, Object>();

    }

    @Test
    @Rollback
    public void testInsert(){
        Node node = new Node();
        node.setId(UUIDGenerator.getUUID());
        node.setManageTypeId((byte)2);
        node.setProvinceId("440000000000");
        node.setTownId("445203002000");
        node.setCityId("445200000000");
        node.setCountyId("445203000000");
        node.setVillageId(null);
        node.setAddr("123");
        node.setLng(123.0);
        node.setLat(123.0);
        node.setStationCode("123");
        node.setStationName("123");
        node.setHeatArea(123.0);
        node.setOrgId(Long.parseLong("2"));
        node.setComId("40a6bfd44863406e8356bbcfe879fd70");
        nodeService.insert(node);

    }
}
