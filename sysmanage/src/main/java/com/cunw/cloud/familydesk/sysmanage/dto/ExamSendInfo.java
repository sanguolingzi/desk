package com.cunw.cloud.familydesk.sysmanage.dto;

import com.cunw.cloud.framework.model.IBasePO;
import com.cunw.cloud.framework.model.ICreatePO;
import com.cunw.cloud.framework.model.IUpdatePO;

import java.util.Date;

public class ExamSendInfo implements IBasePO<String>, IUpdatePO, ICreatePO {
    /**
     * id
     */
    private String id;

    /**
     * 考试id
     */
    private String examId;

    /**
     * 学生编号
     */
    private String studentCode;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 老师名称
     */
    private String teacherName;

    /**
     * 老师编号
     */
    private String teacherCode;

    /**
     * 各科目总分
     */
    private Float totalScore;

    /**
     * 平均分（总分/科目数）
     */
    private Float avgScore;

    /**
     * 班级排名
     */
    private Integer classRank;

    /**
     * 科目成绩，格式注释：
     * [{
     * “科目id”: 1，
     * “科目名称"："数学"
     * "成绩"：“22”，
     * "单科的班级排名"：“1”，
     * "单科平均分"：“15”，
     * "单科等级"：“A”
     * }]
     */
    private String subjects;

    /**
     * 评语
     */
    private String remark;

    /**
     * 考试人数
     */
    private Integer examPersonSize;

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

    /**
     * 总分平均分（班级总分/考试人数）
     */
    private Float totalAvgScore;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId == null ? null : examId.trim();
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode == null ? null : teacherCode.trim();
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Float avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getClassRank() {
        return classRank;
    }

    public void setClassRank(Integer classRank) {
        this.classRank = classRank;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects == null ? null : subjects.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getExamPersonSize() {
        return examPersonSize;
    }

    public void setExamPersonSize(Integer examPersonSize) {
        this.examPersonSize = examPersonSize;
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

    public Float getTotalAvgScore() {
        return totalAvgScore;
    }

    public void setTotalAvgScore(Float totalAvgScore) {
        this.totalAvgScore = totalAvgScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", examId=").append(examId);
        sb.append(", studentCode=").append(studentCode);
        sb.append(", studentName=").append(studentName);
        sb.append(", teacherName=").append(teacherName);
        sb.append(", teacherCode=").append(teacherCode);
        sb.append(", totalScore=").append(totalScore);
        sb.append(", avgScore=").append(avgScore);
        sb.append(", classRank=").append(classRank);
        sb.append(", subjects=").append(subjects);
        sb.append(", remark=").append(remark);
        sb.append(", examPersonSize=").append(examPersonSize);
        sb.append(", crtUserCode=").append(crtUserCode);
        sb.append(", crtOrgCode=").append(crtOrgCode);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", updUserCode=").append(updUserCode);
        sb.append(", updOrgCode=").append(updOrgCode);
        sb.append(", updDate=").append(updDate);
        sb.append(", totalAvgScore=").append(totalAvgScore);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}