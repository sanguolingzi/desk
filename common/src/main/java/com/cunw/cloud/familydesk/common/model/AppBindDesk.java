package com.cunw.cloud.familydesk.common.model;

import lombok.Data;

import java.util.List;

/**
 * app绑定课桌数据model
 */
@Data
public class AppBindDesk {

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
    /**
     * 绑定设备
     */
    private String deviceNo;

    /**
     * 学生数据集合
     */
    private List<SysStudent> studentList;


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", parentId=").append(parentId);
        sb.append(", parentName=").append(parentName);
        sb.append(", sex=").append(sex);
        sb.append(", phone=").append(phone);
        sb.append(", deviceNo=").append(deviceNo);
        sb.append(", studentList=[");
        if(studentList!=null && !studentList.isEmpty()){
            for(SysStudent sysStudent:studentList){
                sb.append("{");
                sb.append(sysStudent.toString());
                sb.append("},");
            }
        }
        sb.append("]");
        sb.append("]");

        return sb.toString();
    }
}
