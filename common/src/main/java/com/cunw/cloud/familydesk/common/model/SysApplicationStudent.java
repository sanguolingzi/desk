package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class SysApplicationStudent implements IBasePO<String> {
    private String appStuId;

    private String studentId;

    private String appId;

    private String deskId;

    /**
     * 1 未删除 0 已删除
     */
    private Integer status;

    private Date crtDate;

    /**
     * 1 未评价  0评价
     */
    private Integer doEvaluate;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.appStuId;
    }

    @Override
    public void setId(String appStuId) {
        this.appStuId = appStuId == null ? null : appStuId.trim();
    }

    public String getAppStuId() {
        return appStuId;
    }

    public void setAppStuId(String appStuId) {
        this.appStuId = appStuId == null ? null : appStuId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getDeskId() {
        return deskId;
    }

    public void setDeskId(String deskId) {
        this.deskId = deskId == null ? null : deskId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Integer getDoEvaluate() {
        return doEvaluate;
    }

    public void setDoEvaluate(Integer doEvaluate) {
        this.doEvaluate = doEvaluate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", appStuId=").append(appStuId);
        sb.append(", studentId=").append(studentId);
        sb.append(", appId=").append(appId);
        sb.append(", deskId=").append(deskId);
        sb.append(", status=").append(status);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", doEvaluate=").append(doEvaluate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}