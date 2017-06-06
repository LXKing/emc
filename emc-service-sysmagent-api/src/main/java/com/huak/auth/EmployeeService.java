package com.huak.auth;


import com.huak.auth.model.Employee;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import java.util.Map;

public interface EmployeeService {
	/**
	 * 分页查询员工信息
	 * @param paramsMap 查询条件
	 * @param page 分页信息
	 * @return
	 */
	PageResult<Employee> queryByPage(Map<String, Object> paramsMap, Page page);



	/**
	 * 添加员工信息
	 * @param employee
	 * @return
	 * @throws RuntimeException
	 */
	int addEmployee(Employee employee) throws RuntimeException;

	/**
	 * 获取员工信息，根据主键
	 * @param id
	 * @return
	 */
    Employee getEmployeeById(Long id) throws Exception;

	/**
	 * 编辑员工信息 
	 * @param employee
	 * @return
	 * @throws Exception
	 */
	int editEmployee(Employee employee) throws Exception;

	/**
	 * 删除员工信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int removeEmployee(String[] ids) throws Exception;


}
