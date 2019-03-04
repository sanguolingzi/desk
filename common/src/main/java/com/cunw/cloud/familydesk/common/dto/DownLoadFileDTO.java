package com.cunw.cloud.familydesk.common.dto;

import lombok.Data;

/** 答题表
 * @ClassName AnswerParam
 * @dESCRIPTION TODO
 * @Author chenbo
 * @Date 2018/8/16
 * @Version 1.0
 **/
@Data
public class DownLoadFileDTO {

    private String fileName;

    private String fileType;

    private String fileUrl;

    public DownLoadFileDTO(){

    }

    public DownLoadFileDTO(String fileName, String fileType, String fileUrl){
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUrl = fileUrl;
    }

}
