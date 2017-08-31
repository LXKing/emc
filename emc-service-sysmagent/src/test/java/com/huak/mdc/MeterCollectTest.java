package com.huak.mdc;

import com.huak.base.BaseTest;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.io.IOException;

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
public class MeterCollectTest extends BaseTest {
    @Resource
    private MeterCollectService meterCollectService;

    @Test
    @Rollback
    public void testSelectDemo() throws IOException {

        String path = "D:\\workSp\\code\\upload\\计量器具.xlsx";
        meterCollectService.excelUpload( path,"计量器具.xlsx");
    }
}
