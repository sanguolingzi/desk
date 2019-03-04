package com.cunw.cloud.familydesk.common.dto;

import lombok.Data;

@Data
public class StudySmartAssemblyStatisticDTO {

    public String assemblyId;

    public Long rightAnswerCount;

    public Long wrongAnswerCount;

    public Long score;

    public StudySmartAssemblyStatisticDTO(String assemblyId, Long rightAnswerCount, Long wrongAnswerCount, Long score){
        this.assemblyId = assemblyId;
        this.rightAnswerCount = rightAnswerCount;
        this.wrongAnswerCount = wrongAnswerCount;
        this.score = score;
    }

    public StudySmartAssemblyStatisticDTO(){}
}