package com.huak.org.dao;

import com.huak.auth.model.Role;
import com.huak.org.model.Feed;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FeedDao {
    int deleteByPrimaryKey(String id);

    int insert(Feed record);

    int insertSelective(Feed record);

    Feed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Feed record);

    int updateByPrimaryKey(Feed record);

    List<Feed> selectPageByMap(Map<String,Object> paramsMap);
}