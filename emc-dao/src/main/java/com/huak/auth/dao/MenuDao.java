package com.huak.auth.dao;


import com.huak.auth.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectPageByName();
}