package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.resource.dto.AnswerDTO;
import com.cunw.cloud.familydesk.common.dto.AnswerParam;
import com.cunw.cloud.familydesk.common.dto.StudySmartAssemblyStatisticDTO;
import com.cunw.cloud.familydesk.common.model.StudySmartAssemblyQuestion;
import com.cunw.cloud.familydesk.sysmanage.mapper.StudySmartAssemblyQuestionMapper;
import com.cunw.cloud.familydesk.sysmanage.service.IStudySmartAssemblyQuestionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * StudySmartAssemblyQuestion服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-17 02:10
 * 类描述
 * 修订历史：
 * 日期：2018-08-17
 * 作者：generator
 * 参考：
 * 描述：StudySmartAssemblyQuestion服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "studySmartAssemblyQuestionService")
public class StudySmartAssemblyQuestionServiceImpl extends BaseServiceImpl<StudySmartAssemblyQuestion, String> implements IStudySmartAssemblyQuestionService {

    @Autowired
    private StudySmartAssemblyQuestionMapper studySmartAssemblyQuestionMapper;

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    @Override
    protected IBaseMapper<StudySmartAssemblyQuestion, String> getMapper() {
        return studySmartAssemblyQuestionMapper;
    }

    @Override
    public List<StudySmartAssemblyQuestion> selectByQuestId(List<String> questIdList, String assemblyId) {
        List<StudySmartAssemblyQuestion> studySmartAssemblyQuestionList = studySmartAssemblyQuestionMapper.selectByQuestId(questIdList);
        return studySmartAssemblyQuestionList.stream().filter(x -> x.getAssemblyId().equals(assemblyId)).collect(Collectors.toList());
    }


    public StudySmartAssemblyStatisticDTO saveStudySmartAssemblyQuestion(String assemblyId, List<AnswerParam> answerList){
        StudySmartAssemblyStatisticDTO studySmartAssemblyStatisticDTO = new StudySmartAssemblyStatisticDTO();
        //调用资源接口获取答案是否正确
        List<String> questionIdList = answerList.stream().map(x -> {return x.getQuestionId();}).collect(Collectors.toList());
        List<AnswerDTO> answerDTOList = getAnswerList(questionIdList);
        int index = 1;
        List<StudySmartAssemblyQuestion> studySmartAssemblyQuestionList = new ArrayList<>();
        for (AnswerParam answerParam : answerList) {
            StudySmartAssemblyQuestion studySmartAssemblyQuestion = new StudySmartAssemblyQuestion(genID(), assemblyId, answerParam.getQuestionId(), answerParam.getAnswer(),index);
            checkQuestionAnswer(studySmartAssemblyQuestion, answerDTOList);
            studySmartAssemblyQuestionList.add(studySmartAssemblyQuestion);
            index++;
        }
        if(CollectionUtils.isNotEmpty(studySmartAssemblyQuestionList)){
            batchAdd(studySmartAssemblyQuestionList);
            studySmartAssemblyStatisticDTO.setAssemblyId(assemblyId);
            long rightCount = studySmartAssemblyQuestionList.stream().filter(x -> x.getIsRight() == 1).count();
            long wrongCount = studySmartAssemblyQuestionList.stream().filter(x -> x.getIsRight() == 0).count();
            studySmartAssemblyStatisticDTO.setRightAnswerCount(rightCount);
            studySmartAssemblyStatisticDTO.setScore(rightCount);
            studySmartAssemblyStatisticDTO.setWrongAnswerCount(wrongCount);
        }
        return studySmartAssemblyStatisticDTO;
    }

    void checkQuestionAnswer(StudySmartAssemblyQuestion studySmartAssemblyQuestion, List<AnswerDTO> answerDTOList){
        //验证是否是正确答案
        for(AnswerDTO rightAnswer : answerDTOList){
            if(rightAnswer.getQuestionId().equals(studySmartAssemblyQuestion.getQuestId())){
                if(StringUtils.isEmpty(studySmartAssemblyQuestion.getAnswer())){
                    studySmartAssemblyQuestion.setIsRight(0);  //错误
                }else if(rightAnswer.getAnswer().equals(studySmartAssemblyQuestion.getAnswer())){
                    studySmartAssemblyQuestion.setIsRight(1);  //正确
                }else{
                    studySmartAssemblyQuestion.setIsRight(0);  //错误
                }
                studySmartAssemblyQuestion.setRightAnswer(rightAnswer.getAnswer());//正确答案
                break;
            }
        }
    }

    public List<AnswerDTO> getAnswerList(List<String> questionIdList){
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        if(CollectionUtils.isEmpty(questionIdList)){
            return answerDTOList;
        }
        Map<String,String> param =new HashMap<>();
        param.put("qustionStr", JsonMapper.nonEmptyMapper().toJson(questionIdList));
        answerDTOList = ServerRestTemplate.getList(resourceServerUrl + "/resource/questiondetails/questionanswers", AnswerDTO.class, param);
        return answerDTOList;
    }
}
