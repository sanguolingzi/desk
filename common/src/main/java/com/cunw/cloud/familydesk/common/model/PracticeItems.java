package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import com.cunw.cloud.resource.model.Question;

import java.util.Date;

public class PracticeItems implements IBasePO<String> {
    /**
     * 练习题目id
     */
    private String itemId;

    /**
     * 试卷id
     */
    private String paperId;

    /**
     * 题目id
     */
    private String questId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 班级编码
     */
    private String classCode;

    /**
     * 老师编码
     */
    private String teacherCode;

    /**
     * 答题时长
     */
    private Integer answerTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 答题类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建人
     */
    private String crtUserCode;

    /**
     * 创建机构
     */
    private String crtOrgCode;

    /**
     * 创建时间
     */
    private Date crtDate;

    /**
     * 修改人
     */
    private String updUserCode;

    /**
     * 修改机构
     */
    private String updOrgCode;

    /**
     * 修改日期
     */
    private Date updDate;

    /*
     * 题库
    */
    private Question question;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.itemId;
    }

    @Override
    public void setId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId == null ? null : questId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode == null ? null : teacherCode.trim();
    }

    public Integer getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Integer answerTime) {
        this.answerTime = answerTime;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCrtUserCode() {
        return crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode == null ? null : crtUserCode.trim();
    }

    public String getCrtOrgCode() {
        return crtOrgCode;
    }

    public void setCrtOrgCode(String crtOrgCode) {
        this.crtOrgCode = crtOrgCode == null ? null : crtOrgCode.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getUpdUserCode() {
        return updUserCode;
    }

    public void setUpdUserCode(String updUserCode) {
        this.updUserCode = updUserCode == null ? null : updUserCode.trim();
    }

    public String getUpdOrgCode() {
        return updOrgCode;
    }

    public void setUpdOrgCode(String updOrgCode) {
        this.updOrgCode = updOrgCode == null ? null : updOrgCode.trim();
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public Question getQuestions() {
        return question;
    }

    public void setQuestions(Question questions) {
        this.question = questions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", paperId=").append(paperId);
        sb.append(", questId=").append(questId);
        sb.append(", courseId=").append(courseId);
        sb.append(", classCode=").append(classCode);
        sb.append(", teacherCode=").append(teacherCode);
        sb.append(", answerTime=").append(answerTime);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", crtUserCode=").append(crtUserCode);
        sb.append(", crtOrgCode=").append(crtOrgCode);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", updUserCode=").append(updUserCode);
        sb.append(", updOrgCode=").append(updOrgCode);
        sb.append(", updDate=").append(updDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}