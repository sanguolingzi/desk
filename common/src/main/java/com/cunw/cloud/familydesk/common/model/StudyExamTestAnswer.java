package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

import java.util.Date;

public class StudyExamTestAnswer implements IBasePO<String> {
    /**
     * 冲刺答题ID
     */
    private String answerId;

    /**
     * 冲刺记录ID
     */
    private String testId;

    /**
     * 学生编码
     */
    private String studentCode;

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
     * 分值
     */
    private Integer score;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 答题时间
     */
    private Date answerTime;

    private String rightAnswer; //正确答案

    private String explanation;  //答案解析

    private String stem; //题目

    private String options; //题目选项

    private Integer type;

    private String typeName;

    private String difficultName;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.answerId;
    }

    @Override
    public void setId(String answerId) {
        this.answerId = answerId == null ? null : answerId.trim();
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId == null ? null : answerId.trim();
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId == null ? null : testId.trim();
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public String getDifficultName() {
        return difficultName;
    }

    public void setDifficultName(String difficultName) {
        this.difficultName = difficultName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", answerId=").append(answerId);
        sb.append(", testId=").append(testId);
        sb.append(", studentCode=").append(studentCode);
        sb.append(", questId=").append(questId);
        sb.append(", answer=").append(answer);
        sb.append(", questOrder=").append(questOrder);
        sb.append(", isRight=").append(isRight);
        sb.append(", score=").append(score);
        sb.append(", status=").append(status);
        sb.append(", answerTime=").append(answerTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}