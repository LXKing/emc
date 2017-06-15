package com.huak.org;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.org.model.Cell;

/**
 * 单元controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/cell")
public class CellController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CellService cellService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String listPage(Model model){
		logger.info("跳转到单元列表页面");
		model.addAttribute("com", cellService.getCompanySelectHtmlStr(null));
		model.addAttribute("community", cellService.getCommunitySelectHtmlStr(new HashMap<String,String>(), null));
		model.addAttribute("ban", cellService.getBanSelectHtmlStr(new HashMap<String,String>(), null));
		return "/org/cell/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.PATCH)
	@ResponseBody
	public String list(@RequestParam Map<String,String> params,Page page){
		logger.info("查询单元信息");
		JSONObject result = new JSONObject();
		//查询单元信息
		PageResult<Cell> list = cellService.list(params,page);
		result.put(Constants.LIST, list);
		logger.info("查询单元信息："+list);
		return result.toJSONString();
	}
	
	@RequestMapping(value="/add/{companyId}",method=RequestMethod.GET)
	public String addPage(Model model,@PathVariable String companyId){
		logger.info("跳转到添加单元页面");
		model.addAttribute("com", cellService.getCompanySelectHtmlStr(companyId));
		return "/org/cell/add";
	}
	
	@RequestMapping(value="/banSelectHtmlStr",method=RequestMethod.GET)
	@ResponseBody
	public String getBanSelectHtmlStr(@RequestParam Map<String,String> param){
		logger.info("查询楼座下拉框html字符串");
		JSONObject result = new JSONObject();
		String htmlStr = cellService.getBanSelectHtmlStr(param,null);
		result.put("html", htmlStr);
		return result.toJSONString();
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String add(Cell cell){
		logger.info("添加单元信息");
		JSONObject result = new JSONObject();
		try{
			result.put(Constants.FLAG, false);
			int save = cellService.save(cell);
			if(save<=0){
				result.put(Constants.MSG, "添加单元信息失败");
	        }else{
	        	result.put(Constants.FLAG, true);
	        	result.put(Constants.MSG, "添加单元信息成功");
	        }
		}catch(Exception e){
			logger.error("添加单元信息异常" + e.getMessage());
			result.put(Constants.MSG, "添加单元信息异常");
		}
		return result.toJSONString();
	}
	
	@RequestMapping(value="/edit/{cellId}",method=RequestMethod.GET)
	public String editPage(Model model,@PathVariable String cellId){
		logger.info("跳转到修改单元页面");
		Cell cell = cellService.get(cellId);
		if(cell!=null){
			model.addAttribute("cell", cell);
			model.addAttribute("com", cellService.getCompanySelectHtmlStr(cell.getComId()));
			Map<String,String> param = new HashMap<String,String>();
			param.put("comId", cell.getComId());
			param.put("orgId", cell.getOrgId().toString());
			param.put("communityId", cell.getCommunityId());
			model.addAttribute("community", cellService.getCommunitySelectHtmlStr(param,cell.getCommunityId()));
			model.addAttribute("ban", cellService.getBanSelectHtmlStr(param,cell.getBanId()));
		}
		return "/org/cell/edit";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public String edit(Cell cell){
		logger.info("修改单元信息，单元id:"+cell.getId());
		JSONObject result = new JSONObject();
		try{
			result.put(Constants.FLAG, false);
			int edit = cellService.edit(cell);
			if(edit<=0){
				result.put(Constants.MSG, "修改单元信息失败");
	        }else{
	        	result.put(Constants.FLAG, true);
	        	result.put(Constants.MSG, "修改单元信息成功");
	        }
		}catch(Exception e){
			logger.error("修改单元信息异常" + e.getMessage());
			result.put(Constants.MSG, "修改单元信息异常");
		}
		return result.toJSONString();
	}
	
	@RequestMapping(value="/delete/{cellId}",method=RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable String cellId){
		logger.info("删除单元信息，单元id："+cellId);
		JSONObject result = new JSONObject();
		try{
			result.put(Constants.FLAG, false);
			int delete = cellService.delete(cellId);
			if(delete<=0){
				result.put(Constants.MSG, "删除单元信息失败");
	        }else{
	        	result.put(Constants.FLAG, true);
	        	result.put(Constants.MSG, "删除单元信息成功");
	        }
		}catch(Exception e){
			logger.error("删除单元信息异常" + e.getMessage());
			result.put(Constants.MSG, "删除单元信息异常");
		}
		return result.toJSONString();
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, String> param, HttpServletResponse response) {
        logger.info("导出单元列表EXCEL");
        try {
        	JSONObject jo = new JSONObject();
	        jo.put(Constants.FLAG, false);
	        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
	        cellName.put("cell_name", "单元名称");
	        cellName.put("ban_name", "楼座名称");
	        cellName.put("com_name", "所属公司");
	        cellName.put("org_name", "所属机构");
	        List<Map<String, Object>> cellValues = cellService.exportCell(param);
            if(cellValues==null){
            	jo.put(Constants.MSG, "导出失败");
            }else if(cellValues.size()==0){
            	jo.put(Constants.MSG, "没有数据要导出");
            }else{
            	jo.put(Constants.MSG, "导出失败");
            	HSSFWorkbook wb = CommonExcelExport.excelExport(cellName, cellValues);
            	OutputStream out = response.getOutputStream();
            	//输出Excel文件  
            	String mimetype = "application/vnd.ms-excel";
                response.setContentType(mimetype);
                response.setCharacterEncoding("UTF-8");
                String fileName = "cellInfo.xls";
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                wb.write(out);
                out.flush();
            }
        } catch (Exception e) {
            logger.error("导出单元列表EXCEL异常" + e.getMessage());
        }
	}
}
