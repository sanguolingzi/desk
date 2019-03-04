package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import com.cunw.cloud.framework.model.ICreatePO;
import com.cunw.cloud.framework.model.IUpdatePO;

import java.util.Date;

public class FavorFile implements IBasePO<String>, ICreatePO, IUpdatePO {
    /**
     * 收藏文件id
     */
    private String favorFileId;

    /**
     * 收藏夹id
     */
    private String favorId;

    /**
     * 源文件id
     */
    private String fileId;

    /**
     * 源文件名
     */
    private String fileName;

    /**
     * 文件来源：1-来自我的云盘 2-来自课件 3-来自云资源库
     */
    private String fileSrc;

    /**
     * 源文件URL
     */
    private String fileUrl;

    /**
     * 状态：0-启用 2-禁用
     */
    private String status;

    /**
     * 创建人
     */
    private String crtUserCode;

    /**
     * 创建机构
     */
    private String crtOrgCode;

    /**
     * 创建时间
     */
    private Date crtDate;

    /**
     * 修改人
     */
    private String updUserCode;

    /**
     * 修改机构
     */
    private String updOrgCode;

    /**
     * 修改日期
     */
    private Date updDate;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.favorFileId;
    }

    @Override
    public void setId(String favorFileId) {
        this.favorFileId = favorFileId == null ? null : favorFileId.trim();
    }

    public String getFavorFileId() {
        return favorFileId;
    }

    public void setFavorFileId(String favorFileId) {
        this.favorFileId = favorFileId == null ? null : favorFileId.trim();
    }

    public String getFavorId() {
        return favorId;
    }

    public void setFavorId(String favorId) {
        this.favorId = favorId == null ? null : favorId.trim();
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

    public String getFileSrc() {
        return fileSrc;
    }

    public void setFileSrc(String fileSrc) {
        this.fileSrc = fileSrc == null ? null : fileSrc.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCrtUserCode() {
        return crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode == null ? null : crtUserCode.trim();
    }

    public String getCrtOrgCode() {
        return crtOrgCode;
    }

    public void setCrtOrgCode(String crtOrgCode) {
        this.crtOrgCode = crtOrgCode == null ? null : crtOrgCode.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getUpdUserCode() {
        return updUserCode;
    }

    public void setUpdUserCode(String updUserCode) {
        this.updUserCode = updUserCode == null ? null : updUserCode.trim();
    }

    public String getUpdOrgCode() {
        return updOrgCode;
    }

    public void setUpdOrgCode(String updOrgCode) {
        this.updOrgCode = updOrgCode == null ? null : updOrgCode.trim();
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
        sb.append(", favorFileId=").append(favorFileId);
        sb.append(", favorId=").append(favorId);
        sb.append(", fileId=").append(fileId);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileSrc=").append(fileSrc);
        sb.append(", fileUrl=").append(fileUrl);
        sb.append(", status=").append(status);
        sb.append(", crtUserCode=").append(crtUserCode);
        sb.append(", crtOrgCode=").append(crtOrgCode);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", updUserCode=").append(updUserCode);
        sb.append(", updOrgCode=").append(updOrgCode);
        sb.append(", updDate=").append(updDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}