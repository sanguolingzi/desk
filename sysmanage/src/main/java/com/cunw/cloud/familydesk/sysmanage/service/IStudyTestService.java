package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.dto.StudyTestDTO;
import com.cunw.cloud.familydesk.common.dto.TestQuestion;
import com.cunw.cloud.familydesk.common.model.KnowledgeMasterCount;
import com.cunw.cloud.familydesk.common.model.QuestionRankCount;
import com.cunw.cloud.familydesk.common.model.StudyTest;

import java.util.List;
import java.util.Map;

/**
 * StudyTest服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：StudyTest服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IStudyTestService extends IBaseService<StudyTest, String> {

    /**
     * 取测验题目
     * @param studentCode
     * @param type
     * @param subjectId
     * @param id
     * @return
     */
    List<TestQuestion> getTestQuestion(String studentCode, Integer type, String subjectId, String id);

    /**
     *
     * @param type
     * @param studentCode
     * @param id
     * @param rank
     * @param useTime
     * @param answers
     */
    StudyTestDTO addTest(String studentCode, Integer type, String id, Integer rank, Integer useTime, List<Map> answers);

    /**
     * 取测验详情
     * @param studentCode
     * @param type
     * @param id
     * @param rank
     * @return
     */
    StudyTest getTest(String studentCode, Integer type, String id, Integer rank);

    /**
     * 获取测验答题详情
     * @param testId
     * @return
     */
    StudyTest getTestDetail(String testId);

    Integer getTestStar(String studentCode);

    /**
     * 答题难易度统计
     * @param params
     * @return
     */
    List<QuestionRankCount> getQuestionRankCount(Map<String, Object> params);

    /**
     * 知识点掌握程度统计
     * @param params
     * @return
     */
    List<KnowledgeMasterCount> getKnowledgeMasterCount(Map<String, Object> params);
}