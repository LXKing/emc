package com.huak.org;


import com.huak.org.model.Administrative;

import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/17.
 */
public interface AdministrativeService {
    int deleteByPrimaryKey(String admCode);

    int insert(Administrative record);

    int insertSelective(Administrative record);

    Administrative selectByPrimaryKey(String admCode);

    int updateByPrimaryKeySelective(Administrative record);

    int updateByPrimaryKey(Administrative record);

    List<Map<String,Object>> findAllByLevel(Map<String, String> paramsMap);

}
