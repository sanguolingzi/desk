package com.cunw.cloud.familydesk.common.model;

import lombok.Data;
/**
 * app绑定课桌结果数据
 */
@Data
public class AppBindDeskResult {

    /**
     * 0 无结果 1有结果
     */
    private Integer status;
    /**
     * 家长id
     */
    private String parentId;
    /**
     * 家长名称
     */
    private String parentName;
    /**
     * 性别 0 女 1男
     */
    private Integer sex;
    /**
     * 手机号码
     */
    private String phone;

    private String accessToken;

    private String refreshToken;

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", status=").append(status);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentName=").append(parentName);
        sb.append(", sex=").append(sex);
        sb.append(", phone=").append(phone);
        sb.append(", accessToken=").append(accessToken);
        sb.append("]");

        return sb.toString();
    }
}
