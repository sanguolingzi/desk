package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class SysParentDeviceBind implements IBasePO<String> {
    private String bindId;

    private String parentId;

    private String deviceId;

    /**
     * 1 未删除 0 已删除
     */
    private Integer status;

    private Date crtDate;

    private static final long serialVersionUID = 1L;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bindId=").append(bindId);
        sb.append(", parentId=").append(parentId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", status=").append(status);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}