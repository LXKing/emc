package com.huak.org;




import com.huak.org.dao.AdministrativeDao;
import com.huak.org.model.Administrative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/17.
 */
@Service
public class AdministrativeImpl implements AdministrativeService {

    @Autowired
    private AdministrativeDao administrativeDao;

    @Override
    public int deleteByPrimaryKey(String admCode) {
        return 0;
    }

    @Override
    public int insert(Administrative record) {
        return 0;
    }

    @Override
    public int insertSelective(Administrative record) {
        return 0;
    }

    @Override
    public Administrative selectByPrimaryKey(String admCode) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Administrative record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Administrative record) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> findAllByLevel(Map<String, String> paramsMap) {
        return administrativeDao.findAllByLevel(paramsMap);
    }

}
