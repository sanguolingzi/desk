package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.familydesk.common.model.StudyExamTestAnswer;
import com.cunw.cloud.familydesk.common.model.TestAnswer;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.resource.model.QuestionDifficult;


import java.util.List;
import java.util.Map;

/**
 * StudyExamTestAnswer服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-15 03:11
 * 类描述
 * 修订历史：
 * 日期：2018-08-15
 * 作者：generator
 * 参考：
 * 描述：StudyExamTestAnswer服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IStudyExamTestAnswerService extends IBaseService<StudyExamTestAnswer, String> {

    List<TestAnswer> getMonthTestAnswerCount(Map<String, Object> params);

    List<QuestionDifficult> getQuestionDifficultCount(Map<String, Object> params);

	List<StudyExamTestAnswer> getQuesList(List<String> quesIdList);

}