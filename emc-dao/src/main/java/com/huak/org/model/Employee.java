package com.huak.org.model;

import java.io.Serializable;
import java.util.Date;

public class Employee  implements Serializable {
    private String id;

    private String empName;

    private String empCode;

    private String empTel;

    private String addr;

    private String orgId;

    private Integer empAge;

    private Integer empGender;

    private Date empBirthday;

    private String email;

    private String empMobilephone;

    private String userId;

    public Employee(String id, String empName, String empCode, String empTel, String addr, String orgId, Integer empAge, Integer empGender, Date empBirthday, String email, String empMobilephone, String userId) {
        this.id = id;
        this.empName = empName;
        this.empCode = empCode;
        this.empTel = empTel;
        this.addr = addr;
        this.orgId = orgId;
        this.empAge = empAge;
        this.empGender = empGender;
        this.empBirthday = empBirthday;
        this.email = email;
        this.empMobilephone = empMobilephone;
        this.userId = userId;
    }

    public Employee() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode == null ? null : empCode.trim();
    }

    public String getEmpTel() {
        return empTel;
    }

    public void setEmpTel(String empTel) {
        this.empTel = empTel == null ? null : empTel.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public Integer getEmpGender() {
        return empGender;
    }

    public void setEmpGender(Integer empGender) {
        this.empGender = empGender;
    }

    public Date getEmpBirthday() {
        return empBirthday;
    }

    public void setEmpBirthday(Date empBirthday) {
        this.empBirthday = empBirthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEmpMobilephone() {
        return empMobilephone;
    }

    public void setEmpMobilephone(String empMobilephone) {
        this.empMobilephone = empMobilephone == null ? null : empMobilephone.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}