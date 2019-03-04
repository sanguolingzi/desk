package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class SysStudentParentUnbind implements IBasePO<String> {
    private String stuParUnbindId;

    private String studentId;

    private String parentId;

    /**
     * 记录产生时间
     */
    private Date crtDate;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.stuParUnbindId;
    }

    @Override
    public void setId(String stuParUnbindId) {
        this.stuParUnbindId = stuParUnbindId == null ? null : stuParUnbindId.trim();
    }

    public String getStuParUnbindId() {
        return stuParUnbindId;
    }

    public void setStuParUnbindId(String stuParUnbindId) {
        this.stuParUnbindId = stuParUnbindId == null ? null : stuParUnbindId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stuParUnbindId=").append(stuParUnbindId);
        sb.append(", studentId=").append(studentId);
        sb.append(", parentId=").append(parentId);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}