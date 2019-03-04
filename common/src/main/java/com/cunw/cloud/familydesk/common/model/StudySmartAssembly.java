package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import com.cunw.cloud.familydesk.common.dto.AnswerParam;

import java.util.Date;
import java.util.List;

public class StudySmartAssembly implements IBasePO<String> {
    /**
     * 组题ID
     */
    private String assemblyId;

    /**
     * 试卷id
     */
    private String paperId;

    /**
     * 学生编码
     */
    private String studentCode;

    /**
     * 试卷标题
     */
    private String title;

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
    private String chapters;

    /**
     * 关联知识点
     */
    private String knowledges;

    /**
     * 组题难度值 说明：[0 = "不限" 1 = '容易', 2 = '较易', 3 = '普通', 4 = '较难', 5 = '困难']
     */
    private Integer difficult;

    /**
     * 0 关联组题 1 精准组题
     */
    private Integer type;

    /**
     * 考试分数
     */
    private Integer score;

    /**
     * 题目数量
     */
    private Integer num;

    /**
     * 答题时间
     */
    private Integer useTime;

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

    /**
     * 状态
     */
    private Integer status;

    /**
     * 答案
     */
    private List<AnswerParam> answerList;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.assemblyId;
    }

    @Override
    public void setId(String assemblyId) {
        this.assemblyId = assemblyId == null ? null : assemblyId.trim();
    }

    public String getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId == null ? null : assemblyId.trim();
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public String getChapters() {
        return chapters;
    }

    public void setChapters(String chapters) {
        this.chapters = chapters == null ? null : chapters.trim();
    }

    public String getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(String knowledges) {
        this.knowledges = knowledges == null ? null : knowledges.trim();
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<AnswerParam> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerParam> answerList) {
        this.answerList = answerList;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
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
        sb.append(", assemblyId=").append(assemblyId);
        sb.append(", studentCode=").append(studentCode);
        sb.append(", title=").append(title);
        sb.append(", stage=").append(stage);
        sb.append(", subjectId=").append(subjectId);
        sb.append(", versionId=").append(versionId);
        sb.append(", bookid=").append(bookid);
        sb.append(", chapters=").append(chapters);
        sb.append(", knowledges=").append(knowledges);
        sb.append(", difficult=").append(difficult);
        sb.append(", type=").append(type);
        sb.append(", num=").append(num);
        sb.append(", crtUserCode=").append(crtUserCode);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", updDate=").append(updDate);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
//    String studySmartAssemblyId = genID();
//            model.setId(studySmartAssemblyId);
//            model.setCrtDate(new Date());
//    DirDTO dirDTO = getDirDTO(paper.getDirId());   //获取dir的上级目录
//            model.setStage(dirDTO.getStage());
//            model.setSubjectId(dirDTO.getSubjectId());
//            model.setVersionId(dirDTO.getVersionId());
//            model.setBookid(dirDTO.getBookId());
//            model.setNum(model.getAnswerList() == null ? 0 : model.getAnswerList().size());
    public void parse(String studySmartAssemblyId, String stage, String subjectId, String versionId, String bookId, Integer num){
        this.assemblyId = studySmartAssemblyId;
        this.stage = stage;
        this.subjectId = subjectId;
        this.versionId = versionId;
        this.bookid = bookId;
        this.num = num;
        this.crtDate = new Date();
    }
}