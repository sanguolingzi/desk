package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.resource.model.QuestionAnswer;
import com.cunw.cloud.familydesk.common.model.StudentWrongQuestion;
import com.cunw.cloud.familydesk.common.model.WrongQuestion;

import java.util.List;
import java.util.Map;

/**
 * StudentWrongQuestion服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 03:44
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：StudentWrongQuestion服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IStudentWrongQuestionService extends IBaseService<StudentWrongQuestion, String> {

    List<WrongQuestion> getSubjectWrongQuestions(String studentCode, Integer stage);

    List<WrongQuestion> getSubjectBookWrongQuestions(String studentCode, String subjectId);

    PageList<WrongQuestion> getKnowledgeWrongQuestionListBySubject(String studentCode, String subjectId, Integer pageNum, Integer pageSize);

    PageList<WrongQuestion> getKnowledgeWrongQuestionList(String studentCode, String subjectId, String bookId, Integer pageNum, Integer pageSize);

    Map<String, Object> getWrongQuestionDetails(String studentCode, String dirId, String startDate, String endDate);

    List<QuestionAnswer> getKnowledgeWrongQuestionDetails(Map<String, Object> map, String knowledgeId);

    RESTfulResult insertWrongQuestion(StudentWrongQuestion model);

    RESTfulResult updateStudentWrongQuestion(String id, String label);
}