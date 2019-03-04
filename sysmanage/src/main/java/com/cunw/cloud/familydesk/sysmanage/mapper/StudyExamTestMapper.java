package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.StudyExamTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyExamTestMapper extends IBaseMapper<StudyExamTest, String> {

    List<StudyExamTest> selectByParametersScore(Map<String, ?> params);

    Integer selectAnswerCount(String paperId);
}