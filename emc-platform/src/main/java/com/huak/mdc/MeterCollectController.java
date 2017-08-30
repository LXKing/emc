package com.huak.mdc;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.mdc.model.MeterCollect;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 计量仪器管理
 * Created by MR-BIN on 2017/8/28.
 */
@RequestMapping("/meterCollect")
@Controller
public class MeterCollectController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MeterCollectService meterCollectService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String listPage(Model model) {
        logger.info("转至系统计量器具列表页");
//        String code = "pipeType";
//        List<SysDic> dic = orgService.selectSysDicAll(code);
//        model.addAttribute("sysdic",dic);
        return "/sys/mdc/list";
    }

    /**
     * 跳转至计量器具导入界面
     * @param model
     * @return
     */
    @RequestMapping(value = "/uploadPage", method = RequestMethod.GET)
    public String toUpload(Model model) {
        logger.info("转至系统计量器具导入界面");
        return "/sys/mdc/upload";
    }

    /**
     * 后台-计量器具导入
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "upload", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String add(HttpServletRequest request,HttpServletResponse response) {
        logger.info("后台-计量器具导入开始");
        JSONObject jo = new JSONObject();
        meterCollectService.excelUpload(request);
        return jo.toJSONString();
    }
    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("计量器具列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, meterCollectService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("计量器具列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 计量仪器导出
     * @param paramsMap
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) throws IOException {
        logger.info("后台-导出计量采集表EXCEL");
        String workBookName = "计量采集表";//文件名
        HSSFWorkbook wb = null;
        OutputStream out = null;
        try {
            List<Map<String,Object>> data = meterCollectService.exportExcel(paramsMap);
            wb = CommonExcelExport.excelExport(Constants.CELL_NAME, data);
            //response输出流导出excel
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("后台-导出计量采集表EXCEL异常" + e.getMessage());
        }finally {
            if(null != out){
                out.close();
            }
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        logger.info("计量器具新增页面");
        return "/sys/mdc/add";
    }
    @RequestMapping(value = "/addvalue", method = RequestMethod.POST)
    @ResponseBody
    public String add(MeterCollect meterCollect, HttpServletRequest request) {
        logger.info("添加计量采集信息日志");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            if(meterCollect.getIsreal()==Byte.valueOf("1")){
                meterCollect.setIsprestore(Byte.valueOf("0"));
            }
            meterCollect.setId(UUIDGenerator.getUUID());
            meterCollectService.insert(meterCollect);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加计量采集成功");
        } catch (Exception e) {
            logger.error("添加计量采集异常" + e.getMessage());
            jo.put(Constants.MSG, "添加计量采集失败");
        }
        return jo.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/check/code", method = RequestMethod.POST)
    public String checkNodeCode(@RequestParam  String code,
                                @RequestParam  String comId ){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",code);
        map.put("comId",comId);
        JSONObject jo = new JSONObject();
        boolean  flag =   meterCollectService.checkCode(map);
        if(flag){
            jo.put(Constants.FLAG,false);
        }else{
            jo.put(Constants.FLAG, true);
        }
        return jo.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/check/name", method = RequestMethod.POST)
    public String checkName(@RequestParam  String name,
                                @RequestParam  String unitId ){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("unitId",unitId);
        JSONObject jo = new JSONObject();
        boolean  flag =   meterCollectService.checkName(map);
        if(flag){
            jo.put(Constants.FLAG,false);
        }else{
            jo.put(Constants.FLAG, true);
        }
        return jo.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/check/serialno", method = RequestMethod.POST)
    public String checkSerialSo(@RequestParam  String serialNo
    ){

        JSONObject jo = new JSONObject();
        boolean  flag =   meterCollectService.checkNo(serialNo);
        if(flag){
            jo.put(Constants.FLAG,false);
        }else{
            jo.put(Constants.FLAG, true);
        }
        return jo.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/unit", method = RequestMethod.POST)
    public String getUnitList(@RequestParam  String unitType
    ){

        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list =   meterCollectService.getUnitInfo(unitType);
        jo.put(Constants.LIST,list);
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改热源页");
        try {

            MeterCollect mec= meterCollectService.selectByPrimaryKey(id);
            List<Map<String,Object>> list=meterCollectService.getUnitInfo(mec.getUnitType().toString());
            model.addAttribute("mec", mec);
            model.addAttribute("uList",list);
        } catch (Exception e) {
            logger.error("跳转修改页异常" + e.getMessage());
        }
        return "/sys/mdc/edit";
    }
    @RequestMapping(value = "/editvalue", method = RequestMethod.POST)
    @ResponseBody
    public String editValue(MeterCollect meterCollect,HttpServletRequest request) {
        logger.info("修改计量器具");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int i = meterCollectService.updateByPrimaryKeySelective(meterCollect);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改计量器具成功");
        } catch (Exception e) {
            logger.error("修改计量器具异常" + e.getMessage());
            jo.put(Constants.MSG, "修改计量器具失败");
        }
        return jo.toJSONString();
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMeter(@PathVariable("id") String id) {
        logger.info("删除计量器具");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            meterCollectService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除成功");
        } catch (Exception e) {
            logger.error("删除异常" + e.getMessage());
            jo.put(Constants.MSG, "删除失败");
        }
        return jo.toJSONString();
    }
}
