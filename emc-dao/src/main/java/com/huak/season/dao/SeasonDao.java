package com.huak.season.dao;


import com.huak.season.model.Season;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonDao {
    int deleteByPrimaryKey(String id);

    int insert(Season record);

    int insertSelective(Season record);

    Season selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Season record);

    int updateByPrimaryKey(Season record);

    List<Season> selectPageByName(@Param("name")String name);
}