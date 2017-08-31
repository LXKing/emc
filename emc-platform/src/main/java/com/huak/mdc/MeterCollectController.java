package com.huak.mdc;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.User;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.common.utils.MultipartFileParam;
import com.huak.common.utils.MultipartFileUploadUtil;
import com.huak.org.model.Company;
import org.apache.commons.io.FileUtils;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

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
    private static AtomicLong counter = new AtomicLong(0L);
    private static String UPLOAD_TEMP_DIR = "/usr/software/logs/sysmagent";
    private static String UPLOAD_TEMP_DIR1 = "D:\\workSp\\code\\upload";
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
        JSONObject jo = new JSONObject();
        String prefix = "req_count:" + counter.incrementAndGet() + ":";
        System.out.println(prefix + "start !!!");
        MultipartFileParam param = null;
        Map<String, Object> obj = null;
        RandomAccessFile accessTmpFile = null;
        RandomAccessFile accessConfFile = null;
        //使用 工具类解析相关参数，工具类代码见下面
        try {
            param = MultipartFileUploadUtil.parse(request);
            System.out.println(prefix + "chunks= " + param.getChunks());
            System.out.println(prefix + "chunk= " + param.getChunk());
            System.out.println(prefix + "chunkSize= " + param.getParam().get("chunkSize"));
            //这个必须与前端设定的值一致
            long chunkSize = 512 * 1024;
            if (param.isMultipart()) {
                String tempFileName = param.getFileName();
                File confFile = new File(UPLOAD_TEMP_DIR1, param.getFileName() + ".conf");
                File tmpDir = new File(UPLOAD_TEMP_DIR1);
                File tmpFile = new File(UPLOAD_TEMP_DIR1, tempFileName);
                if (!tmpDir.exists()) {
                    tmpDir.mkdirs();
                }
                accessTmpFile = new RandomAccessFile(tmpFile, "rw");
                accessConfFile = new RandomAccessFile(confFile, "rw");
                long offset = chunkSize * param.getChunk();
                //定位到该分片的偏移量
                accessTmpFile.seek(offset);
                //写入该分片数据
                accessTmpFile.write(param.getFileItem().get());

                //把该分段标记为 true 表示完成
                System.out.println(prefix + "set part " + param.getChunk() + " complete");
                accessConfFile.setLength(param.getChunks());
                accessConfFile.seek(param.getChunk());
                accessConfFile.write(Byte.MAX_VALUE);

                //completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
                byte[] completeList = FileUtils.readFileToByteArray(confFile);
                byte isComplete = Byte.MAX_VALUE;
                for (int i = 0; i < completeList.length && isComplete == Byte.MAX_VALUE; i++) {
                    //与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
                    isComplete = (byte) (isComplete & completeList[i]);
                    System.out.println(prefix + "check part " + i + " complete?:" + completeList[i]);
                }

                if (isComplete == Byte.MAX_VALUE) {
                    System.out.println(prefix + "upload complete !!");
                    obj = meterCollectService.excelUpload(UPLOAD_TEMP_DIR1 + "\\" + param.getFileName(), param.getFileName());
                }
                accessTmpFile.close();
                accessConfFile.close();
            }
        }catch(Exception e){
            logger.error("后台-计量器具导入异常:"+e);
        }finally {
                try {
                    if(null !=accessTmpFile){
                      accessTmpFile.close();
                    }
                    if(null !=accessConfFile){
                        accessConfFile.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File file = new File(UPLOAD_TEMP_DIR1);
                if(file.exists()){
                    System.out.println("删除excel");
                    File[] files = file.listFiles();
                    for (File files1 : files){
                        files1.delete();
                    }
                }
        }
        jo.put("message",obj);
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
            //虚表时 给预存字段设置  默认值
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
    public String getUnitList(@RequestParam  String unitType){

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
