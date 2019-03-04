package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.resource.model.question.QueryAccuracyRanking;
import com.cunw.cloud.resource.model.question.QueryKnowledgeRanking;
import com.cunw.cloud.familydesk.common.model.KnowledgeMaster;

import java.util.List;
import java.util.Map;

/**
 * KnowledgeMaster服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：KnowledgeMaster服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IKnowledgeMasterService extends IBaseService<KnowledgeMaster, String> {

    List queryKnowledges(String studentCode, String subjectId, String upid);

    KnowledgeMaster getAndInit(String studentCode, String subjectId, String chapterId);

    List<QueryKnowledgeRanking> getByCorrectRate(String studentCode, String chapterId);

    List<QueryKnowledgeRanking> getByCorrectRateMax(String studentCode, String chapterId);

    Integer selectByRank(String studentCode, String knowledgeId);

    Integer selectByRankCount(Integer rank);

    List<KnowledgeMaster> selectByStudetArea(String studentCode);

    List<QueryKnowledgeRanking> selectByStudentRanking(Map<String, Object> params);

    Integer selectByStudentRank(Map<String, Object> params);

    List<QueryAccuracyRanking> selectByStudentRankStar(Map<String, Object> params);

    void updateRank(KnowledgeMaster knowledgeMaster);
}