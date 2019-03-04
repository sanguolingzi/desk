package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

import java.util.Date;
import java.util.List;

public class StudyTest implements IBasePO<String> {
    /**
     * 同步测验ID
     */
    private String testId;

    /**
     * 掌握情况ID
     */
    private String masterId;

    /**
     * 测验类型(0 教材，1 知识点)
     */
    private Integer type;

    /**
     * 学生编码
     */
    private String studentCode;

    /**
     * 总分值
     */
    private Integer score;

    /**
     * 星值（难度）
     */
    private Integer star;

    /**
     * 1.基础 2.巩固 3.拔高
     */
    private Integer rank;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 答题用时(分钟)
     */
    private Integer useTime;

    /**
     * 答题时间
     */
    private Date answerTime;

    private List<StudyTestAnswer> answers;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.testId;
    }

    @Override
    public void setId(String testId) {
        this.testId = testId == null ? null : testId.trim();
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId == null ? null : testId.trim();
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId == null ? null : masterId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public List<StudyTestAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<StudyTestAnswer> answers) {
        this.answers = answers;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", testId=").append(testId);
        sb.append(", masterId=").append(masterId);
        sb.append(", type=").append(type);
        sb.append(", studentCode=").append(studentCode);
        sb.append(", score=").append(score);
        sb.append(", star=").append(star);
        sb.append(", rank=").append(rank);
        sb.append(", status=").append(status);
        sb.append(", useTime=").append(useTime);
        sb.append(", answerTime=").append(answerTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}