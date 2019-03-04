package com.cunw.cloud.familydesk.common.model;

/**
 * @ClassName QuestionRankCount
 * @DESCRIPTION TODO
 * @Author yy.zhou
 * @Date 2018/8/22
 * @Version 1.0
 **/
public class QuestionRankCount {

    private Integer rank;

    private String rankName;

    private Integer sumStar;

    private Integer maxStar;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Integer getSumStar() {
        return sumStar;
    }

    public void setSumStar(Integer sumStar) {
        this.sumStar = sumStar;
    }

    public Integer getMaxStar() {
        return maxStar;
    }

    public void setMaxStar(Integer maxStar) {
        this.maxStar = maxStar;
    }

    @Override
    public String toString() {
        return "QuestionRankCount{" +
                "rank=" + rank +
                ", rankName='" + rankName + '\'' +
                ", sumStar=" + sumStar +
                ", maxStar=" + maxStar +
                '}';
    }
}