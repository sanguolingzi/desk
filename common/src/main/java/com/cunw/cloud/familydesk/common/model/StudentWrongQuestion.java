package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import com.cunw.cloud.framework.model.ICreatePO;
import com.cunw.cloud.framework.model.IUpdatePO;
import com.cunw.cloud.resource.model.QuestionAnswer;

import java.util.Date;

public class StudentWrongQuestion implements IBasePO<String>, IUpdatePO, ICreatePO {
    private String wrongQuestionId;

    private Integer stage;

    private String studentCode;

    private String questionId;

    private Integer questionCount;

    /**
     * 错题来源(1.安卓 2.ios 3.课桌端)
     */
    private String source;

    /**
     * 标签(1.不理解 2.一般理解 3.非常理解)
     */
    private String label;

    /**
     * 我的答题
     */
    private String myAnswer;

    /**
     * dirId
     */
    private String subjectId;

    private String subjectName;

    private String bookId;

    private String bookName;

    private String knowledgeId;

    private String knowledgeName;

    /**
     * 0未移除, 1已移除
     */
    private String status;

    private String crtUserCode;

    private String crtOrgCode;

    private Date crtDate;

    private String updUserCode;

    private String updOrgCode;

    private Date updDate;

    private QuestionAnswer questionAnswer;

    private static final long serialVersionUID = 1L;

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    @Override
    public String getId() {
        return this.wrongQuestionId;
    }

    @Override
    public void setId(String wrongQuestionId) {
        this.wrongQuestionId = wrongQuestionId == null ? null : wrongQuestionId.trim();
    }

    public String getWrongQuestionId() {
        return wrongQuestionId;
    }

    public void setWrongQuestionId(String wrongQuestionId) {
        this.wrongQuestionId = wrongQuestionId == null ? null : wrongQuestionId.trim();
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
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

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

    public QuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(QuestionAnswer questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    @Override
    public String toString() {
        return "StudentWrongQuestion{" +
                "wrongQuestionId='" + wrongQuestionId + '\'' +
                ", stage=" + stage +
                ", studentCode='" + studentCode + '\'' +
                ", questionId='" + questionId + '\'' +
                ", questionCount=" + questionCount +
                ", source='" + source + '\'' +
                ", label='" + label + '\'' +
                ", myAnswer='" + myAnswer + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", knowledgeId='" + knowledgeId + '\'' +
                ", knowledgeName='" + knowledgeName + '\'' +
                ", status='" + status + '\'' +
                ", crtUserCode='" + crtUserCode + '\'' +
                ", crtOrgCode='" + crtOrgCode + '\'' +
                ", crtDate=" + crtDate +
                ", updUserCode='" + updUserCode + '\'' +
                ", updOrgCode='" + updOrgCode + '\'' +
                ", updDate=" + updDate +
                ", questionAnswer=" + questionAnswer +
                '}';
    }
}