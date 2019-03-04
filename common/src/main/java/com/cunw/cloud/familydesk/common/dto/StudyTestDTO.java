package com.cunw.cloud.familydesk.common.dto;

import lombok.Data;

/**
 * 功能/ 模块：
 *
 * @author 贺康 hekang@newcloudnet.com
 * @version 1.0-SNAPSHORT 2018/8/17 8:57
 * 类描述
 * 修订历史：
 * 日期：2018/8/17
 * 作者：贺康
 * 参考：
 * 描述：
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Data
public class StudyTestDTO {

    /**
     * 同步测验ID
     */
    private String test;

    /**
     * 等级
     */
    private Integer rank;


    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }


}
