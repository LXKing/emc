package com.huak.org.dao;

import com.huak.org.model.Room;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDao {
    int deleteByPrimaryKey(String id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);
}