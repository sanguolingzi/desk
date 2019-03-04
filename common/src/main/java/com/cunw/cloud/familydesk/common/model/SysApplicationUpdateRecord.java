package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class SysApplicationUpdateRecord implements IBasePO<String> {
    private String recordId;

    private String appId;

    private String version;

    private String description;
    /**
     * 1 可用 0删除
     */
    private Integer status;

    private Date crtDate;

    /**
     * 单位 KB
     */
    private Integer appSize;

    /**
     * 关联文件id
     */
    private String fileId;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.recordId;
    }

    @Override
    public void setId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Integer getAppSize() {
        return appSize;
    }

    public void setAppSize(Integer appSize) {
        this.appSize = appSize;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordId=").append(recordId);
        sb.append(", appId=").append(appId);
        sb.append(", version=").append(version);
        sb.append(", description=").append(description);
        sb.append(", status=").append(status);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", appSize=").append(appSize);
        sb.append(", fileId=").append(fileId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}