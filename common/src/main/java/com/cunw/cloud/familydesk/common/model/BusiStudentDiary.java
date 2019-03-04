package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class BusiStudentDiary implements IBasePO<String> {
    private String diaryId;

    private String diaryStudentId;

    private String diaryTitle;

    private String diaryMood;

    /**
     * 1 未删除 0 已删除
     */
    private Integer status;

    private Date crtDate;

    private String diaryText;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.diaryId;
    }

    @Override
    public void setId(String diaryId) {
        this.diaryId = diaryId == null ? null : diaryId.trim();
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId == null ? null : diaryId.trim();
    }

    public String getDiaryStudentId() {
        return diaryStudentId;
    }

    public void setDiaryStudentId(String diaryStudentId) {
        this.diaryStudentId = diaryStudentId == null ? null : diaryStudentId.trim();
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle == null ? null : diaryTitle.trim();
    }

    public String getDiaryMood() {
        return diaryMood;
    }

    public void setDiaryMood(String diaryMood) {
        this.diaryMood = diaryMood == null ? null : diaryMood.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getDiaryText() {
        return diaryText;
    }

    public void setDiaryText(String diaryText) {
        this.diaryText = diaryText == null ? null : diaryText.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", diaryId=").append(diaryId);
        sb.append(", diaryStudentId=").append(diaryStudentId);
        sb.append(", diaryTitle=").append(diaryTitle);
        sb.append(", diaryMood=").append(diaryMood);
        sb.append(", status=").append(status);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", diaryText=").append(diaryText);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}