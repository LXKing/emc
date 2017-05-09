package com.huak.org;

import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.season.model.Season;
import org.springframework.stereotype.Service;
import com.huak.org.model.Administrative;
/**
 * Created by Administrator on 2017/5/9.
 */


@Service
public interface OrgService {

    public int delete(String id);

    public int insert(Administrative season);

    public PageResult<Administrative> queryByPage(String name, Page page);

    public Administrative  selectAdministrator();
}
