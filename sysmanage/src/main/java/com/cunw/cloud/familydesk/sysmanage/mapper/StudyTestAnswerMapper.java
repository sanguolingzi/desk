package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.StudyTestAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyTestAnswerMapper extends IBaseMapper<StudyTestAnswer, String> {

    int batchInsertAsMasterId(@Param("type") Integer type, @Param("masterId") String masterId, @Param("rank") Integer rank, @Param("list") List<StudyTestAnswer> testAnswerList);

    /**
     * 根据学生code和知识点id查询测验过的题目
     * @param paramMap
     * @return
     */
    List<StudyTestAnswer> selectByKnowledgeAndStudentCode(Map<String, Object> paramMap);

}