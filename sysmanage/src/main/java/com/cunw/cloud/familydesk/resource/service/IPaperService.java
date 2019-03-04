package com.cunw.cloud.familydesk.resource.service;

import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.resource.model.question.QueryQuestion;
import com.cunw.cloud.familydesk.common.model.Paper;

import java.util.List;

/**
 * Paper服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-02-26 03:38
 * 类描述
 * 修订历史：
 * 日期：2018-02-26
 * 作者：generator
 * 参考：
 * 描述：Paper服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IPaperService extends IBaseService<Paper, String> {

    /**
     * 添加试卷
     * @param paper
     */
    RESTfulResult addPaper(Paper paper);

    /**
     * 添加随堂练习
     * @param paper
     */
    void addPractices(Paper paper);

    /**
     * 获取试卷详情
     * @param paperId
     * @return
     */
    Paper getPaperDetail(String paperId);

    /**
     * 获取练习题详情
     * @param paperId
     * @return
     */
    Paper getPracticePaperDetail(String paperId);

    /**
     * 获取试卷难度统计
     * @param paperId
     */
    List<QueryQuestion> getDifficultStatistic(String paperId);

}