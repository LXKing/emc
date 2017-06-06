package com.huak.auth;

import com.github.pagehelper.PageHelper;
import com.huak.auth.dao.EmployeeDao;
import com.huak.auth.model.Employee;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public PageResult<Employee> queryByPage(Map<String, Object> paramsMap, Page page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(employeeDao.selectPageByMap(paramsMap));
    }

    @Override
    public int addEmployee(Employee employee) throws RuntimeException {
        return employeeDao.insert(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) throws Exception {
        return employeeDao.selectByPrimaryKey(id);
    }

    @Override
    public int editEmployee(Employee employee) throws Exception {
        return employeeDao.updateByPrimaryKey(employee);
    }

    @Override
    public int removeEmployee(String[] ids) throws Exception {
        return employeeDao.delete(ids);
    }
}
