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
import com.huak.org.model.Room;

/**
 * 楼座controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/room")
public class RoomController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String listPage(Model model){
		logger.info("跳转到楼座列表页面");
		model.addAttribute("com", roomService.getCompanySelectHtmlStr(null));
		model.addAttribute("community", roomService.getCommunitySelectHtmlStr(new HashMap<String,String>(), null));
		model.addAttribute("ban", roomService.getBanSelectHtmlStr(new HashMap<String,String>(), null));
		model.addAttribute("cell", roomService.getCellSelectHtmlStr(new HashMap<String,String>(), null));
		return "/org/room/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.PATCH)
	@ResponseBody
	public String list(@RequestParam Map<String,String> params,Page page){
		logger.info("查询楼座信息");
		JSONObject result = new JSONObject();
		//查询楼座信息
		PageResult<Room> list = roomService.list(params,page);
		result.put(Constants.LIST, list);
		logger.info("查询楼座信息："+list);
		return result.toJSONString();
	}
	
	@RequestMapping(value="/add/{companyId}",method=RequestMethod.GET)
	public String addPage(Model model,@PathVariable String companyId){
		logger.info("跳转到添加楼座页面");
		model.addAttribute("com", roomService.getCompanySelectHtmlStr(companyId));
		return "/org/room/add";
	}
	
	@RequestMapping(value="/cellSelectHtmlStr",method=RequestMethod.GET)
	@ResponseBody
	public String getCellSelectHtmlStr(@RequestParam Map<String,String> param){
		logger.info("查询楼座下拉框html字符串");
		JSONObject result = new JSONObject();
		String htmlStr = roomService.getCellSelectHtmlStr(param,null);
		result.put("html", htmlStr);
		return result.toJSONString();
	}
	
	@RequestMapping(value="/lineSelectHtmlStr",method=RequestMethod.GET)
	@ResponseBody
	public String querLineSelectHtmlStr(@RequestParam Map<String,String> param){
		logger.info("查询管线下拉框html字符串");
		JSONObject result = new JSONObject();
		String htmlStr = roomService.getLineSelectHtmlStr(param,null);
		result.put("html", htmlStr);
		return result.toJSONString();
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String add(Room room){
		logger.info("添加楼座信息");
		JSONObject result = new JSONObject();
		try{
			result.put(Constants.FLAG, false);
			int save = roomService.save(room);
			if(save<=0){
				result.put(Constants.MSG, "添加楼座信息失败");
	        }else{
	        	result.put(Constants.FLAG, true);
	        	result.put(Constants.MSG, "添加楼座信息成功");
	        }
		}catch(Exception e){
			logger.error("添加楼座信息异常" + e.getMessage());
			result.put(Constants.MSG, "添加楼座信息异常");
		}
		return result.toJSONString();
	}
	
	@RequestMapping(value="/edit/{roomId}",method=RequestMethod.GET)
	public String editPage(Model model,@PathVariable String roomId){
		logger.info("跳转到修改楼座页面");
		Room room = roomService.get(roomId);
		if(room!=null){
			model.addAttribute("room", room);
			model.addAttribute("com", roomService.getCompanySelectHtmlStr(room.getComId()));
			Map<String,String> param = new HashMap<String,String>();
			param.put("comId", room.getComId());
			param.put("orgId", room.getOrgId().toString());
			param.put("communityId", room.getCommunityId());
			param.put("banId", room.getBanId());
			model.addAttribute("community", roomService.getCommunitySelectHtmlStr(param,room.getCommunityId()));
			model.addAttribute("ban", roomService.getBanSelectHtmlStr(param,room.getBanId()));
			model.addAttribute("cell", roomService.getCellSelectHtmlStr(param,room.getCellId()));
			model.addAttribute("line", roomService.getLineSelectHtmlStr(param,room.getLineId()));
		}
		return "/org/room/edit";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public String edit(Room room){
		logger.info("修改楼座信息，楼座id:"+room.getId());
		JSONObject result = new JSONObject();
		try{
			result.put(Constants.FLAG, false);
			int edit = roomService.edit(room);
			if(edit<=0){
				result.put(Constants.MSG, "修改楼座信息失败");
	        }else{
	        	result.put(Constants.FLAG, true);
	        	result.put(Constants.MSG, "修改楼座信息成功");
	        }
		}catch(Exception e){
			logger.error("修改楼座信息异常" + e.getMessage());
			result.put(Constants.MSG, "修改楼座信息异常");
		}
		return result.toJSONString();
	}
	
	@RequestMapping(value="/delete/{roomId}",method=RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable String roomId){
		logger.info("删除楼座信息，楼座id："+roomId);
		JSONObject result = new JSONObject();
		try{
			result.put(Constants.FLAG, false);
			int delete = roomService.delete(roomId);
			if(delete<=0){
				result.put(Constants.MSG, "删除楼座信息失败");
	        }else{
	        	result.put(Constants.FLAG, true);
	        	result.put(Constants.MSG, "删除楼座信息成功");
	        }
		}catch(Exception e){
			logger.error("删除楼座信息异常" + e.getMessage());
			result.put(Constants.MSG, "删除楼座信息异常");
		}
		return result.toJSONString();
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, String> param, HttpServletResponse response) {
        logger.info("导出户列表EXCEL");
        try {
        	JSONObject jo = new JSONObject();
	        jo.put(Constants.FLAG, false);
	        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
	        cellName.put("room_name", "户名称");
	        cellName.put("cell_name", "单元名称");
	        cellName.put("ban_name", "楼座名称");
	        cellName.put("community_name", "小区名称");
	        cellName.put("com_name", "所属公司");
	        cellName.put("org_name", "所属机构");
	        List<Map<String, Object>> cellValues = roomService.exportRoom(param);
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
                String fileName = "roomInfo.xls";
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                wb.write(out);
                out.flush();
            }
        } catch (Exception e) {
            logger.error("导出户列表EXCEL异常" + e.getMessage());
        }
	}
	
}
