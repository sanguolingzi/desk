package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

public class SysStageGrade implements IBasePO<String> {
    private String stageGradeId;

    /**
     * 学段id
     */
    private String stageId;

    /**
     * 年级id
     */
    private String gradeId;

    /**
     * 1 可用 0 删除
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.stageGradeId;
    }

    @Override
    public void setId(String stageGradeId) {
        this.stageGradeId = stageGradeId == null ? null : stageGradeId.trim();
    }

    public String getStageGradeId() {
        return stageGradeId;
    }

    public void setStageGradeId(String stageGradeId) {
        this.stageGradeId = stageGradeId == null ? null : stageGradeId.trim();
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId == null ? null : stageId.trim();
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId == null ? null : gradeId.trim();
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
        sb.append(", stageGradeId=").append(stageGradeId);
        sb.append(", stageId=").append(stageId);
        sb.append(", gradeId=").append(gradeId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}