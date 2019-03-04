package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.KnowledgeQuestion;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KnowledgeQuestionMapper extends IBaseMapper<KnowledgeQuestion, String> {
}