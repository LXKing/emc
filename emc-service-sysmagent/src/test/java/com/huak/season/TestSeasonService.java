package com.huak.season;

import com.huak.base.BaseTest;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.season.model.Season;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  java.com.huak.season<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/4/24<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class TestSeasonService extends BaseTest {
    @Resource
    private SeasonService seasonService;
    @Test
    @Rollback
    public void testSelectDemo(){
        String id = "1";
        Season season = seasonService.selectByPrimaryKey(id);
        System.out.println(season.getName());
    }

    @Test
    @Rollback
    public void testInsert(){
        Season season = new Season();
        season.setId(UUIDGenerator.getUUID());
        season.setComid(UUIDGenerator.getUUID());
        season.setName("采暖季2");
        season.setSdate("2017-01-01");
        season.setEdate("2017-01-11");
        seasonService.insert(season);
    }
    public void testDeleteDemo(){
        String id = "1";
        int o =  seasonService.delete(id);
        System.out.print(o);
    }

    @Test
    @Rollback
    public void testSelectPage(){
        PageResult<Season> seasons = seasonService.queryByPage("采暖",new Page());
        for (Season season : seasons.getList()){
            System.err.println(season.getName());
        }
    }
}
