package com.cunw.cloud.familydesk.common.dto;

import lombok.Data;

/**
 * 功能/ 模块：科目和试卷数量
 *
 * @author 
 * @version 1.0-SNAPSHORT 2018/4/2
 *          类描述
 *          修订历史：
 *          日期：2018/4/2
 *          作者：陈波
 *          参考：
 *          描述：
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Data
public class SubjectDTO {

    private String subjectId;

    /**
     * 科目
     */
    private String subjectName;

    /**
     * 试卷数量
     */
    private Long paperCount;

    public SubjectDTO(){

    }

    public SubjectDTO(String subjectId, String subjectName, Long count){
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.paperCount = count;
    }

}
