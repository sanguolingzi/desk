package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.StudyTestAnswer;

import java.util.List;
import java.util.Map;

/**
 * StudyTestAnswer服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：StudyTestAnswer服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IStudyTestAnswerService extends IBaseService<StudyTestAnswer, String> {

    void batchAddAsMasterId(Integer type, String masterId, Integer rank, List<StudyTestAnswer> testAnswerList);

    /**
     * 根据学生code和知识点id查询测验过的题目
     * @param paramMap
     * @return
     */
    List<StudyTestAnswer> getByKnowledgeAndStudentCode(Map<String, Object> paramMap);

}