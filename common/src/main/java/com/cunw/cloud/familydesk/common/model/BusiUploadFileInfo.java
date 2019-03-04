package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class BusiUploadFileInfo implements IBasePO<String> {
    private String fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件存放相对路径
     */
    private String path;

    /**
     * 1 可用 0 不可用
     */
    private Integer status;

    /**
     * 文件大小 单位KB
     */
    private Integer size;

    /**
     * 文件类型 0 文档 1 幻灯片 2 图片 3 视频 
     */
    private Integer fileType;

    private Date crtDate;

    /**
     * 文件后缀名
     */
    private String exten;

    /**
     * 文件引用状态 1 已引用 0 未引用
     */
    private Integer hasReferences;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.fileId;
    }

    @Override
    public void setId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getExten() {
        return exten;
    }

    public void setExten(String exten) {
        this.exten = exten == null ? null : exten.trim();
    }

    public Integer getHasReferences() {
        return hasReferences;
    }

    public void setHasReferences(Integer hasReferences) {
        this.hasReferences = hasReferences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileId=").append(fileId);
        sb.append(", fileName=").append(fileName);
        sb.append(", path=").append(path);
        sb.append(", status=").append(status);
        sb.append(", size=").append(size);
        sb.append(", fileType=").append(fileType);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", exten=").append(exten);
        sb.append(", hasReferences=").append(hasReferences);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}