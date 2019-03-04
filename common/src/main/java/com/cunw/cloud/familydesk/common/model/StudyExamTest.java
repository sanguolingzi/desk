package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

import java.util.Date;

public class StudyExamTest implements IBasePO<String> {
    /**
     * 冲刺记录ID
     */
    private String testId;

    /**
     * 学生编码
     */
    private String studentCode;

    /**
     * 学段
     */
    private String stage;

    /**
     * 所属学科
     */
    private String subjectId;

    /**
     * 教材版本ID
     */
    private String versionId;

    /**
     * 教材册别
     */
    private String bookid;

    /**
     * 试卷ID
     */
    private String paperId;

    /**
     * 试卷ID
     */
    private String paperName;

    /**
     * 总得分
     */
    private Integer score;

    /**
     * 用时
     */
    private Integer useTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建人
     */
    private String crtUserCode;

    /**
     * 创建时间
     */
    private Date crtDate;

    /**
     * 修改时间
     */
    private Date updDate;



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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage == null ? null : stage.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId == null ? null : versionId.trim();
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid == null ? null : bookid.trim();
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCrtUserCode() {
        return crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode == null ? null : crtUserCode.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", testId=").append(testId);
        sb.append(", studentCode=").append(studentCode);
        sb.append(", stage=").append(stage);
        sb.append(", subjectId=").append(subjectId);
        sb.append(", versionId=").append(versionId);
        sb.append(", bookid=").append(bookid);
        sb.append(", paperId=").append(paperId);
        sb.append(", score=").append(score);
        sb.append(", useTime=").append(useTime);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", crtUserCode=").append(crtUserCode);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", updDate=").append(updDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}