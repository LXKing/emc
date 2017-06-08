package com.huak.auth;

import com.huak.auth.model.Menu;
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
 * File name:  java.com.huak.season<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/4/24<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public class TestMenuService extends BaseTest {
    @Resource
    private MenuService menuService;
//    @Test
//    @Rollback
//    public void testSelectDemo(){
//        String id = "1";
//        Season season = seasonService.selectByPrimaryKey(id);
//        System.out.println(season.getName());
//    }
//
//    @Test
//    @Rollback
//    public void testInsert(){
//        Season season = new Season();
//        season.setId(UUIDGenerator.getUUID());
//        season.setComid(UUIDGenerator.getUUID());
//        season.setName("采暖季2");
//        season.setSdate("2017-01-01");
//        season.setEdate("2017-01-11");
//        seasonService.insert(season);
//    }
//    public void testDeleteDemo(){
//        String id = "1";
//        int o =  seasonService.delete(id);
//        System.out.print(o);
//    }

    @Test
    @Rollback
    public void testQueryByPage(){
        HashMap<String,Object> params = new HashMap<>();
        params.put("menuName","前台");
        List<Map<String, Object>> seasons = menuService.selectTree(params);
        for (Map season : seasons){
            System.err.println(season.get("name"));
        }
    }


    @Test
    @Rollback
    public void testInsert(){

//        Menu menu = new Menu("1","测试","/org/list","","1","2017-05-16 08:49:00",(byte)1,(byte)1,1);
//            menuService.insert(menu);
        Menu menu1 = new Menu("ac7eb6410b1e4d0cbc2f46958758fe59","测试12","/org/list","","1","2017-05-16 08:49:00",(byte)1,(byte)1,1,"AD");
        menuService.updateByPrimaryKey(menu1);

    }



    @Test
    @Rollback
    public void delete(){
        menuService.delete("1");
    }

    @Test
    @Rollback
    public void selectById(){
      Menu menu =  menuService.selectByPrimaryKey("dc80259dd2aa405588cdad3719b703f5");
        System.out.println(menu.getMenuName());
    }
}
