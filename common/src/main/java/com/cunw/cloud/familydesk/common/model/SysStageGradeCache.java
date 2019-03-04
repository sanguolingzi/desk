package com.cunw.cloud.familydesk.common.model;

import java.io.Serializable;

public class SysStageGradeCache implements Serializable {

    /**
     * 学段id
     */
    private String stageId;

    /**
     * 年级id
     */
    private String gradeCode;


    private String gradeName;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stageId=").append(stageId);
        sb.append(", gradeCode=").append(gradeCode);
        sb.append(", gradeName=").append(gradeName);
        sb.append("]");
        return sb.toString();
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}