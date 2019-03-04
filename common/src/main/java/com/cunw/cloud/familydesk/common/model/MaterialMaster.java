package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

import java.util.Date;

public class MaterialMaster implements IBasePO<String> {
    /**
     * 掌握情况ID
     */
    private String masterId;

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
     * 关联章节
     */
    private String chapterId;

    /**
     * 掌握分值
     */
    private Integer score;

    /**
     * 掌握星值
     */
    private Integer star;

    /**
     * 1.基础 2.巩固 3.拔高
     */
    private Integer rank;

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
        return this.masterId;
    }

    @Override
    public void setId(String masterId) {
        this.masterId = masterId == null ? null : masterId.trim();
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId == null ? null : masterId.trim();
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

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId == null ? null : chapterId.trim();
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", masterId=").append(masterId);
        sb.append(", studentCode=").append(studentCode);
        sb.append(", stage=").append(stage);
        sb.append(", subjectId=").append(subjectId);
        sb.append(", versionId=").append(versionId);
        sb.append(", bookid=").append(bookid);
        sb.append(", chapterId=").append(chapterId);
        sb.append(", score=").append(score);
        sb.append(", star=").append(star);
        sb.append(", crtUserCode=").append(crtUserCode);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", updDate=").append(updDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}