package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.StudentWrongQuestion;
import com.cunw.cloud.familydesk.common.model.WrongQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentWrongQuestionMapper extends IBaseMapper<StudentWrongQuestion, String> {

    List<WrongQuestion> getSubjectWrongQuestions(Map<String, Object> map);

    List<WrongQuestion> getSubjectBookWrongQuestions(@Param("studentCode") String studentCode, @Param("subjectId") String subjectId);

    List<StudentWrongQuestion> getWrongQuestionIds(Map<String, Object> map);

    List<StudentWrongQuestion> getWrongQuestions(Map<String, Object> map);

    List<WrongQuestion> getknowledgeWrongQuestionCount(Map<String, Object> map);

    List<WrongQuestion> selectknowledgeWrongQuestionCountBySubject(Map<String, Object> map);
}