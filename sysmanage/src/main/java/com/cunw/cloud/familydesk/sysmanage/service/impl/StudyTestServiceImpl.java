package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.exception.BusinessException;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.resource.model.KnowledgeName;
import com.cunw.cloud.resource.model.Question;
import com.cunw.cloud.familydesk.common.dto.StudyTestDTO;
import com.cunw.cloud.familydesk.common.dto.TestQuestion;
import com.cunw.cloud.familydesk.common.model.*;
import com.cunw.cloud.familydesk.sysmanage.api.handle.ResourceGatewayHandle;
import com.cunw.cloud.familydesk.sysmanage.mapper.StudyTestMapper;
import com.cunw.cloud.familydesk.sysmanage.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * StudyTest服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：StudyTest服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@Service(value = "studyTestService")
public class StudyTestServiceImpl extends BaseServiceImpl<StudyTest, String> implements IStudyTestService {

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    @Autowired
    private ResourceGatewayHandle resourceGatewayHandle;

    @Autowired
    private StudyTestMapper studyTestMapper;

    @Autowired
    private IStudyTestAnswerService studyTestAnswerService;

    @Autowired
    private IMaterialMasterService materialMasterService;

    @Autowired
    private IMaterialQuestionService materialQuestionService;

    @Autowired
    private IKnowledgeMasterService knowledgeMasterService;

    @Autowired
    private IKnowledgeQuestionService knowledgeQuestionService;

    @Override
    protected IBaseMapper<StudyTest, String> getMapper() {
        return studyTestMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<TestQuestion> getTestQuestion(String studentCode, Integer type, String subjectId, String id) {
        List<Question> questions;
        if (type == 0) { //教材
            questions = getMaterialQuestions(studentCode, subjectId, id);
        } else {
            questions = getKnowledgeQuestions(studentCode, subjectId, id);
        }
        if (CollectionUtils.isEmpty(questions)) {
            return null;
        }
        // 转为更少的对象
        return questions.stream().map(question -> new TestQuestion(question)).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public StudyTestDTO addTest(String studentCode, Integer type, String id, Integer rank, Integer useTime, List<Map> answers) {
        StudyTestDTO studyTestDTO = new StudyTestDTO();
        StudyTest test = new StudyTest();
        test.setTestId(genID());
        test.setStudentCode(studentCode);
        test.setType(type);
        test.setMasterId(getMasterId(type, studentCode, id));
        test.setUseTime(useTime);
        test.setStatus(0);
        test.setRank(rank);
        test.setAnswerTime(new Date());
        log.info("[ test ] > serviceImpl [addTest] (StudyTest)={}", test.toString());
        int testStar = 0;
        int testScore = 0;
        List<StudyTestAnswer> testAnswerList = new ArrayList<>(answers.size());
        // 处理答题结果
        for (Map map : answers) {
            StudyTestAnswer answer = new StudyTestAnswer();
            answer.setQuestId(String.valueOf(map.get("questId")));
            answer.setAnswer(String.valueOf(map.get("answer")));
            answer.setIsRight(Integer.valueOf(map.get("isRight").toString()));
            // 正确加星和加分，题目分值暂时默认为1
            if (answer.getIsRight().equals(1)) {
                testStar ++;
                testScore ++;
            }
            answer.setAnswerTime(test.getAnswerTime());
            answer.setTestId(test.getTestId());
            answer.setAnswerId(genID());
            answer.setScore(1);
            testAnswerList.add(answer);
        }
        log.info("call [ test ] > serviceImpl [addTest] (testAnswerList)={}", JsonMapper.nonEmptyMapper().toJson(testAnswerList));
        // 取当前掌握情况
        int currRank, currStar, currScore, caleRank = 0, caleStar = 0, caleScore = 0;
        MaterialMaster materialMaster = null;
        KnowledgeMaster knowledgeMaster = null;
        if (type == 0) {
            materialMaster = materialMasterService.getAndInit(studentCode, null, id);
            currRank = materialMaster.getRank();
            currStar = materialMaster.getStar();
            currScore = materialMaster.getScore();
            log.info("call [ test ] > serviceImpl [addTest] (materialMaster)={}", materialMaster.toString());
        } else {
            knowledgeMaster = knowledgeMasterService.getAndInit(studentCode, null, id);
            currRank = knowledgeMaster.getRank();
            currStar = knowledgeMaster.getStar();
            currScore = knowledgeMaster.getScore();
            log.info("call [ test ] > serviceImpl [addTest] (knowledgeMaster)={}", knowledgeMaster.toString());
        }
        // 计算章节掌握情况
        // 当前层级测验
        if (currRank == rank) {
            // 本次答题正确数大于等3则升层级
            if (testStar >= 3) {
                caleRank = rank + 1;
                caleStar = testStar;
                caleScore = testScore;
                studyTestDTO.setRank(caleRank);
            } else {
                // 本次答题正确数小于3则只更新最大正确数
                caleStar = currStar >= testStar ? currStar : testStar;
                caleScore = currScore >= testScore ? currScore : testScore;
                caleRank = rank;
                studyTestDTO.setRank(rank);
            }
        }
//        else if (currRank > rank) {
//            // 已过层级测验，只更新最大正确数
//            caleStar = currStar >= testStar ? currStar : testStar;
//            caleScore = currScore >= testScore ? currScore : testScore;
//        }
        else {
            throw new BusinessException("不能超前测验！");
        }
        // 更新
        if (type == 0) {
            materialMaster.setRank(caleRank);
            materialMaster.setStar(caleStar);
            materialMaster.setScore(caleScore);
            materialMasterService.updateRank(materialMaster);
        } else {
            knowledgeMaster.setRank(caleRank);
            knowledgeMaster.setStar(caleStar);
            knowledgeMaster.setScore(caleScore);
            knowledgeMasterService.updateRank(knowledgeMaster);
        }
        // 记录测验
        test.setScore(testScore);
        test.setStar(testStar);
        studyTestMapper.insert(test);
        studyTestAnswerService.batchAddAsMasterId(type, test.getMasterId(), test.getRank(), testAnswerList);

        studyTestDTO.setTest(test.getId());
        return studyTestDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public StudyTest getTest(String studentCode, Integer type, String id, Integer rank) {
        StudyTest test = studyTestMapper.selectByStudentCodeAndTypeAndIdAndRank(studentCode, type, id, rank);
        test.setAnswers(studyTestAnswerService.queryByProperty("testId", test.getId()));
        log.info("[getTest] > serviceImpl [getTest] (test)={}", test.toString());
        String questIds = test.getAnswers().stream().map(a -> a.getQuestId()).collect(Collectors.joining(","));
        List<Question> questions = getResourceQuestionHasAnswer(questIds);
        log.info("[getTest] > serviceImpl [getTest] (questions)={}", JsonMapper.nonEmptyMapper().toJson(questions));
        Map<String, Question> map = questions.stream().collect(Collectors.toMap(Question::getQuestId, Function.identity()));
        for (StudyTestAnswer answer : test.getAnswers()) {
            answer.setQuestion(map.get(answer.getQuestId()));
        }
        log.info("[getTest] > serviceImpl [getTest] return={}", JsonMapper.nonEmptyMapper().toJson(test));
        return test;
    }

    @Override
    public StudyTest getTestDetail(String testId) {
        StudyTest test = getById(testId);
        if(test != null){
            test.setAnswers(studyTestAnswerService.queryByProperty("testId", test.getId()));
            String questIds = test.getAnswers().stream().map(a -> a.getQuestId()).collect(Collectors.joining(","));
            List<Question> questions = getResourceQuestionHasAnswer(questIds);
            Map<String, Question> map = questions.stream().collect(Collectors.toMap(Question::getQuestId, Function.identity()));
            for (StudyTestAnswer answer : test.getAnswers()) {
                answer.setQuestion(map.get(answer.getQuestId()));
            }
        }
        return test;
    }

    @Override
    public Integer getTestStar(String studentCode) {
        return studyTestMapper.getTestStar(studentCode);
    }

    @Override
    public List<QuestionRankCount> getQuestionRankCount(Map<String, Object> params) {
        List<QuestionRankCount> questionRankCounts = studyTestMapper.getQuestionRankCount(params);
        questionRankCounts = questionRankCounts.stream().map(qr ->{
            qr.setMaxStar(numberTransformMaxValue(qr.getSumStar()));
            return  qr;
        }).collect(Collectors.toList());

        return questionRankCounts;
    }

    @Override
    public List<KnowledgeMasterCount> getKnowledgeMasterCount(Map<String, Object> params) {
        log.info("knowledgeMasterCount method call serviceImpl getKnowledgeMasterCount param ({})", JsonMapper.nonEmptyMapper().toJson(params));
        List<KnowledgeMasterCount> knowledgeMasterCounts = studyTestMapper.getKnowledgeMasterCount(params);
        log.info("knowledgeMasterCount method call serviceImpl getKnowledgeMasterCount result: ({})", JsonMapper.nonEmptyMapper().toJson(knowledgeMasterCounts));

        List<String> knowledgeIds = null;
        if(org.apache.commons.collections.CollectionUtils.isNotEmpty(knowledgeMasterCounts)){
            knowledgeIds = knowledgeMasterCounts.stream().map(k -> k.getKnowledgeId()).collect(Collectors.toList());
        }else{
            return null;
        }

        StringBuffer sbIds = new StringBuffer();
        if(org.apache.commons.collections.CollectionUtils.isNotEmpty(knowledgeIds)){
            for(int i = 0; i < knowledgeIds.size(); i++){
                if((i+1) == knowledgeIds.size()){
                    sbIds.append(knowledgeIds.get(i));
                }else{
                    sbIds.append(knowledgeIds.get(i)+",");
                }
            }
        }
        Map<String, Object> resourceParams = new HashMap<>(1);
        resourceParams.put("ids", sbIds.toString());
        log.info("knowledgeMasterCount > getKnowledgeMasterCount call resource plat: queryKnowledgeNames param:{}", JsonMapper.nonEmptyMapper().toJson(resourceParams));
        List<KnowledgeName> knowledgeNames = ServerRestTemplate.postList(resourceServerUrl + "/resource/knowledges/queryKnowledgeNames", KnowledgeName.class, resourceParams);
        log.info("knowledgeMasterCount > getKnowledgeMasterCount call resource plat: queryKnowledgeNames result: {}", JsonMapper.nonEmptyMapper().toJson(knowledgeNames));

        List<KnowledgeMasterCount> resultList = knowledgeMasterCounts.stream().map(km ->{
            knowledgeNames.forEach(kn ->{
                if(km.getKnowledgeId().equals(kn.getKnowledgeId())){
                    km.setKnowledgeName(kn.getKnowledgeName());
                    return;
                }
            });
            return  km;
        }).collect(Collectors.toList());

        return resultList;
    }

    public Integer numberTransformMaxValue(Integer number) {
        if(number == null || number == 0){
            return 0;
        }
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

    private List<Question> getMaterialQuestions(String studentCode, String subjectId, String id) {
        MaterialMaster master = materialMasterService.getAndInit(studentCode, subjectId, id);
        // 未通过的层级 = 已通过的层级 + 1
        int rank = master.getRank() + 1;
        if (rank > 3)
            return null;
        // 取已出过的题
        List<MaterialQuestion> mquestions = materialQuestionService.queryByMasterIdAndRank(master.getMasterId(), rank);
        //集合为空，说明在未通过的层级，没答过题，直接随机出题
        if (CollectionUtils.isEmpty(mquestions)) {
            // 第一次测验，随机出题
            List<Question> questionList = getResourceQuestionHasAnswer(0, rank, id);
            if (CollectionUtils.isEmpty(questionList))
                return null;
            List<MaterialQuestion> materialQuestionList = new ArrayList<>(questionList.size());
            for (int i = 0; i < questionList.size(); i ++) {
                materialQuestionList.add(new MaterialQuestion(genID(), master.getMasterId(), questionList.get(i).getQuestId(), i + 1, rank));
            }
            materialQuestionService.batchAdd(materialQuestionList);
            return questionList;
        } else { //集合不为空，说明在未通过的层级，答过题，但没通过，直接取出题目，重新答一次
            String questIds = mquestions.stream().map(question -> question.getQuestId()).collect(Collectors.joining(","));
            return getResourceQuestionHasAnswer(questIds);
        }
    }

    private List<Question> getKnowledgeQuestions(String studentCode, String subjectId, String id) {
        KnowledgeMaster master = knowledgeMasterService.getAndInit(studentCode, subjectId, id);
        // 未通过的层级 = 已通过的层级 + 1
        int rank = master.getRank() + 1;
        if (rank > 3)
            return null;
        // 取已出过的题
        List<KnowledgeQuestion> kquestions = knowledgeQuestionService.queryByMasterIdAndRank(master.getMasterId(), rank);
        log.info("[getTestQuestion] > impl [getKnowledgeQuestions] > (kquestions)={} ", JsonMapper.nonEmptyMapper().toJson(kquestions));
        if (CollectionUtils.isEmpty(kquestions)) {
            // 第一次测验，随机出题
            List<Question> questionList = getResourceQuestionHasAnswer(1, rank, id);
            if (CollectionUtils.isEmpty(questionList))
                return null;
            List<KnowledgeQuestion> knowledgeQuestionList = new ArrayList<>(questionList.size());
            for (int i = 0; i < questionList.size(); i ++) {
                knowledgeQuestionList.add(new KnowledgeQuestion(genID(), master.getMasterId(), questionList.get(i).getQuestId(), i + 1, rank));
            }
            knowledgeQuestionService.batchAdd(knowledgeQuestionList);
            return questionList;
        } else {
            String questIds = kquestions.stream().map(question -> question.getQuestId()).collect(Collectors.joining(","));
            List<Question> questionList = getResourceQuestionHasAnswer(questIds);
            log.info("[getTestQuestion] > impl [getKnowledgeQuestions] > (questionList)={} ", JsonMapper.nonEmptyMapper().toJson(questionList));
            return questionList;
        }
    }

    /**
     * 调用资源的题库出题
     * @param type
     * @param rank
     * @param id
     * @return
     */
    private List<Question> getResourceQuestionHasAnswer(int type, int rank, String id) {
        StringBuilder json = new StringBuilder();
        json.append("{\"type\":").append(type).append(",\"id\":").append(id).append(",\"rank\":").append(rank).append("}");
        log.info("getTestQuestion > impl getTestQuestion >  getResourceQuestionHasAnswer param={} ", json.toString());
        List<Question> questions = resourceGatewayHandle.postList("smartQuestion", json.toString(), Question.class);
        log.info("getTestQuestion > impl getTestQuestion >  getResourceQuestionHasAnswer result={} ", JsonMapper.nonEmptyMapper().toJson(questions));
        return questions;
    }

    /**
     * 调用资源的题库取明细
     * @param questIds
     * @return
     */
    private List<Question> getResourceQuestionHasAnswer(String questIds) {
        StringBuilder json = new StringBuilder();
        json.append("{\"questIds\":\"").append(questIds).append("\"}");
        return resourceGatewayHandle.postList("getQuestions", json.toString(), Question.class);
    }

    /**
     * 取masterId
     * @param type
     * @param studentCode
     * @param id
     * @return
     */
    private String getMasterId(Integer type, String studentCode, String id) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("studentCode", studentCode);
        params.put("chapterId", id);
        params.put("knowledgeId", id);
        return type.equals(0) ? materialMasterService.getByParams(params).getMasterId() : knowledgeMasterService.getByParams(params).getMasterId();
    }
}
