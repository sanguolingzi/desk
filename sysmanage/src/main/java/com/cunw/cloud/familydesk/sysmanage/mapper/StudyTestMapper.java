package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.KnowledgeMasterCount;
import com.cunw.cloud.familydesk.common.model.QuestionRankCount;
import com.cunw.cloud.familydesk.common.model.StudyTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyTestMapper extends IBaseMapper<StudyTest, String> {

    StudyTest selectByMaterialMaster(@Param("studentCode") String studentCode, @Param("star") Integer star, @Param("id") String id);

    StudyTest selectByKnowledgeMaster(@Param("studentCode") String studentCode, @Param("star") Integer star, @Param("id") String id);

    StudyTest selectByStudentCodeAndTypeAndIdAndRank(@Param("studentCode") String studentCode, @Param("type") Integer type, @Param("id") String id, @Param("rank") Integer rank);

    List<QuestionRankCount> getQuestionRankCount(Map<String, Object> params);

    List<KnowledgeMasterCount> getKnowledgeMasterCount(Map<String, Object> params);

    Integer getTestStar(String studentCode);

}