package com.cunw.cloud.familydesk.common.model;

/**
 * @ClassName KnowledgeMasterCount
 * @DESCRIPTION TODO
 * @Author yy.zhou
 * @Date 2018/8/21
 * @Version 1.0
 **/
public class KnowledgeMasterCount {

    /**
     * 知识点id
     */
    private String knowledgeId;

    private String knowledgeName;

    /**
     * 父级知识点id
     */
    private String pKnowledgeId;

    /**
     * 知识点掌握程度百分值
     */
    private float pointValue;

    /**
     * 平均值
     */
    private float averageValue;


    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getpKnowledgeId() {
        return pKnowledgeId;
    }

    public void setpKnowledgeId(String pKnowledgeId) {
        this.pKnowledgeId = pKnowledgeId;
    }

    public float getPointValue() {
        return pointValue;
    }

    public void setPointValue(float pointValue) {
        this.pointValue = pointValue;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public float getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(float averageValue) {
        this.averageValue = averageValue;
    }

    @Override
    public String toString() {
        return "KnowledgeMasterCount{" +
                "knowledgeId='" + knowledgeId + '\'' +
                ", knowledgeName='" + knowledgeName + '\'' +
                ", pKnowledgeId='" + pKnowledgeId + '\'' +
                ", pointValue=" + pointValue +
                ", averageValue=" + averageValue +
                '}';
    }
}