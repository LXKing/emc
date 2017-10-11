package com.huak.web.home.health;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huak.health.model.PollingMessage;
import com.huak.health.type.PollingType;
import com.huak.tools.HealthItem;
import com.huak.web.home.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.home.health<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/22<BR>
 * Description:   健康指数  <BR>
 * Function List:  <BR>
 */
@Controller
@RequestMapping("/health")
public class HealthController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Queue<PollingMessage> CONNECTIONS = new ConcurrentLinkedQueue<>();//消息队列

    private static boolean OVER = false;//标识

    private final static long TIMEOUT = 20000l;//超时时间

    @RequestMapping(method = RequestMethod.GET)
    public String page(Model model, HttpServletRequest request) {
        logger.info("跳转健康指数页面");
        model.addAttribute("healthItem", JSONArray.toJSONString(HealthItem.HEALTH_ITEM));
        return "health/inspect";
    }

    @RequestMapping(value = "/testing",method = RequestMethod.POST)
    @ResponseBody
    public void testing( HttpServletRequest request,HttpServletResponse response) {
        synchronized(this){
            CONNECTIONS.clear();
            OVER = false;
            //todo 业务数据入队列
            for(int i=0;i<20;i++){
                CONNECTIONS.offer(new PollingMessage(PollingType.MSG.getKey(),i));
            }

            OVER = true;
        }

    }

    /**
     *
     *  开启长连接
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/polling",method = RequestMethod.GET)
    public @ResponseBody  DeferredResult<JSONObject> polling( HttpServletRequest request,HttpServletResponse response) {
        synchronized(this){
            DeferredResult<JSONObject> result = new DeferredResult<>(TIMEOUT,null);  //设置超时,超时返回null
            final JSONObject jo = new JSONObject();
            if(OVER && CONNECTIONS.isEmpty()){
                //消息接收完毕，返回结束标识
                jo.put(PollingType.END.getKey(),PollingType.END.getDes());
                result.setResult(jo);
            }else{
                //队列取值
                final PollingMessage msg = CONNECTIONS.poll();
                jo.put(PollingType.MSG.getKey(),msg);
                result.setResult(jo);
                //结束后操作
                result.onCompletion(new Runnable() {
                    @Override
                    public void run() {
                        //System.out.println(jo.toJSONString());
                    }
                });
            }
            return result;
        }

    }




}
