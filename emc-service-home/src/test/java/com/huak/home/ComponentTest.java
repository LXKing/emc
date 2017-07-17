package com.huak.home;

import com.huak.base.BaseTest;
import com.huak.home.component.ComponentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/7/17.
 */
public class ComponentTest  extends BaseTest {
    @Autowired
    private ComponentService componentService;

    @Test
    public void execute(){
        Map<String,Object> params = new HashMap<>();
        params.put("code","101030100");
        params.put("status","0");
        Map<String,Object> map =  componentService.weatherForcast(params);
    }
}
