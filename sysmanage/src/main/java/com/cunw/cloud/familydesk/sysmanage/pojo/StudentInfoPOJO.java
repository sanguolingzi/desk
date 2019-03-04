package com.cunw.cloud.familydesk.sysmanage.pojo;


import javax.validation.constraints.NotNull;

public class StudentInfoPOJO {
    private String id;

    /**
     * 学生姓名
     */
    @NotNull(message = "学生姓名不能为空")
    private String stuName;

    /**
     * 学生家长id
     */
    @NotNull(message = "家长id不能为空")
    private String parentId;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    private Integer age;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;

    /**
     * 学校名称
     */
    @NotNull(message = "学校名称不能为空")
    private String schoolName;

    /**
     * 年级
     */
    @NotNull(message = "年级不能为空")
    private String gradeCode;

    /**
     * 头像
     */
    private String headImg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
