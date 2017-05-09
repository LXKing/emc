package com.huak.org;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Administrative;
import org.springframework.stereotype.Service;
import com.huak.org.dao.AdministrativeDao;
import com.huak.org.OrgService;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/5/9.
 */

@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    private AdministrativeDao administrativeDao;

    @Override
    public int insert(Administrative season) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public PageResult<Administrative> queryByPage(String name, Page page) {
        return null;
    }

    @Override
    public Administrative selectAdministrator() {
        System.out.print("----------------------service-------------------------");
        Administrative ad = administrativeDao.selectByPrimaryKey(null);

        return null;
    }
}
