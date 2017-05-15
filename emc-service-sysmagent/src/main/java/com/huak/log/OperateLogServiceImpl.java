package com.huak.log;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huak.log.dao.OperateLogDao;
import com.huak.log.model.OperateLog;

@Service
public class OperateLogServiceImpl implements OperateLogService {

	@Resource
	private OperateLogDao logDao;

	@Override
	public void saveOperateLog(OperateLog log) {
		logDao.insertOperateLog(log);
	}
}
