package com.huak.home.workorder;

import com.huak.base.BaseTest;
import com.huak.workorder.model.WorkOrderInfo;
import org.junit.Test;

import javax.annotation.Resource;

public class WorkOrderInfoServiceImplTest extends BaseTest {

    @Resource
    private  WorkOrderInfoService workOrderInfoService;


    private Byte type = 1;
    private String name = "测试工单";
    private String content = "测试工单内容测试工单内容测试工单内容测试工单内容测试工单内容";
    private String startTime = "2017-10-26 12:00:11";
    private String finishTime = "2017-10-28 12:32:11";
    private String createTime = "2017-10-25 12:00:11";
    private String creator = "8c3c0d599726477ca34192d605d994d4";
    private String finish = "";
    private String actualFinishTime = "2017-10-25 12:00:11";
    private String comid = "a3e5e868e7844c349e5cf51c5e6bc37d";
    private String reason = "测试退单原因测试退单原因测试退单原因测试退单原因测试退单原因";
    private String monitor = "85f552f226d843fab1b7e0aa9bb3869b";
    private String takor = "7da37be265bc4ad8b81ab6067b84067f";

    @Test
    public void testSaveA() throws Exception {
        WorkOrderInfo orderInfo = new WorkOrderInfo();
        orderInfo.setComid(comid);
        orderInfo.setName(name);
        orderInfo.setContent(content);
        orderInfo.setCreator(creator);
        orderInfo.setStartTime(startTime);
        orderInfo.setFinishTime(finishTime);
        orderInfo.setCreateTime(createTime);
        orderInfo.setType(type);

        workOrderInfoService.saveA(orderInfo);
    }
}