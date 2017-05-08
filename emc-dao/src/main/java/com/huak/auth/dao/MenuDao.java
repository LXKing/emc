package com.huak.auth.dao;


import com.huak.auth.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectPageByName(@Param("name")String name);
}