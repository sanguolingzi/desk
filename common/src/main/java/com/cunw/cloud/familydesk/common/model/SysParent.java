package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class SysParent implements IBasePO<String> {
    private String parentId;

    private String parentName;

    /**
     * 1 未删除 0 已删除
     */
    private Integer status;

    private String headImg;

    private Date crtDate;

    /**
     * 0 女 1 男
     */
    private Integer sex;

    private String phone;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.parentId;
    }

    @Override
    public void setId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", parentId=").append(parentId);
        sb.append(", parentName=").append(parentName);
        sb.append(", status=").append(status);
        sb.append(", headImg=").append(headImg);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", sex=").append(sex);
        sb.append(", phone=").append(phone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}