package com.huak.log.dao;

import org.springframework.stereotype.Repository;

import com.huak.log.model.OperateLog;

@Repository
public interface OperateLogDao {

	void insertOperateLog(OperateLog log);

}
