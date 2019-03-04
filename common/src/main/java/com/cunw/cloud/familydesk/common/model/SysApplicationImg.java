package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

public class SysApplicationImg implements IBasePO<String> {
    private String appImgId;

    private String appId;

    private String imgUrl;

    /**
     * 1 未删除 0 已删除
     */
    private Integer status;

    /**
     * 关联文件id
     */
    private String fileId;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.appImgId;
    }

    @Override
    public void setId(String appImgId) {
        this.appImgId = appImgId == null ? null : appImgId.trim();
    }

    public String getAppImgId() {
        return appImgId;
    }

    public void setAppImgId(String appImgId) {
        this.appImgId = appImgId == null ? null : appImgId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", appImgId=").append(appImgId);
        sb.append(", appId=").append(appId);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", status=").append(status);
        sb.append(", fileId=").append(fileId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}