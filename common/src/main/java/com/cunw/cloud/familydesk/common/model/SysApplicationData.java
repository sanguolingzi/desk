package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

public class SysApplicationData implements IBasePO<String> {
    private String appId;

    /**
     * 下载次数
     */
    private Integer download;

    /**
     * 人气
     */
    private Integer favourite;

    /**
     * 好评次数
     */
    private Integer evaluate;

    /**
     * 1 未删除 0 已删除
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.appId;
    }

    @Override
    public void setId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public Integer getFavourite() {
        return favourite;
    }

    public void setFavourite(Integer favourite) {
        this.favourite = favourite;
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
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
        sb.append(", appId=").append(appId);
        sb.append(", download=").append(download);
        sb.append(", favourite=").append(favourite);
        sb.append(", evaluate=").append(evaluate);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}