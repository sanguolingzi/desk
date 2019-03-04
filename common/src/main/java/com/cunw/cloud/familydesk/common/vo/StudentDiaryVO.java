package com.cunw.cloud.familydesk.common.vo;

import java.util.Date;
import java.util.List;

/**
 * 学生日记VO
 */
public class StudentDiaryVO {
    /**
     * 日记id
     */
    private String diaryId;

    /**
     * 学生id
     */
    private String diaryStudentId;

    /**
     * 日记标题
     */
    private String  diaryTitle;

    /**
     * 日记心情
     */
    private String diaryMood;

    /**
     * 日记内容
     */
    private String diaryText;

    /**
     *日记记录时间
     */
    private Date crtDate;

    /**
     * 日记图片路径
     */
    private String fileUrl;

    /**
     * 日记图片集合
     */
    List<String> fileUrlList;

    public StudentDiaryVO(){};

    public StudentDiaryVO(String diaryId, String diaryStudentId, String diaryTitle, String diaryMood, String diaryText, Date crtDate, String fileUrl, List<String> fileUrlList) {
        this.diaryId = diaryId;
        this.diaryStudentId = diaryStudentId;
        this.diaryTitle = diaryTitle;
        this.diaryMood = diaryMood;
        this.diaryText = diaryText;
        this.crtDate = crtDate;
        this.fileUrl = fileUrl;
        this.fileUrlList = fileUrlList;
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public String getDiaryStudentId() {
        return diaryStudentId;
    }

    public void setDiaryStudentId(String diaryStudentId) {
        this.diaryStudentId = diaryStudentId;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    public String getDiaryMood() {
        return diaryMood;
    }

    public void setDiaryMood(String diaryMood) {
        this.diaryMood = diaryMood;
    }

    public String getDiaryText() {
        return diaryText;
    }

    public void setDiaryText(String diaryText) {
        this.diaryText = diaryText;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<String> getFileUrlList() {
        return fileUrlList;
    }

    public void setFileUrlList(List<String> fileUrlList) {
        this.fileUrlList = fileUrlList;
    }

    @Override
    public String toString() {
        return "StudentDiaryVO{" +
                "diaryId='" + diaryId + '\'' +
                ", diaryStudentId='" + diaryStudentId + '\'' +
                ", diaryTitle='" + diaryTitle + '\'' +
                ", diaryMood='" + diaryMood + '\'' +
                ", diaryText='" + diaryText + '\'' +
                ", crtDate=" + crtDate +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileUrlList=" + fileUrlList +
                '}';
    }
}
