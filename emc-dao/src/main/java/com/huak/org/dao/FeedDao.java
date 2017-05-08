package com.huak.org.dao;

import com.huak.org.model.Feed;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedDao {
    int deleteByPrimaryKey(String id);

    int insert(Feed record);

    int insertSelective(Feed record);

    Feed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Feed record);

    int updateByPrimaryKey(Feed record);
}