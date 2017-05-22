package com.huak.org;

import com.huak.base.dao.DateDao;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Administrative;
import com.huak.org.model.Org;
import org.springframework.stereotype.Service;
import com.huak.org.dao.AdministrativeDao;
import com.huak.org.dao.OrgDao;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2017/5/9.
 */

@Service
public class OrgServiceImpl implements OrgService {


    @Resource
    private AdministrativeDao administrativeDao;
    @Resource
    private OrgDao orgDao;
    @Resource
    private DateDao dateDao;
    @Override
    public int insert(Administrative season) {
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean delete(String id) {
        boolean flag=false;
        String[] ids = id.split(",");
        try {
            if(ids.length>1){
                for (int i = 0; i <ids.length ; i++) {
                    orgDao.deleteByPrimaryKey(ids[i]);
                }
                flag=true;
            }else{
                orgDao.deleteByPrimaryKey(ids[0]);
                flag=true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            flag=false;
        }
        return flag;
    }

    @Override
    public PageResult<Administrative> queryByPage(String name, Page page) {
        return null;
    }


    @Override
    public Administrative selectAdministrator() {
        return null;
    }


    @Override
    public List<Org> selectOrgAll() {
        System.out.print("----------------------service-------------------------");
        List<Org> lad = orgDao.selectOrgAll();
        return lad;
    }

    @Override
    public boolean insertOrg(Org org) {
        boolean flag=false;
        org.setCreateTime(dateDao.getTime());
        int i =orgDao.insert(org);
        if(i>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean checkOrgName(String orgName) {
        boolean flag=false;
        List<Org>  list = orgDao.CheckOrgName(orgName);
            if(list.size()>0){
                flag=true;
            }
        return flag;
    }

    @Override
    public Org selectByPrimaryKey(String id) {
        Org org = orgDao.selectByPrimaryKey(id);
        return org;
    }

    @Override
    public boolean updateOrg(Org org) {
          boolean flag=false;
          int i =  orgDao.updateByPrimaryKeySelective(org);
          if(i>0){
              flag=true;
          }
        return flag;
    }

    @Override
    public List<Administrative> selectAll() {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectOrgByMap(Map<String, Object> params) {
        return orgDao.selectOrgByMap(params);
    }
}
