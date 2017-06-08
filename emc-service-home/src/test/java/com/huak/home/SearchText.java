package com.huak.home;

import com.huak.base.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.home<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/6/8<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class SearchText extends BaseTest{
    @Resource
    private SearchService searchService;
    @Test
    public void findOrgs(){
        String comId = "40a6bfd44863406e8356bbcfe879fd70";
        List<Map<String, Object>> orgs = searchService.getOrgList(comId);
        System.out.print("");
    }
}
