package com.huak.org;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.org.model.Node;
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
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/5/16.
 */
@Controller
@RequestMapping("/station")
public class NodeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private NodeService nodeService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统热力站列表页");
        return "/org/node/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("热力站列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, nodeService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("热力站列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage() {
        return "/org/node/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Node node, HttpServletRequest request) {
        logger.info("添加热力站");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();



            node.setId(UUIDGenerator.getUUID());
            nodeService.insertSelective(node);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加热力站成功");
        } catch (Exception e) {
            logger.error("添加热力站异常" + e.getMessage());
            jo.put(Constants.MSG, "添加热力站失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改热力站页");
        try {
            model.addAttribute("role", nodeService.selectById(id));
        } catch (Exception e) {
            logger.error("跳转修改热力站页异常" + e.getMessage());
        }
        return "/org/node/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(Node node) {
        logger.info("修改热力站");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            nodeService.updateByPrimaryKey(node);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改热力站成功");
        } catch (Exception e) {
            logger.error("修改热力站异常" + e.getMessage());
            jo.put(Constants.MSG, "修改热力站失败");
        }
        return jo.toJSONString();
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteRole(@PathVariable("id") String id) {
        logger.info("删除热力站");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            nodeService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除热力站成功");
        } catch (Exception e) {
            logger.error("删除热力站异常" + e.getMessage());
            jo.put(Constants.MSG, "删除热力站失败");
        }
        return jo.toJSONString();
    }


    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出热力站列表EXCEL");
        String workBookName = "热力站列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("ID", "主键");
        cellName.put("ROLE_NAME", "热力站名称");
        cellName.put("ROLE_DES", "热力站说明");
        cellName.put("memo", "备注");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = nodeService.exportExcel(paramsMap);
            HSSFWorkbook wb = CommonExcelExport.excelExport(cellName, cellValues);
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
            logger.error("导出热力站列表EXCEL异常" + e.getMessage());
        }
    }
}
