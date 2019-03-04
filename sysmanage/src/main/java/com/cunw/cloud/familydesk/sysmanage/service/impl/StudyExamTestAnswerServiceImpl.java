package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.common.model.StudyExamTestAnswer;
import com.cunw.cloud.familydesk.common.model.TestAnswer;
import com.cunw.cloud.familydesk.sysmanage.mapper.StudyExamTestAnswerMapper;
import com.cunw.cloud.familydesk.sysmanage.service.IStudyExamTestAnswerService;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.resource.model.QuestionDifficult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * StudyExamTestAnswer服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-15 03:11
 * 类描述
 * 修订历史：
 * 日期：2018-08-15
 * 作者：generator
 * 参考：
 * 描述：StudyExamTestAnswer服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@Service(value = "studyExamTestAnswerService")
public class StudyExamTestAnswerServiceImpl extends BaseServiceImpl<StudyExamTestAnswer, String> implements IStudyExamTestAnswerService {

    @Autowired
    private StudyExamTestAnswerMapper studyExamTestAnswerMapper;

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    @Override
    protected IBaseMapper<StudyExamTestAnswer, String> getMapper() {
        return studyExamTestAnswerMapper;
    }

    @Override
    public List<TestAnswer> getMonthTestAnswerCount(Map<String, Object> params) {

        return studyExamTestAnswerMapper.getMonthTestAnswerCount(params);
    }

    @Override
    public List<QuestionDifficult> getQuestionDifficultCount(Map<String, Object> params) {
        log.info("questionDifficultCount method call serviceImpl getTestAnswerQuestionIds param: ({})", JsonMapper.nonEmptyMapper().toJson(params));
        List<StudyExamTestAnswer> studyExamTestAnswers = studyExamTestAnswerMapper.getTestAnswerQuestionIds(params);
        log.info("questionDifficultCount method call serviceImpl getTestAnswerQuestionIds result: ({})", JsonMapper.nonEmptyMapper().toJson(studyExamTestAnswers));

        List<String> questionIds = null;
        if(CollectionUtils.isNotEmpty(studyExamTestAnswers)){
            questionIds = studyExamTestAnswers.stream().map(q -> q.getQuestId()).collect(Collectors.toList());
        }
        StringBuffer sbIds = new StringBuffer();
        if(CollectionUtils.isNotEmpty(questionIds)){
            for(int i = 0; i < questionIds.size(); i++){
                if((i+1) == questionIds.size()){
                    sbIds.append(questionIds.get(i));
                }else{
                    sbIds.append(questionIds.get(i)+",");
                }
            }
        }
        Map<String, Object> resourceParams = new HashMap<>(1);
        resourceParams.put("ids", sbIds.toString());

        log.info("questionDifficultCount > getQuestionDifficultCount call resource plat: questionDifficults param:{}", JsonMapper.nonEmptyMapper().toJson(resourceParams));
        List<QuestionDifficult> questionDifficults = null;
                //ServerRestTemplate.postList(resourceServerUrl + "/resource/questions/difficults/count", QuestionDifficult.class, resourceParams);
        log.info("questionDifficultCount > getQuestionDifficultCount call resource plat: questionDifficults result: {}", JsonMapper.nonEmptyMapper().toJson(questionDifficults));

        //题目难度五大类分为三大类
        Map<String, Integer> questionDifficultMap = new HashMap<String, Integer>();
        questionDifficults.forEach(qd -> {
            if (qd.getDifficult() == 1 || qd.getDifficult() == 2){
                if(questionDifficultMap.get("basics") == null){
                    questionDifficultMap.put("basics", qd.getDifficultCount());
                }else{
                    questionDifficultMap.put("basics", questionDifficultMap.get("basics")+qd.getDifficultCount());
                }
            }else if(qd.getDifficult() == 3 || qd.getDifficult() == 4){
                if(questionDifficultMap.get("consolidation") == null){
                    questionDifficultMap.put("consolidation", qd.getDifficultCount());
                }else{
                    questionDifficultMap.put("consolidation", questionDifficultMap.get("basics")+qd.getDifficultCount());
                }
            }else{
                if(questionDifficultMap.get("high") == null){
                    questionDifficultMap.put("high", qd.getDifficultCount());
                }else{
                    questionDifficultMap.put("high", questionDifficultMap.get("basics")+qd.getDifficultCount());
                }
            }
        });

        List<QuestionDifficult> threeDifficults = new ArrayList<QuestionDifficult>();
        for (String key : questionDifficultMap.keySet()) {
            QuestionDifficult qd = new QuestionDifficult();
            qd.setDifficultName(key);
            qd.setDifficultCount(questionDifficultMap.get(key));
            Integer maxValue = numberTransformMaxValue(questionDifficultMap.get(key));
            qd.setMaxValue(maxValue);
            threeDifficults.add(qd);
        }
        return threeDifficults;
    }

    public Integer numberTransformMaxValue(Integer number) {

        Integer outNumber = 0;

        String countStr = String.valueOf(number);

        Integer countLength = countStr.length();

        if(countLength == 1) {
            outNumber = 10;
        }else if(countLength == 2) {
            outNumber = (number - (number%10)) + 10;
        }else if(countLength == 3) {
            outNumber = (number - (number%100)) + 100;
        }else if(countLength == 4) {
            outNumber = (number - (number%1000)) + 1000;
        }else if(countLength == 5) {
            outNumber = (number - (number%10000)) + 10000;
        }else if(countLength == 6) {
            outNumber = (number - (number%100000)) + 100000;
        }
        return outNumber;
    }

    @Override
    public List<StudyExamTestAnswer> getQuesList(List<String> quesIdList) {
        return studyExamTestAnswerMapper.selectQuest(quesIdList);
    }
}
