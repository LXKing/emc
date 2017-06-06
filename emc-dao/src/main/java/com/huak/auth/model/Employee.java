package com.huak.auth.model;

public class Employee {
    private Long id;

    private Long orgId;

    private String empName;

    private Boolean sex;

    private Boolean age;

    private String birthday;

    private String jobNum;

    private String tel;

    private String phone;

    private String email;

    private String memo;

    private Long creator;

    private String createTime;

    private Boolean status;

    public Employee(Long id, Long orgId, String empName, Boolean sex, Boolean age, String birthday, String jobNum, String tel, String phone, String email, String memo, Long creator, String createTime,Boolean status) {
        this.id = id;
        this.orgId = orgId;
        this.empName = empName;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
        this.jobNum = jobNum;
        this.tel = tel;
        this.phone = phone;
        this.email = email;
        this.memo = memo;
        this.creator = creator;
        this.createTime = createTime;
        this.status = status;
    }

    public Employee() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Boolean getAge() {
        return age;
    }

    public void setAge(Boolean age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum == null ? null : jobNum.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}