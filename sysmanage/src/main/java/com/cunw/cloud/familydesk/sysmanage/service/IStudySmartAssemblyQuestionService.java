package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.dto.AnswerParam;
import com.cunw.cloud.familydesk.common.dto.StudySmartAssemblyStatisticDTO;
import com.cunw.cloud.familydesk.common.model.StudySmartAssemblyQuestion;

import java.util.List;

/**
 * StudySmartAssemblyQuestion服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-17 02:10
 * 类描述
 * 修订历史：
 * 日期：2018-08-17
 * 作者：generator
 * 参考：
 * 描述：StudySmartAssemblyQuestion服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface IStudySmartAssemblyQuestionService extends IBaseService<StudySmartAssemblyQuestion, String> {

    List<StudySmartAssemblyQuestion> selectByQuestId(List<String> questIdList, String assemblyId);

    StudySmartAssemblyStatisticDTO saveStudySmartAssemblyQuestion(String assemblyId, List<AnswerParam> answerList);

}