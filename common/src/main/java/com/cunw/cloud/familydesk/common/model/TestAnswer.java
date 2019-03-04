package com.cunw.cloud.familydesk.common.model;

/**
 * @ClassName TestAnswer
 * @DESCRIPTION TODO
 * @Author yy.zhou
 * @Date 2018/8/18
 * @Version 1.0
 **/
public class TestAnswer {

    private String isRight;

    private Integer questionCount;

    private String answerDate;

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }

    @Override
    public String toString() {
        return "TestAnswer{" +
                "isRight=" + isRight +
                ", questionCount=" + questionCount +
                ", answerDate=" + answerDate +
                '}';
    }
}