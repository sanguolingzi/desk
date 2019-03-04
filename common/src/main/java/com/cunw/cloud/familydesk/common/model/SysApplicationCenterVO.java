package com.cunw.cloud.familydesk.common.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysApplicationCenterVO {
    private String appId;

    private String appName;

    private String appDesc;

    private String appLogo;

    private Integer downloadNum;
    /**
     * 单位 KB
     */
    private Double appSize;

    private Integer appRecommend;

    private Date crtDate;

    private String version;

    private String fileId;

    private String packageName;
}
