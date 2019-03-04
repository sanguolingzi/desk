package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class SysAppBindResult implements IBasePO<String> {
    private String bindResultId;

    /**
     * parentId生成的有效令牌
     */
    private String accessToken;

    private String parentId;

    private Date crtDate;

    /**
     * 数据有效期 单位分钟
     */
    private Integer expiredTime;

    /**
     * 1 可用 0不可用
     */
    private Integer status;

    /**
     * 绑定设备id
     */
    private String deviceId;

    private String refreshToken;

    /**
     * 课桌端获取结果绑定信息
     */
    private Date updDate;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.bindResultId;
    }

    @Override
    public void setId(String bindResultId) {
        this.bindResultId = bindResultId == null ? null : bindResultId.trim();
    }

    public String getBindResultId() {
        return bindResultId;
    }

    public void setBindResultId(String bindResultId) {
        this.bindResultId = bindResultId == null ? null : bindResultId.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
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

    public Integer getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Integer expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bindResultId=").append(bindResultId);
        sb.append(", accesstoken=").append(accessToken);
        sb.append(", parentId=").append(parentId);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", expiredTime=").append(expiredTime);
        sb.append(", status=").append(status);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", updDate=").append(updDate);
        sb.append(", refreshToken=").append(refreshToken);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}