package com.huak.home.component;

import java.util.Map;

/**
 * Created by MR-BIN on 2017/6/8.
 */
public interface ComponentService {

    /**
     * 能耗明细查询
     */
    Map<String,Object> energyDetail(Map<String,Object> params);
}
