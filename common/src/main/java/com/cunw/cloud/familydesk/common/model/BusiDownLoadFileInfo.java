package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

import java.util.Date;

public class BusiDownLoadFileInfo implements IBasePO<String> {
    private String fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小 单位KB
     */
    private Integer size;

    /**
     * 文件类型 0 文档 1 幻灯片 2 图片 3 视频 
     */
    private Integer fileType;

    /**
     * 文件后缀名
     */
    private String exten;

    private byte[] fileByte;

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

    public String getExten() {
        return exten;
    }

    public void setExten(String exten) {
        this.exten = exten;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileId=").append(fileId);
        sb.append(", fileName=").append(fileName);
        sb.append(", size=").append(size);
        sb.append(", fileType=").append(fileType);
        sb.append(", exten=").append(exten);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}