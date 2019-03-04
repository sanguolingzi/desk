package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.resource.dto.SmartPaperQuestionParamDTO;
import com.cunw.cloud.resource.dto.XinHuaQuestionDTO;
import com.cunw.cloud.resource.model.XinHuaQuestionType;
import com.cunw.cloud.resource.model.question.QueryKnowledge;
import com.cunw.cloud.resource.model.question.QuestionType;
import com.cunw.cloud.familydesk.common.dto.QuestionAnswerDTO;
import com.cunw.cloud.familydesk.common.dto.StudySmartAssemblyStatisticDTO;
import com.cunw.cloud.familydesk.common.model.Paper;
import com.cunw.cloud.familydesk.common.model.StudySmartAssembly;

import java.util.List;

/**
 * StudySmartAssembly服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-17 02:10
 * 类描述
 * 修订历史：
 * 日期：2018-08-17
 * 作者：generator
 * 参考：
 * 描述：StudySmartAssembly服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IStudySmartAssemblyService extends IBaseService<StudySmartAssembly, String> {

    /**
     * 保存答题
     * @param model
     */
    StudySmartAssemblyStatisticDTO saveStudySmartAssemblyAnswer(StudySmartAssembly model);

    List<QuestionAnswerDTO> getPaperDetailList(String testId);

    PageList<Paper> getAnsweredPaperIdList(Integer pageNum, Integer pageSize, String dirId, String paperType, String studentCode, String user);

    /**
     *未答试卷统计
     * @param paperId
     * @return
     */
    List<QueryKnowledge> getNoAnswerPaperStatistics(String paperId);

    /**
     *已答试卷统计
     * @param paperId
     * @return
     */
    List<QueryKnowledge> getAnsweredPaperStatistics(String paperId, String assemblyId);

    List<QuestionType> getQuestionCountByType(String dirIds, String type, Integer difficult, String questionTypes);

    /**
     * 获取智能组卷题目
     * @param
     * @return
     */
    List<XinHuaQuestionDTO> getSmartPaperQuestion(SmartPaperQuestionParamDTO smartPaperQuestionParamDTO);

    List<XinHuaQuestionType> selectByQuestType(Integer subjectId);
}