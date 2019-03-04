package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

import java.util.Date;

public class KnowledgeMaster implements IBasePO<String> {
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
     * 关联知识点
     */
    private String knowledgeId;

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

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId == null ? null : knowledgeId.trim();
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
        return "KnowledgeMaster{" +
                "masterId='" + masterId + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", stage='" + stage + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", knowledgeId='" + knowledgeId + '\'' +
                ", score=" + score +
                ", star=" + star +
                ", rank=" + rank +
                ", crtUserCode='" + crtUserCode + '\'' +
                ", crtDate=" + crtDate +
                ", updDate=" + updDate +
                '}';
    }
}