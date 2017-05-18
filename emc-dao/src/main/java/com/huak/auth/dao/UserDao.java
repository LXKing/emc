package com.huak.auth.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huak.auth.model.User;

@Repository
public interface UserDao {

	List<User> selectPageByMap(Map<String, String> paramsMap);

}
