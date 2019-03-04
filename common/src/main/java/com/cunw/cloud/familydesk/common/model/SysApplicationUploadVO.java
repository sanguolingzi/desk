package com.cunw.cloud.familydesk.common.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class SysApplicationUploadVO {
    @NotNull(message = "应用文件id不能为空")
    @NotEmpty(message = "应用文件id不能为空")
    private String appFileId;

    @NotNull(message = "应用预览图文件id不能为空")
    @NotEmpty(message = "应用预览图文件id不能为空")
    private String imgFileIds;

    @NotNull(message = "应用logo文件id不能为空")
    @NotEmpty(message = "应用logo文件id不能为空")
    private String logoFileId;

    @NotNull(message = "学段id不能为空")
    @NotEmpty(message = "学段id不能为空")
    private String stageId;

    @NotNull(message = "分类id不能为空")
    @NotEmpty(message = "分类id不能为空")
    private String appCategoryId;

    private String appId;

    @NotNull(message = "应用名称不能为空")
    @NotEmpty(message = "应用名称不能为空")
    private String appName;

    @NotNull(message = "应用描述不能为空")
    @NotEmpty(message = "应用描述不能为空")
    private String appDesc;

    @NotNull(message = "应用版本描述不能为空")
    @NotEmpty(message = "应用版本描述不能为空")
    private String description;

    @NotNull(message = "应用版本不能为空")
    @NotEmpty(message = "应用版本不能为空")
    private String version;

    @NotNull(message = "模块结构的包名不能为空")
    @NotEmpty(message = "模块结构的包名不能为空")
    private String packageName;
}
