package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.service.pagehelper.PageHelper;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.resource.dto.DirDTO;
import com.cunw.cloud.resource.model.Dir;
import com.cunw.cloud.resource.model.KnowledgeCount;
import com.cunw.cloud.resource.model.QuestionAnswer;
import com.cunw.cloud.familydesk.common.model.StudentWrongQuestion;
import com.cunw.cloud.familydesk.common.model.WrongQuestion;
import com.cunw.cloud.familydesk.sysmanage.mapper.StudentWrongQuestionMapper;
import com.cunw.cloud.familydesk.sysmanage.service.IStudentWrongQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * StudentWrongQuestion服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 03:44
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：StudentWrongQuestion服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@Service(value = "studentWrongQuestionService")
public class StudentWrongQuestionServiceImpl extends BaseServiceImpl<StudentWrongQuestion, String> implements IStudentWrongQuestionService {

    @Autowired
    private StudentWrongQuestionMapper studentWrongQuestionMapper;

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    @Override
    protected IBaseMapper<StudentWrongQuestion, String> getMapper() {
        return studentWrongQuestionMapper;
    }

    @Override
    public List<WrongQuestion> getSubjectWrongQuestions(String studentCode, Integer stage) {
        log.info("call subjectlistQuestions method call getSubjectWrongQuestions param studentCode: ({})", studentCode);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("studentCode", studentCode);
        params.put("stage", stage);
        List<WrongQuestion> wrongQuestionCounts = studentWrongQuestionMapper.getSubjectWrongQuestions(params);
        log.info("call subjectlistQuestions method call getSubjectWrongQuestions result: ({})", JsonMapper.nonEmptyMapper().toJson(wrongQuestionCounts));

        return wrongQuestionCounts;
    }

    @Override
    public List<WrongQuestion> getSubjectBookWrongQuestions(String studentCode, String subjectId) {

        log.info("listBookQuestions method call service getSubjectBookWrongQuestions param studentCode: ({}), subjectId=({})",studentCode, subjectId);
        List<WrongQuestion> wrongQuestionCounts = studentWrongQuestionMapper.getSubjectBookWrongQuestions(studentCode, subjectId);
        log.info("listBookQuestions method call service getSubjectBookWrongQuestions result: ({})", JsonMapper.nonEmptyMapper().toJson(wrongQuestionCounts));

        return wrongQuestionCounts;
    }

    @Override
    public PageList<WrongQuestion> getKnowledgeWrongQuestionListBySubject(String studentCode, String subjectId, Integer pageNum, Integer pageSize) {
        List<KnowledgeCount> knowledgeCounts = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("studentCode", studentCode);
        params.put("subjectId", subjectId);
        log.info("[listKnowledgeQuestions] service [getKnowledgeWrongQuestionListBySubject] -> [selectknowledgeWrongQuestionCountBySubject] param: ({})", JsonMapper.nonEmptyMapper().toJson(params));
        PageHelper.startPage(pageNum, pageSize);
        List<WrongQuestion> wrongQuestionCounts = studentWrongQuestionMapper.selectknowledgeWrongQuestionCountBySubject(params);
        log.info("[listKnowledgeQuestions] service [getKnowledgeWrongQuestionListBySubject] -> [selectknowledgeWrongQuestionCountBySubject] result: ({})", JsonMapper.nonEmptyMapper().toJson(wrongQuestionCounts));
        PageList pageInfo = new PageList(wrongQuestionCounts);
        return pageInfo;
    }

    @Override
    public PageList<WrongQuestion> getKnowledgeWrongQuestionList(String studentCode, String subjectId, String bookId, Integer pageNum, Integer pageSize) {
        List<KnowledgeCount> knowledgeCounts = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("studentCode", studentCode);
        params.put("subjectId", subjectId);
        params.put("bookId", bookId);
        log.info("[bookKnowledgeQuestions] service [getBookKnowledgeWrongQuestions] -> [getknowledgeWrongQuestionCount] param: ({})", JsonMapper.nonEmptyMapper().toJson(params));
        PageHelper.startPage(pageNum, pageSize);
        List<WrongQuestion> wrongQuestionCounts = studentWrongQuestionMapper.getknowledgeWrongQuestionCount(params);
        log.info("[bookKnowledgeQuestions] service [getBookKnowledgeWrongQuestions] -> [getknowledgeWrongQuestionCount] result: ({})", JsonMapper.nonEmptyMapper().toJson(wrongQuestionCounts));
        PageList pageInfo = new PageList(wrongQuestionCounts);
        return pageInfo;
    }

    @Override
    public Map<String, Object> getWrongQuestionDetails(String studentCode, String dirId, String startDate, String endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> questionIds = null;
        params.put("studentCode", studentCode);
        params.put("subjectId", dirId);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        List<StudentWrongQuestion> wrongQuestions = studentWrongQuestionMapper.getWrongQuestions(params);
        log.info("call subjectviewQuestions method call getStudentWrongQuestionIds result: ({})", JsonMapper.nonEmptyMapper().toJson(wrongQuestions));
        if (CollectionUtils.isNotEmpty(wrongQuestions)){
            questionIds = wrongQuestions.stream().map(q -> q.getQuestionId()).collect(Collectors.toList());
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
        log.info("subjectviewQuestions > getQuestionView call resource plat: getQuestion/answer param:{}", JsonMapper.nonEmptyMapper().toJson(resourceParams));
        List<QuestionAnswer> questionAnswers = ServerRestTemplate.postList(resourceServerUrl + "/resource/questions/getQuestion/answer", QuestionAnswer.class, resourceParams);
        log.info("subjectviewQuestions > getQuestionView call resource plat: getQuestion/answer result: {}", JsonMapper.nonEmptyMapper().toJson(questionAnswers));

        //排序
        List<QuestionAnswer> orderQuestionAnswer = new ArrayList<>();
        wrongQuestions.forEach(wq -> {
            questionAnswers.forEach(qa -> {
                if(wq.getQuestionId() .equals(qa.getQuestId())){
                    qa.setWrongQuestionId(wq.getWrongQuestionId());
                    orderQuestionAnswer.add(qa);
                    wq.setQuestionAnswer(qa);
                    return;
                }
            });

        });

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("count",orderQuestionAnswer.size());
        dataMap.put("wrongQuestion", wrongQuestions);
        return dataMap;
    }

    @Override
    public List<QuestionAnswer> getKnowledgeWrongQuestionDetails(Map<String, Object> params, String knowledgeId) {

        List<String> questionIds = null;
        log.info("viewKnowledgeQuestions call getWrongQuestionDetails call getWrongQuestionIds param params: ({})", JsonMapper.nonEmptyMapper().toJson(params));
        List<StudentWrongQuestion> wrongQuestions = studentWrongQuestionMapper.getWrongQuestionIds(params);
        log.info("viewKnowledgeQuestions call getWrongQuestionDetails call getWrongQuestionIds result: ({})", JsonMapper.nonEmptyMapper().toJson(wrongQuestions));
        if (CollectionUtils.isNotEmpty(wrongQuestions)){
            questionIds = wrongQuestions.stream().map(q -> q.getQuestionId()).collect(Collectors.toList());
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
        resourceParams.put("knowledgeId", knowledgeId);
        log.info("viewKnowledgeQuestions call getWrongQuestionDetails call resource plat: getQuestion/knowledge param:{}", JsonMapper.nonEmptyMapper().toJson(resourceParams));
        List<QuestionAnswer> questionAnswers = ServerRestTemplate.postList(resourceServerUrl + "/resource/questions/getQuestion/knowledge", QuestionAnswer.class, resourceParams);
        log.info("viewKnowledgeQuestions call getWrongQuestionDetails call resource plat: getQuestion/knowledge result: {}", JsonMapper.nonEmptyMapper().toJson(questionAnswers));

        //插入错题ID
        questionAnswers.forEach(q -> {
            wrongQuestions.forEach(wq ->{
                if(q.getQuestId().equals(wq.getQuestionId())){
                    q.setWrongQuestionId(wq.getWrongQuestionId());
                    return;
                }
            });
        });
        return questionAnswers;
    }

    @Override
    public RESTfulResult insertWrongQuestion(StudentWrongQuestion model) {
        RESTfulResult resTfulResult = new RESTfulResult();
        boolean isExist = false;
        int result = 0;
        Map<String, Object> map = new HashMap<>();
        map.put("studentCode", model.getStudentCode());
        map.put("questionId", model.getQuestionId());
        List<StudentWrongQuestion> studentWrongQuestionList = query(map);
        if(CollectionUtils.isNotEmpty(studentWrongQuestionList)){
            //是否已经加入错题本
//            isExist = studentWrongQuestionList.stream().anyMatch(tq -> tq.getQuestionId().equals(model.getQuestionId()));
            isExist = true;
        }
        if(!isExist){
            model.setWrongQuestionId(genID());
            model.setStatus("0");
            log.info("insertWrongQuestion call getQuestionChapterInfo call resource plat: param:{}", model.getQuestionId());
            DirDTO dirDTO = ServerRestTemplate.get(resourceServerUrl + "/resource/questions/chapter/"+model.getQuestionId()+"?knowledgeId="+model.getKnowledgeId(), DirDTO.class);
            log.info("insertWrongQuestion call getQuestionChapterInfo call resource plat: result:{}", dirDTO.toString());
            if(dirDTO != null && dirDTO.getSubjectId() != null){
                model.setSubjectId(dirDTO.getSubjectId());
                model.setSubjectName(dirDTO.getSubjectIdName());
//                    if(dirDTO.getBookId() == null){
//                        model.setBookId("other");
//                        model.setBookName("其他");
//                    }else{
//                        model.setBookId(dirDTO.getBookId());
//                        model.setBookName(dirDTO.getBookIdName());
//                    }
                model.setKnowledgeId(dirDTO.getKnowledgeId());
                model.setKnowledgeName(dirDTO.getKnowledgeName());
            }

            model.setCrtDate(new Date());
            model.setCrtUserCode(this.getCurrUser().getLoginName());
            result = studentWrongQuestionMapper.insert(model);
        }

        log.info("insertWrongQuestion call insert StudentWrongQuestion result:{}, model={}", result, model.toString());
        if(result > 0 || isExist){
            resTfulResult.setCode("0");
            resTfulResult.setSucceedMessage("加入错题成功");
            resTfulResult.setSucceed();
        }else{
            resTfulResult.setCode("-1");
            resTfulResult.setFailedMessage("加入错题失败");
            resTfulResult.setFailed();
        }
        log.info("insertWrongQuestion impl return resTfulResult:{}", JsonMapper.nonEmptyMapper().toJson(resTfulResult));
        return resTfulResult;
    }

    @Override
    public RESTfulResult updateStudentWrongQuestion(String id, String label) {
        RESTfulResult resTfulResult = new RESTfulResult();
        resTfulResult.setCode("-1");
        resTfulResult.setFailedMessage("添加标签失败");
        resTfulResult.setFailed();
        StudentWrongQuestion studentWrongQuestion = getById(id);
        if(studentWrongQuestion != null) {
            studentWrongQuestion.setLabel(label);
            int rerult = studentWrongQuestionMapper.updateByPrimaryKeySelective(studentWrongQuestion);
            if(rerult > 0 ){
                resTfulResult.setCode("0");
                resTfulResult.setSucceedMessage("添加标签成功");
                resTfulResult.setSucceed();
            }
        }
        return resTfulResult;
    }

    public StudentWrongQuestion fillWrongQuestion(StudentWrongQuestion studentWrongQuestion, List<Dir> dirs){
        dirs.forEach(d -> {
            if(studentWrongQuestion.getSubjectId().equals(d.getDirId())){
                studentWrongQuestion.setSubjectName(d.getDirName());
            }
        });
        return studentWrongQuestion;
    }
}
