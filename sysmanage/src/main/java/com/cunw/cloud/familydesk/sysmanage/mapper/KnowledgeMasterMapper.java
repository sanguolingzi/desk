package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.resource.model.question.QueryAccuracyRanking;
import com.cunw.cloud.resource.model.question.QueryKnowledgeRanking;
import com.cunw.cloud.familydesk.common.model.KnowledgeMaster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface KnowledgeMasterMapper extends IBaseMapper<KnowledgeMaster, String> {

    List<KnowledgeMaster> selectByDirIds(@Param("studentCode") String studentCode, @Param("dirIds") List<Integer> dirIds);


    List<QueryKnowledgeRanking> selectByCorrectRate(@Param("studentCode") String studentCode, @Param("knowledgeId") String knowledgeId);


    List<QueryKnowledgeRanking> selectByCorrectRateMax(@Param("studentCode") String studentCode, @Param("knowledgeId") String knowledgeId);

    Integer selectByRank(@Param("studentCode") String studentCode, @Param("knowledgeId") String knowledgeId);

    Integer selectByRankCount(@Param("rank") Integer rank);

    List<KnowledgeMaster> selectByStudetArea(@Param("studentCode") String studentCode);

    List<QueryKnowledgeRanking> selectByStudentRanking(Map<String, Object> params);

    Integer selectByStudentRank(Map<String, Object> params);

    List<QueryAccuracyRanking> selectByStudentRankStar(Map<String, Object> params);

    void updateRank(KnowledgeMaster knowledgeMaster);

}