package com.huak.org.dao;

import com.huak.org.model.Org;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrgDao {
    int deleteByPrimaryKey(String id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);

    List<Org> selectOrgAll();

    List<Org> CheckOrgName(String orgName);

    List<Map<String,Object>> selectOrgByMap(Map<String,Object> params);
}