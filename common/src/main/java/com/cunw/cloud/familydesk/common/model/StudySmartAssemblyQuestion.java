package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

import java.util.Date;

public class StudySmartAssemblyQuestion implements IBasePO<String> {
    /**
     * 组题题目ID
     */
    private String assemblyQuestId;

    /**
     * 组题ID
     */
    private String assemblyId;

    /**
     * 题目ID
     */
    private String questId;

    /**
     * 答题
     */
    private String answer;

    /**
     * 题目顺序
     */
    private Integer questOrder;

    /**
     * 是否正确
     */
    private Integer isRight;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 答题时间
     */
    private Date answerTime;

    private String explanation;

    private String rightAnswer;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.assemblyQuestId;
    }

    @Override
    public void setId(String assemblyQuestId) {
        this.assemblyQuestId = assemblyQuestId == null ? null : assemblyQuestId.trim();
    }

    public String getAssemblyQuestId() {
        return assemblyQuestId;
    }

    public void setAssemblyQuestId(String assemblyQuestId) {
        this.assemblyQuestId = assemblyQuestId == null ? null : assemblyQuestId.trim();
    }

    public String getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId == null ? null : assemblyId.trim();
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId == null ? null : questId.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", assemblyQuestId=").append(assemblyQuestId);
        sb.append(", assemblyId=").append(assemblyId);
        sb.append(", questId=").append(questId);
        sb.append(", answer=").append(answer);
        sb.append(", questOrder=").append(questOrder);
        sb.append(", isRight=").append(isRight);
        sb.append(", status=").append(status);
        sb.append(", answerTime=").append(answerTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    public StudySmartAssemblyQuestion(){

    }

    public StudySmartAssemblyQuestion(String id, String assemblyId, String questId, String answer, Integer index){
        this.assemblyQuestId = id;
        this.assemblyId = assemblyId;
        this.questId = questId;
        this.answer = answer;
        this.questOrder = index;
        this.answerTime = new Date();
    }

}