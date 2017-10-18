package com.huak.weather;

import com.huak.base.BaseTest;
import com.huak.diacrisis.WorkWarnService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.weather<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/26<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

public class TestWorkWarn extends BaseTest {

    @Autowired
    private WorkWarnService wrkWarnService;

    @Test
    public void testWeather(){
        wrkWarnService.executeWarning();

    }
}
