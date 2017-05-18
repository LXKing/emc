package com.huak.auth;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huak.auth.model.User;
import com.huak.auth.model.vo.OrgEmpVo;
import com.huak.common.Constants;
import com.huak.common.page.Page;


/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/24<BR>
 * Description:   用户  <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(ModelMap modelMap) {
        logger.info("转至系统用户列表页");
        modelMap.put("demo", "demo");
        return "/auth/user/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, String> paramsMap, Page page) {
        logger.info("用户列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, userService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("用户列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
    
    @RequestMapping(value = "/org/emp", method = RequestMethod.POST)
    @ResponseBody
    public String orgEmp(String orgId) {
        logger.info("查询某组织节点下的所有员工");
        String result = "";
        ObjectMapper om = new ObjectMapper();
        try {
            List<OrgEmpVo> oeList = userService.queryOrgEmpByOrgId(orgId);
            result = om.writeValueAsString(oeList);
        } catch (Exception e) {
            logger.error("查询某组织节点下的所有员工" + e.getMessage());
        }
        return result;
    }
//
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public String editPage(Model model, @PathVariable("id") Long id) {
//        logger.info("跳转编辑用户页");
//        try {
//            model.addAttribute("obj", userService.getUser(id));
//        } catch (Exception e) {
//            logger.error("跳转编辑页异常" + e.getMessage());
//        }
//        return "/auth/user/user_edit";
//    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
//    @ResponseBody
//    public String edit(@RequestParam Map<String, String> paramsMap) {
//        logger.info("编辑用户");
//
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG, false);
//        try {
//            userService.editUser(paramsMap);
//            jo.put(Constants.FLAG, true);
//            jo.put(Constants.MSG, "编辑用户成功");
//        } catch (Exception e) {
//            logger.error("编辑用户异常" + e.getMessage());
//            jo.put(Constants.MSG, "编辑用户失败");
//        }
//        return jo.toJSONString();
//    }
//
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage() {
        return "/auth/user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(User user) {
        logger.info("添加用户");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
//            paramsMap.put("password", reversibleEncryption.encode(paramsMap.get("password")));
//            userService.addUser(paramsMap);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加用户成功");
        } catch (Exception e) {
            logger.error("添加用户异常" + e.getMessage());
            jo.put(Constants.MSG, "添加用户失败");
        }
        return jo.toJSONString();
    }
//
//    @RequestMapping(value = "/check/phone", method = RequestMethod.POST)
//    @ResponseBody
//    public String checkPhone(@RequestParam Map<String, String> paramsMap) {
//        logger.info("手机号唯一性校验");
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG, false);
//        try {
//            Long num = userService.checkPhone(paramsMap);
//            if (num == 0) {
//                jo.put(Constants.FLAG, true);
//            }
//        } catch (Exception e) {
//            logger.error("手机号唯一性校验异常" + e.getMessage());
//        }
//        return jo.toJSONString();
//    }
//
//    @RequestMapping(value = "/check/login", method = RequestMethod.POST)
//    @ResponseBody
//    public String checkLogin(@RequestParam Map<String, String> paramsMap) {
//        logger.info("登录账号唯一性校验");
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG, false);
//        try {
//            Long num = userService.checkLogin(paramsMap);
//            if (num == 0) {
//                jo.put(Constants.FLAG, true);
//            }
//        } catch (Exception e) {
//            logger.error("登录账号唯一性校验异常" + e.getMessage());
//        }
//        return jo.toJSONString();
//    }
//
//    @RequestMapping(value = "/check/jobNum", method = RequestMethod.POST)
//    @ResponseBody
//    public String checkJobNum(@RequestParam Map<String, String> paramsMap) {
//        logger.info("工号唯一性校验");
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG, false);
//        try {
//            Long num = userService.checkJobNum(paramsMap);
//            if (num == 0) {
//                jo.put(Constants.FLAG, true);
//            }
//        } catch (Exception e) {
//            logger.error("工号唯一性校验异常" + e.getMessage());
//        }
//        return jo.toJSONString();
//    }
//
//
//    @RequestMapping(value = "/reset/pwd", method = RequestMethod.POST)
//    @ResponseBody
//    public String resetPwd(@RequestParam Map<String, String> paramsMap) {
//        logger.info("重置密码");
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG, false);
//        try {
//            String pwd = userService.resetPwd(paramsMap);
//            jo.put(Constants.FLAG, true);
//            jo.put(Constants.MSG, "重置密码成功,密码为【" + pwd + "】");
//        } catch (Exception e) {
//            jo.put(Constants.MSG, "重置密码失败");
//            logger.error("重置密码异常" + e.getMessage());
//        }
//        return jo.toJSONString();
//    }
//
//    /**
//     * 为什么用POST请求
//     * 因为DELETE请求接收不到数组 fuck
//     * @param ids
//     * @return
//     */
//
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    @ResponseBody
//    public String deleteUsers(String ids) {
//        logger.info("批量删除用户");
//
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG, false);
//        try {
//            userService.deleteUsers(ids);
//            jo.put(Constants.FLAG, true);
//            jo.put(Constants.MSG, "删除用户成功");
//        } catch (Exception e) {
//            logger.error("删除用户异常" + e.getMessage());
//            jo.put(Constants.MSG, "删除用户失败");
//        }
//        return jo.toJSONString();
//    }
//
//    /**
//     * @param id
//     * @return
//     */
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public String delete(@PathVariable("id") Long id) {
//        logger.info("删除用户");
//
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG, false);
//        try {
//            userService.deleteUsers(id.toString());
//            jo.put(Constants.FLAG, true);
//            jo.put(Constants.MSG, "删除用户成功");
//        } catch (Exception e) {
//            logger.error("删除用户异常" + e.getMessage());
//            jo.put(Constants.MSG, "删除用户失败");
//        }
//        return jo.toJSONString();
//    }
//
//    @RequestMapping(value = "/status", method = RequestMethod.POST)
//    @ResponseBody
//    public String status(@RequestParam Map<String, String> paramsMap) {
//        logger.info("修改用户可用状态");
//
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG, false);
//        try {
//            userService.updStatus(paramsMap);
//            jo.put(Constants.FLAG, true);
//            jo.put(Constants.MSG, "修改成功");
//        } catch (Exception e) {
//            logger.error("修改用户可用状态异常" + e.getMessage());
//            jo.put(Constants.MSG, "修改失败");
//        }
//        return jo.toJSONString();
//    }
//
//    @RequestMapping(value = "/role", method = RequestMethod.GET)
//    public String rolePage(Model model, Long id) {
//        logger.info("跳转分配角色页");
//        try {
//            model.addAttribute("obj", userService.getUser(id));
//            model.addAttribute("roles", userService.getRoles(id));
//        } catch (Exception e) {
//            logger.error("跳转分配角色页异常" + e.getMessage());
//        }
//        return "/auth/user/user_role";
//    }
//
//    @RequestMapping(value = "/role", method = RequestMethod.POST)
//    @ResponseBody
//    public String role(Long userId, Long[] roleIds) {
//        logger.info("用户分配角色");
//
//        JSONObject jo = new JSONObject();
//        jo.put(Constants.FLAG, false);
//        try {
//            userService.setRoles(userId, roleIds);
//            jo.put(Constants.FLAG, true);
//            jo.put(Constants.MSG, "分配角色成功");
//        } catch (Exception e) {
//            logger.error("用户分配角色异常" + e.getMessage());
//            jo.put(Constants.MSG, "分配角色失败");
//        }
//        return jo.toJSONString();
//    }
//
//    @RequestMapping(value = "/update/pwd", method = RequestMethod.GET)
//    public String updatePwdPage() {
//        logger.info("跳转修改密码页");
//        return "/auth/user/user_pwd";
//    }
//
//    @RequestMapping(value = "/update/pwd", method = RequestMethod.POST)
//    @ResponseBody
//    public String updatePwd(@RequestParam Map<String, String> paramsMap, HttpServletRequest request) {
//        logger.info("修改密码");
//
//        JSONObject jo = new JSONObject();
//
//        HttpSession session = request.getSession(false);
//        Map<String, Object> user = (Map<String, Object>) session.getAttribute(Constants.SESSION_KEY);
//        paramsMap.put("id", user.get("user_id").toString());
//        jo.put(Constants.FLAG, false);
//        try {
//            userService.updPwd(paramsMap);
//            jo.put(Constants.FLAG, true);
//            jo.put(Constants.MSG, "修改密码成功");
//        } catch (Exception e) {
//            logger.error("修改密码异常" + e.getMessage());
//            jo.put(Constants.MSG, "修改密码失败");
//        }
//        return jo.toJSONString();
//    }
//
//    @RequestMapping(value = "/check/pwd", method = RequestMethod.POST)
//    @ResponseBody
//    public String checkPwd(@RequestParam Map<String, String> paramsMap, HttpServletRequest request) {
//        logger.info("原始密码校验");
//        JSONObject jo = new JSONObject();
//
//        HttpSession session = request.getSession(false);
//        Map<String, Object> user = (Map<String, Object>) session.getAttribute(Constants.SESSION_KEY);
//        jo.put(Constants.FLAG, false);
//
//        try {
//            String password = reversibleEncryption.decode(user.get("password").toString());
//            String originalPwd = paramsMap.get("originalPwd");
//            if (password.equals(originalPwd)) {
//                jo.put(Constants.FLAG, true);
//            }
//        } catch (Exception e) {
//            logger.error("原始密码校验异常" + e.getMessage());
//        }
//        return jo.toJSONString();
//    }
//
//    @RequestMapping(value = "/export", method = RequestMethod.GET)
//    public void export(@RequestParam Map<String, String> paramsMap, HttpServletResponse response) {
//        logger.info("导出用户列表EXCEL");
//        String workBookName = "用户列表";//文件名
//        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
//        cellName.put("user_id", "用户主键");
//        cellName.put("room_id", "房间主键");
//        cellName.put("part_id", "部门主键");
//        cellName.put("login", "登录账号");
//        cellName.put("password", "密码");
//        cellName.put("login_time", "登录时间");
//        cellName.put("last_login_time", "上次登录时间");
//        cellName.put("login_num", "登录次数");
//        cellName.put("use_status", "使用状态");
//        List<Map<String, Object>> cellValues = null;//列值
//        try {
//            cellValues = userService.exportUser(paramsMap);
//            commonExcelExport.excelExport(response, cellName, cellValues, workBookName);
//        } catch (Exception e) {
//            logger.error("导出用户列表EXCEL异常" + e.getMessage());
//        }
//    }

}

