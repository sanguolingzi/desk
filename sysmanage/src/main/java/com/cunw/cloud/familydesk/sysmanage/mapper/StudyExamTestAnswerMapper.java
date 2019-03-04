package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.StudyExamTestAnswer;
import com.cunw.cloud.familydesk.common.model.TestAnswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyExamTestAnswerMapper extends IBaseMapper<StudyExamTestAnswer, String> {


    List<StudyExamTestAnswer> selectQuest(List<String> quesIdList);

    List<TestAnswer> getMonthTestAnswerCount(Map<String, Object> params);

    List<StudyExamTestAnswer> getTestAnswerQuestionIds(Map<String, Object> params);
}