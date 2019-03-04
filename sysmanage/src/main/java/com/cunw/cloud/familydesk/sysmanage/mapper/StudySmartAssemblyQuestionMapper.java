package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.StudySmartAssemblyQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudySmartAssemblyQuestionMapper extends IBaseMapper<StudySmartAssemblyQuestion, String> {

    List<StudySmartAssemblyQuestion> selectByQuestId(List<String> questIdList);
}