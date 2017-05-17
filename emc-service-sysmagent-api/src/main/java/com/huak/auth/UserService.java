package com.huak.auth;

import java.util.Map;

import com.huak.auth.model.User;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;

public interface UserService {

	/**
	 * 分页查询用户信息
	 * @param paramsMap 查询条件
	 * @param page 分页信息
	 * @return
	 */
	PageResult<User> queryByPage(Map<String, String> paramsMap, Page page);

}
