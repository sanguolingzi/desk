package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import com.cunw.cloud.framework.model.ICreatePO;
import com.cunw.cloud.framework.model.IUpdatePO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SysStudentBind implements IBasePO<String>, ICreatePO, IUpdatePO {

    /**
     * 注册id
     */
    private String bindId;

    /**
     * 机构代码
     */
    private String schoolCode;

    /**
     * 机构名称
     */
    private String schoolName;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 学生编号
     */
    private String studentCode;

    /**
     * 学生id
     */
    private String parentId;

    /**
     * 学生编号
     */
    private String parentCode;

    /**
     * 状态：1-已提交注册 2-审核通过 3-审核未通过 4-注销
     */
    private String status;

    /**
     * 审核时间
     */
    private Date auditDate;

    /**
     * 审核人
     */
    private String auditorCode;

    /**
     * 创建人
     */
    private String crtUserCode;

    /**
     * 创建机构
     */
    private String crtOrgCode;

    /**
     * 创建时间
     */
    private Date crtDate;

    /**
     * 修改人
     */
    private String updUserCode;

    /**
     * 修改机构
     */
    private String updOrgCode;

    /**
     * 修改日期
     */
    private Date updDate;

    private static final long serialVersionUID = 1L;

    private LinkedHashMap student;

    private String schoolUrl;

    private String parentMobile;

    private String deviceNo;

    private String imei;

    public SysStudentBind() {

    }

    public SysStudentBind(String parentId, String parentCode, SysSchool school, String studentId, String studentCode, Date date, String imei) {
        this.parentId = parentId;
        this.parentCode = parentCode;
        this.schoolCode = school.getSchoolCode();
        this.schoolUrl = school.getUrl();
        this.schoolName = school.getSchoolName();
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.status = "2";
        this.auditDate = date;
        this.auditorCode = "auto";
        this.imei = imei;
    }

    @Override
    public String getId() {
        return this.bindId;
    }

    @Override
    public void setId(String bindId) {
        this.bindId = bindId == null ? null : bindId.trim();
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId == null ? null : bindId.trim();
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode == null ? null : schoolCode.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditorCode() {
        return auditorCode;
    }

    public void setAuditorCode(String auditorCode) {
        this.auditorCode = auditorCode == null ? null : auditorCode.trim();
    }

    @JsonIgnore
    public String getCrtUserCode() {
        return crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode == null ? null : crtUserCode.trim();
    }

    @JsonIgnore
    public String getCrtOrgCode() {
        return crtOrgCode;
    }

    public void setCrtOrgCode(String crtOrgCode) {
        this.crtOrgCode = crtOrgCode == null ? null : crtOrgCode.trim();
    }

    @JsonIgnore
    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    @JsonIgnore
    public String getUpdUserCode() {
        return updUserCode;
    }

    public void setUpdUserCode(String updUserCode) {
        this.updUserCode = updUserCode == null ? null : updUserCode.trim();
    }

    @JsonIgnore
    public String getUpdOrgCode() {
        return updOrgCode;
    }

    public void setUpdOrgCode(String updOrgCode) {
        this.updOrgCode = updOrgCode == null ? null : updOrgCode.trim();
    }

    @JsonIgnore
    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public LinkedHashMap getStudent() {
        return student;
    }

    public void setStudent(LinkedHashMap student) {
        this.student = student;
    }

    public String getSchoolUrl() {
        return schoolUrl;
    }

    public void setSchoolUrl(String schoolUrl) {
        this.schoolUrl = schoolUrl;
    }

    public String getParentMobile() {
        return parentMobile;
    }

    public void setParentMobile(String parentMobile) {
        this.parentMobile = parentMobile;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bindId=").append(bindId);
        sb.append(", schoolCode=").append(schoolCode);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", studentId=").append(studentId);
        sb.append(", studentCode=").append(studentCode);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentCode=").append(parentCode);
        sb.append(", status=").append(status);
        sb.append(", auditDate=").append(auditDate);
        sb.append(", auditorCode=").append(auditorCode);
        sb.append(", crtUserCode=").append(crtUserCode);
        sb.append(", crtOrgCode=").append(crtOrgCode);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", updUserCode=").append(updUserCode);
        sb.append(", updOrgCode=").append(updOrgCode);
        sb.append(", updDate=").append(updDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}