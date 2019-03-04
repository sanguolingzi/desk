package com.cunw.cloud.familydesk.common.model;

/**
 * @ClassName WrongQuestion
 * @DESCRIPTION TODO
 * @Author yy.zhou
 * @Date 2018/8/14
 * @Version 1.0
 **/
public class WrongQuestion {

    private String subjectId;

    private String subjectName;

    private String bookId;

    private String bookName;

    private String knowledgeId;

    private String knowledgeName;

    private Integer questionCount;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
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

    @Override
    public String toString() {
        return "WrongQuestion{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", knowledgeId='" + knowledgeId + '\'' +
                ", knowledgeName='" + knowledgeName + '\'' +
                ", questionCount=" + questionCount +
                '}';
    }
}