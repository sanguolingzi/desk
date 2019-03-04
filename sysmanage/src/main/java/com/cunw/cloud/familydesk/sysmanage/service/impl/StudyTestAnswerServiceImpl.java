package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.StudyTestAnswer;
import com.cunw.cloud.familydesk.sysmanage.mapper.StudyTestAnswerMapper;
import com.cunw.cloud.familydesk.sysmanage.service.IStudyTestAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * StudyTestAnswer服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：StudyTestAnswer服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "studyTestAnswerService")
public class StudyTestAnswerServiceImpl extends BaseServiceImpl<StudyTestAnswer, String> implements IStudyTestAnswerService {

    @Autowired
    private StudyTestAnswerMapper studyTestAnswerMapper;

    @Override
    protected IBaseMapper<StudyTestAnswer, String> getMapper() {
        return studyTestAnswerMapper;
    }

    @Override
    public void batchAddAsMasterId(Integer type, String masterId, Integer rank, List<StudyTestAnswer> testAnswerList) {
        studyTestAnswerMapper.batchInsertAsMasterId(type, masterId, rank, testAnswerList);
    }

    @Override
    public List<StudyTestAnswer> getByKnowledgeAndStudentCode(Map<String, Object> paramMap) {
        return studyTestAnswerMapper.selectByKnowledgeAndStudentCode(paramMap);
    }
}
