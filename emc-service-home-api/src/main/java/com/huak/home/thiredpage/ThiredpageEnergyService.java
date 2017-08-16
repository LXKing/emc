package com.huak.home.thiredpage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/8/2.
 */
public interface ThiredpageEnergyService {

    /**
     *三级页面-集团能源类型的能耗趋势图
     * sunbinbin
     * @return map
     */
    Map<String, Object> getDatasAll(Map<String, Object> params) throws Exception;
    /**
     *三级页面-源、网、站、线、户的各个能源类型的能耗趋势图
     * sunbinbin
     * @return map
     */
    Map<String, Object> getDatas(Map<String, Object> params) throws Exception;

    /**
     *三级页面-换热站排名趋势图
     * sunbinbin
     * @return string
     */
    Map<String,Object> getassessment(Map<String, Object> paramsMap) throws Exception;

    /**
     *三级页面-表单
     * sunbinbin
     * @return map
     */
    Map<String, Object> getTable(Map<String, Object> params) throws Exception;
}
