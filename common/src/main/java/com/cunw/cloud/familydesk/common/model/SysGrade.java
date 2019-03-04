package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

public class SysGrade implements IBasePO<String> {
    /**
     * 年级id
     */
    private String gradeId;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 年级code
     */
    private String gradeCode;

    /**
     * 1 可用 0 删除
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.gradeId;
    }

    @Override
    public void setId(String gradeId) {
        this.gradeId = gradeId == null ? null : gradeId.trim();
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId == null ? null : gradeId.trim();
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName == null ? null : gradeName.trim();
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode == null ? null : gradeCode.trim();
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
        sb.append(", gradeId=").append(gradeId);
        sb.append(", gradeName=").append(gradeName);
        sb.append(", gradeCode=").append(gradeCode);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}