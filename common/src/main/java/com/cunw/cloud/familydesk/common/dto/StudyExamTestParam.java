package com.cunw.cloud.familydesk.common.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class StudyExamTestParam{

    /**
     * 学生编码
     */
    @NotBlank(message = "学生code不能为空")
    private String studentCode;

    /**
     * 教材目录
     */
    @NotBlank(message = "目录不能为空")
    private String dirId;

    /**
     * 试卷ID
     */
    @NotBlank(message = "试卷id不能为空")
    private String paperId;

    /**
     * 试卷名称
     */
    @NotBlank(message = "试卷名称不能为空")
    private String paperName;

    /**
     * 开始时间
     */
    @NotBlank(message = "开始时间不能为空")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @NotBlank(message = "结束时间不能为空")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 答案
     */
    private List<AnswerParam> answerList = new ArrayList<>();
}