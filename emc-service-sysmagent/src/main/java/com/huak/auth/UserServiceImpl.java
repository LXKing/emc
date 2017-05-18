package com.huak.auth;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.huak.auth.dao.UserDao;
import com.huak.auth.model.User;
import com.huak.auth.model.vo.OrgEmpVo;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	/**
	 * 分页查询用户信息
	 */
	@Override
	public PageResult<User> queryByPage(Map<String, String> paramsMap, Page page) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		return Convert.convert(userDao.selectPageByMap(paramsMap));
	}

	@Override
	public List<OrgEmpVo> queryOrgEmpByOrgId(String orgId) {
		return userDao.selectOrgEmpById(orgId);
	}
}
