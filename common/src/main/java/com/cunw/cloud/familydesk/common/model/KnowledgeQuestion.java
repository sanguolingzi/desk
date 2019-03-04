package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

public class KnowledgeQuestion implements IBasePO<String> {
    /**
     * 知识点题目id
     */
    private String id;

    /**
     * 掌握情况ID
     */
    private String masterId;

    /**
     * 题目id
     */
    private String questId;

    /**
     * 题目顺序
     */
    private Integer questOrder;

    /**
     * 1.基础 2.巩固 3.拔高
     */
    private Integer rank;

    private static final long serialVersionUID = 1L;

    public KnowledgeQuestion() {

    }

    public KnowledgeQuestion(String id, String masterId, String questId, Integer questOrder, Integer rank) {
        this.id = id;
        this.masterId = masterId;
        this.questId = questId;
        this.questOrder = questOrder;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId == null ? null : masterId.trim();
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId == null ? null : questId.trim();
    }

    public Integer getQuestOrder() {
        return questOrder;
    }

    public void setQuestOrder(Integer questOrder) {
        this.questOrder = questOrder;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", masterId=").append(masterId);
        sb.append(", questId=").append(questId);
        sb.append(", questOrder=").append(questOrder);
        sb.append(", rank=").append(rank);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}