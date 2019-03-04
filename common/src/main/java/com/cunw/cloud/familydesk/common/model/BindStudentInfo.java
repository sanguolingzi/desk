package com.cunw.cloud.familydesk.common.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class BindStudentInfo {

    /**
     * 课桌学生id
     */
    @NotNull(message = "课桌学生id不能为空")
    @NotEmpty(message = "课桌学生id不能为空")
    private String studentId;

    /**
     * 同步B端的学生code
     */
    @NotNull(message = "学生code不能为空")
    @NotEmpty(message = "学生code不能为空")
    private String syncStudentCode;

    @NotNull(message = "学生id不能为空")
    @NotEmpty(message = "学生id不能为空")
    private String syncStudentId;

    /**
     * 同步B端的学校code
     */
    @NotNull(message = "学校code不能为空")
    @NotEmpty(message = "学校code不能为空")
    private String syncSchoolCode;

    /**
     * 同步B端的班级code
     */
    @NotNull(message = "班级code不能为空")
    @NotEmpty(message = "班级code不能为空")
    private String syncClassCode;


    private static final long serialVersionUID = 1L;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", studentId=").append(studentId);
        sb.append(", syncStudentCode=").append(syncStudentCode);
        sb.append(", syncSchoolCode=").append(syncSchoolCode);
        sb.append(", syncClassCode=").append(syncClassCode);
        sb.append(", syncStudentId=").append(syncStudentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getSyncStudentCode() {
        return syncStudentCode;
    }

    public void setSyncStudentCode(String syncStudentCode) {
        this.syncStudentCode = syncStudentCode;
    }

    public String getSyncSchoolCode() {
        return syncSchoolCode;
    }

    public void setSyncSchoolCode(String syncSchoolCode) {
        this.syncSchoolCode = syncSchoolCode;
    }

    public String getSyncClassCode() {
        return syncClassCode;
    }

    public void setSyncClassCode(String syncClassCode) {
        this.syncClassCode = syncClassCode;
    }

    public String getSyncStudentId() {
        return syncStudentId;
    }

    public void setSyncStudentId(String syncStudentId) {
        this.syncStudentId = syncStudentId;
    }
}