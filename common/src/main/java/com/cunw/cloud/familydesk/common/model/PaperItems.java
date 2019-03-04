package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import com.cunw.cloud.framework.model.ICreatePO;
import com.cunw.cloud.framework.model.IUpdatePO;
import com.cunw.cloud.resource.model.Question;

import java.util.Date;

public class PaperItems implements IBasePO<String>, ICreatePO, IUpdatePO {
    /**
     * 试卷题目id
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
     * 分数
     */
    private Integer score;

    /**
     * 题型：1-填空题 2- 选择题 3-阅读
     */
    private String questType;

    /**
     * 答题时长
     */
    private Integer answerTime;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType == null ? null : questType.trim();
    }

    public Integer getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Integer answerTime) {
        this.answerTime = answerTime;
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

    public Question getQuestion() {
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
        sb.append(", score=").append(score);
        sb.append(", questType=").append(questType);
        sb.append(", answerTime=").append(answerTime);
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