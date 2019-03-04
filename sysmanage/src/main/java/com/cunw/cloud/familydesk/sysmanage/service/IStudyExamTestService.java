package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.resource.model.RealPaper;
import com.cunw.cloud.resource.model.question.QueryQuestion;
import com.cunw.cloud.familydesk.common.dto.StudyExamTestParam;
import com.cunw.cloud.familydesk.common.dto.StudyExamTestStatisticDTO;
import com.cunw.cloud.familydesk.common.dto.SubjectDTO;
import com.cunw.cloud.familydesk.common.model.StudyExamTest;
import com.cunw.cloud.familydesk.common.model.StudyExamTestAnswer;

import java.util.List;

/**
 * StudyExamTest服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-15 03:10
 * 类描述
 * 修订历史：
 * 日期：2018-08-15
 * 作者：generator
 * 参考：
 * 描述：StudyExamTest服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IStudyExamTestService extends IBaseService<StudyExamTest, String>{

    List<QueryQuestion> getAnswerCount(String paperId);

    // List<KnowledgeParamDTO> getKnowledgeAnalysis(String paperId);

    /**
     * 已答试卷科目统计
     * @param stage
     * @param studentCode
     * @return
     */
    List<SubjectDTO> getPaperStatistics(String stage, String studentCode);


    /**
     * 已答试卷册别统计
     * @param subjectId
     * @param studentCode
     * @return
     */
//    List<BookDTO> getPaperBookStatistics(String subjectId, String studentCode);


    /**
     * 获取考前冲刺试卷列表
     * @param bookId
     * @param studentCode
     * @return
     */
    PageList<StudyExamTest> getPaperList(String bookId, String studentCode, Integer pageNum, Integer pageSize);

    /**
     * 保存答题结果
     * @param studyExamTestParam
     */
    StudyExamTestStatisticDTO saveStudyExamTest(StudyExamTestParam studyExamTestParam);

    /**
     * 获取试卷答题明细
     * @param testId
     * @return
     */
    List<StudyExamTestAnswer> getPaperDetailList(String testId);

    /**
     * 获取未下载真题试卷列表
     * @param subjectId
     * @return
     */
    PageList<RealPaper> getRealPaperList(String subjectId, Integer pageNum, Integer pageSize);

    PageList<RealPaper> getMyPaper(String subjectId, Integer pageNum, Integer pageSize);


    /**
     * 获取下载地址路径
     * @param paperId
     * @return
     */
    RESTfulResult getDownloadPaperUrl(String paperId);

    /**
     * 获取真题试卷详情包括子题目
     * @param paperId
     * @return
     */
    List<com.cunw.cloud.resource.model.Question> getPaperDetailInfo(String paperId);}