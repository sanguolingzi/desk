package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

import java.beans.Transient;
import java.util.Date;

public class SysStudent implements IBasePO<String> {
    private String studentId;

    private String studentCode;

    private String studentName;

    private Integer age;

    /**
     * 0 女 1 男
     */
    private Integer sex;

    private String schoolName;

    private String gradeCode;

    private String studentNamePy;

    private String headImg;

    /**
     * 是否已绑定学校B端信息 0 未绑定 1已绑定
     */
    private Integer syncFlag;

    /**
     * 删除标识 1 未删除 0已删除
     */
    private Integer status;

    /**
     * 同步B端的学生code
     */
    private String syncStudentCode;

    /**
     * 同步B端的学校code
     */
    private String syncSchoolCode;

    /**
     * 同步B端的班级code
     */
    private String syncClassCode;

    /**
     * 同步B端的学生id
     */
    private String syncStudentId;

    private Date crtDate;

    private String stageId;

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.studentId;
    }

    @Override
    public void setId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
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
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode == null ? null : gradeCode.trim();
    }

    public String getStudentNamePy() {
        return studentNamePy;
    }

    public void setStudentNamePy(String studentNamePy) {
        this.studentNamePy = studentNamePy == null ? null : studentNamePy.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public Integer getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(Integer syncFlag) {
        this.syncFlag = syncFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSyncStudentCode() {
        return syncStudentCode;
    }

    public void setSyncStudentCode(String syncStudentCode) {
        this.syncStudentCode = syncStudentCode == null ? null : syncStudentCode.trim();
    }

    public String getSyncSchoolCode() {
        return syncSchoolCode;
    }

    public void setSyncSchoolCode(String syncSchoolCode) {
        this.syncSchoolCode = syncSchoolCode == null ? null : syncSchoolCode.trim();
    }

    public String getSyncClassCode() {
        return syncClassCode;
    }

    public void setSyncClassCode(String syncClassCode) {
        this.syncClassCode = syncClassCode == null ? null : syncClassCode.trim();
    }

    public String getSyncStudentId() {
        return syncStudentId;
    }

    public void setSyncStudentId(String syncStudentId) {
        this.syncStudentId = syncStudentId == null ? null : syncStudentId.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    @Override
    public String toString() {
        return "SysStudent{" +
                "studentId='" + studentId + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", studentName='" + studentName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", schoolName='" + schoolName + '\'' +
                ", gradeCode='" + gradeCode + '\'' +
                ", studentNamePy='" + studentNamePy + '\'' +
                ", headImg='" + headImg + '\'' +
                ", syncFlag=" + syncFlag +
                ", status=" + status +
                ", syncStudentCode='" + syncStudentCode + '\'' +
                ", syncSchoolCode='" + syncSchoolCode + '\'' +
                ", syncClassCode='" + syncClassCode + '\'' +
                ", syncStudentId='" + syncStudentId + '\'' +
                ", crtDate=" + crtDate +
                '}';
    }
}