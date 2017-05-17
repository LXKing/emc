package com.huak.sys;

import com.huak.base.BaseTest;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.sys<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/17<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class SysDicServiceTest  extends BaseTest {
    @Resource
    private SysDicService sysDicService;

    @Test
    @Rollback
    public void testSelectDemo(){
        List<Map<String, Object>> list = sysDicService.queryGroup(new HashMap<String, Object>());
        for (Map<String , Object> map: list){
            String typeUs = map.get("typeUs").toString();
        }
    }
}
