package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

public class SysStudentParent implements IBasePO<String> {
    private String stuParId;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 家长id
     */
    private String parentId;

    /**
     * 1 可用 0不可用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.stuParId;
    }

    @Override
    public void setId(String stuParId) {
        this.stuParId = stuParId == null ? null : stuParId.trim();
    }

    public String getStuParId() {
        return stuParId;
    }

    public void setStuParId(String stuParId) {
        this.stuParId = stuParId == null ? null : stuParId.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stuParId=").append(stuParId);
        sb.append(", studentId=").append(studentId);
        sb.append(", parentId=").append(parentId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}