package com.huak.org;

import org.springframework.stereotype.Service;
import com.huak.org.model.Org;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9.
 */


@Service
public interface OrgService {

    public boolean delete(String id);

    List<Org> selectOrgAll();

    public boolean insertOrg(Org org);

    boolean checkOrgName(String orgName);

    public Org selectByPrimaryKey(String id);

    public boolean updateOrg(Org org);

    public List<Map<String,Object>> selectOrgByMap(Map<String,Object> params);
}
