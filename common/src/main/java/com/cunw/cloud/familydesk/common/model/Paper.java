package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import com.cunw.cloud.framework.model.ICreatePO;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.List;

public class Paper implements IBasePO<String>,ICreatePO {
    /**
     * 试卷id
     */
    private String paperId;

    /**
     * 目录id
     */
    @NotBlank(message = "目录ID不能为空！")
    private String dirId;

    /**
     * 试卷名
     */
    @NotBlank(message = "试卷名不能为空！")
    private String paperName;

    /**
     * 试卷类别：1-测试 2-随堂练习
     */
//    @NotBlank(message = "试卷类别不能为空！")
    private String paperType;

    /**
     * 学科
     */
    private String subject;

    /**
     * 考试范围
     */
    private String examScope;

    /**
     * 考试时间
     */
    private Date examDate;

    /**
     * 考试时长
     */
    private Integer answerTime;

    /**
     * 命题人
     */
    private String setPeople;

    /**
     * 下载次数
     */
    private Integer downloadNum;

    /**
     * 试卷文件URL
     */
    private String fileUrl;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否答题
     * 0:未答   1:已答
     */
    private Integer isAnswer;

    /**
     * 智能测试id
     */
    private String assemblyId;


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
    * 试卷题目
    */
    private List<PaperItems> paperItems;

    /*
     * 随堂练习题
     */
    private List<PracticeItems> practiceItems;

    /*
     * 答题分数
     */
    private Integer score;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.paperId;
    }

    @Override
    public void setId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getDirId() {
        return dirId;
    }

    public void setDirId(String dirId) {
        this.dirId = dirId == null ? null : dirId.trim();
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName == null ? null : paperName.trim();
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType == null ? null : paperType.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getExamScope() {
        return examScope;
    }

    public void setExamScope(String examScope) {
        this.examScope = examScope == null ? null : examScope.trim();
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Integer getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Integer answerTime) {
        this.answerTime = answerTime;
    }

    public String getSetPeople() {
        return setPeople;
    }

    public void setSetPeople(String setPeople) {
        this.setPeople = setPeople == null ? null : setPeople.trim();
    }

    public Integer getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public List<PaperItems> getPaperItems() {return paperItems;}

    public void setPaperItems(List<PaperItems> paperItems) {this.paperItems = paperItems;}

    public List<PracticeItems> getPracticeItems() {return practiceItems;}

    public void setPracticeItems(List<PracticeItems> practiceItems) {this.practiceItems = practiceItems;}

    public Integer getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(Integer isAnswer) {
        this.isAnswer = isAnswer;
    }

    public String getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paperId=").append(paperId);
        sb.append(", dirId=").append(dirId);
        sb.append(", paperName=").append(paperName);
        sb.append(", paperType=").append(paperType);
        sb.append(", subject=").append(subject);
        sb.append(", examScope=").append(examScope);
        sb.append(", examDate=").append(examDate);
        sb.append(", answerTime=").append(answerTime);
        sb.append(", setPeople=").append(setPeople);
        sb.append(", downloadNum=").append(downloadNum);
        sb.append(", fileUrl=").append(fileUrl);
        sb.append(", remark=").append(remark);
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