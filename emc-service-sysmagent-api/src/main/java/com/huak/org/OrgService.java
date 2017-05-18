package com.huak.org;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.season.model.Season;
import org.springframework.stereotype.Service;
import com.huak.org.model.Administrative;
import com.huak.org.model.Org;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */


@Service
public interface OrgService {

    public boolean delete(String id);

    public int insert(Administrative season);

    public PageResult<Administrative> queryByPage(String name, Page page);

    public Administrative  selectAdministrator();

    List<Administrative> selectAll();

    List<Org> selectOrgAll();

    public boolean insertOrg(Org org);

    boolean checkOrgName(String orgName);

    public Org selectByPrimaryKey(String id);

    public boolean updateOrg(Org org);

    public List<Map<String,Object>> selectOrgByMap(Map<String,Object> params);
}
