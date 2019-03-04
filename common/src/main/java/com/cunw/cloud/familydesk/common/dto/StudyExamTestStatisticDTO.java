package com.cunw.cloud.familydesk.common.dto;

import lombok.Data;

@Data
public class StudyExamTestStatisticDTO {
    public String testId;

    public Long rightAnswerCount;

    public Long wrongAnswerCount;

    public Long score;

    public StudyExamTestStatisticDTO(String testId, Long rightAnswerCount, Long wrongAnswerCount, Long score){
        this.testId = testId;
        this.rightAnswerCount = rightAnswerCount;
        this.wrongAnswerCount = wrongAnswerCount;
        this.score = score;
    }

}