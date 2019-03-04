package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class SysApplicationCenter implements IBasePO<String> {
    private String appId;
    /**
     * 学段id
     */
    private Integer stageId;
    /**
     * 分类id
     */
    private String appCategoryId;

    /**
     * 应用名称code
     */
    private String appName;
    /**
     * 应用描述
     */
    private String appDesc;

    private String appLogo;

    /**
     * 1 未删除 0 已删除
     */
    private Integer status;

    /**
     * 是否推荐 1 是 0 否
     */
    private Integer appRecommend;

    private Date crtDate;

    /**
     * 应用路径由开发者提供全局唯一
     */
    private String packageName;

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

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getAppCategoryId() {
        return appCategoryId;
    }

    public void setAppCategoryId(String appCategoryId) {
        this.appCategoryId = appCategoryId == null ? null : appCategoryId.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc == null ? null : appDesc.trim();
    }

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo == null ? null : appLogo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAppRecommend() {
        return appRecommend;
    }

    public void setAppRecommend(Integer appRecommend) {
        this.appRecommend = appRecommend;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", appId=").append(appId);
        sb.append(", stageId=").append(stageId);
        sb.append(", appCategoryId=").append(appCategoryId);
        sb.append(", appName=").append(appName);
        sb.append(", appDesc=").append(appDesc);
        sb.append(", appLogo=").append(appLogo);
        sb.append(", status=").append(status);
        sb.append(", appRecommend=").append(appRecommend);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", packageName=").append(packageName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}