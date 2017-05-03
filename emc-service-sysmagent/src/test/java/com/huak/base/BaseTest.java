package com.huak.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lichao on 2016/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = "classpath:applicationContext-service.xml")
public class BaseTest {
    @Before
    public void setUp() throws Exception {


    }
}
