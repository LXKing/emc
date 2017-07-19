package com.huak.api;

import com.huak.org.model.Feed;
import com.huak.org.model.Node;
import com.huak.org.model.Org;
import com.huak.task.model.EmcOrgInter;
import com.huak.task.model.Temperature;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.task<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/13<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

public interface RoomTempService {

    /**
     * 确认室温内是否存在将要导入的数据
     */
    public List<Temperature>  isExsistTemp(Map<String,Object> map);

    /**
     * 确认后导入室温数据
     */
    public Map<String, Object>  insertTemp(Temperature temp);

    /**
     * 确认组织机构是否存在将要导入的数据
     */
    public List<EmcOrgInter>  isExsistInter(Map<String,Object> map);

    /**
     * 确认后导入组织机构
     */
    public Map<String, Object>  insertOrg(Org org);

    /**
     * 保存组织机构关系表数据
     */
    void  insertEmcOrgInter(EmcOrgInter inter);

    /**
     *确认后导入热源信息数据
     */
    public Map<String, Object>  insertFeed(Feed feed);

    /**
     * 确认后导入热力站信息数据
     */
    public Map<String, Object>  inserStation(Node node);
}
