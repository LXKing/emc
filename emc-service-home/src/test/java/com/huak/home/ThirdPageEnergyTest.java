package com.huak.home;

import com.huak.base.BaseTest;
import com.huak.home.thiredpage.ThiredpageEnergyService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by MR-BIN on 2017/8/11.
 */
public class ThirdPageEnergyTest extends BaseTest {

    @Resource private ThiredpageEnergyService thiredpageEnergyService;

    @Test
    public void testqs () throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("orgId","74");
        params.put("feedType","2");
        params.put("startTime","2016-11-15 00:00:00");
        params.put("endTime","2017-03-15 23:59:59");
        params.put("startTimeTq","2015-11-15 00:00:00");
        params.put("endTimeTq","2016-03-15 23:59:59");
        params.put("type","1");
        params.put("orgType","");
        params.put("energytype","1");
        params.put("comId","a3e5e868e7844c349e5cf51c5e6bc37d");
        params.put("tableName","t_emc_final_data_hour_tj");
        Map<String,Object> data = thiredpageEnergyService.getDatasAll(params);
        System.out.println(data);
    }


    @Test
    public void testTable () throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("orgId","74");
        params.put("feedType","2");
        params.put("startTime","2016-11-15 00:00:00");
        params.put("endTime","2016-11-18 23:59:59");
        params.put("type","1");
        params.put("orgType","");
        params.put("energytype","1");
        params.put("comId","a3e5e868e7844c349e5cf51c5e6bc37d");

        Map<String, Object> data = thiredpageEnergyService.getTable(params);
        System.out.println(data);
    }


    /**
     *三级页面-用能单位类型-同期计划数据表格加载
     * sunbinbin
     * @return List
     */
    @Test
    public void testThirdTbles () throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("orgId","74");
        params.put("feedType","2");
        params.put("startTime","2016-11-15 00:00:00");
        params.put("endTime","2017-03-15 23:59:59");
        params.put("startTimeTq","2015-11-15 00:00:00");
        params.put("endTimeTq","2016-03-15 23:59:59");
        params.put("type","1");
        params.put("orgType","3");
        params.put("energytype","1");
        params.put("comId","a3e5e868e7844c349e5cf51c5e6bc37d");

        Map<String, Object>data = thiredpageEnergyService.getThirdTables(params);
        System.out.println(data);
    }
}
