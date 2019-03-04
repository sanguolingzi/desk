package com.cunw.cloud.familydesk.common.dto;

import com.cunw.cloud.framework.utils.base.StringUtils;
import com.cunw.cloud.resource.dto.AnswerDTO;
import com.cunw.cloud.resource.model.Question;
import com.cunw.cloud.familydesk.common.model.StudySmartAssemblyQuestion;

import java.util.List;

public class QuestionAnswerDTO {

    /**
     * 题目ID
     */
    private String questId;

    /**
     * 题目顺序
     */
    private Integer questOrder;

    /**
     * 是否正确
     */
    private Integer isRight;

    private String rightAnswer; //正确答案

    private String explanation;  //答案解析

    private String stem; //题目

    private String options; //题目选项

    private String studentAnswer; //学生答案

    private Integer type; //题目类型id

    private String typeName; //题目类型

    private static final long serialVersionUID = 1L;

    /**
     * 题目子题
     */
    private List<Question> subsets;


    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId == null ? null : questId.trim();
    }

    public Integer getQuestOrder() {
        return questOrder;
    }

    public void setQuestOrder(Integer questOrder) {
        this.questOrder = questOrder;
    }

    public Integer getIsRight() {
        return isRight;
    }

    public void setIsRight(Integer isRight) {
        this.isRight = isRight;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Question> getSubsets() {
        return subsets;
    }

    public void setSubsets(List<Question> subsets) {
        this.subsets = subsets;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", questId=").append(questId);
        sb.append(", questOrder=").append(questOrder);
        sb.append(", isRight=").append(isRight);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public void parse(AnswerDTO answerDTO, StudySmartAssemblyQuestion studySmartAssemblyQuestion){
        this.questId = answerDTO.getQuestionId();
        this.explanation = answerDTO.getExplanation();
        this.questOrder = studySmartAssemblyQuestion.getQuestOrder();
        this.isRight = studySmartAssemblyQuestion.getIsRight();
        if(answerDTO.getTypeName().equals("判断题") && StringUtils.isEmpty(answerDTO.getAnswer())){
            this.rightAnswer = "0";
        }
        this.rightAnswer = answerDTO.getAnswer();
        this.explanation = answerDTO.getExplanation();
        this.stem = answerDTO.getStem();
        this.options = answerDTO.getOptions();
        this.type = answerDTO.getType();
        this.typeName = answerDTO.getTypeName();
        this.studentAnswer = studySmartAssemblyQuestion.getAnswer();
    }
}