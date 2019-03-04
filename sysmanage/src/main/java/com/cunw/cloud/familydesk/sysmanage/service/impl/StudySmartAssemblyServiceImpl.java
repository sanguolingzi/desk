package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.common.dto.StudySmartAssemblyStatisticDTO;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.framework.utils.base.StringUtils;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.resource.dto.AnswerDTO;
import com.cunw.cloud.resource.dto.DirDTO;
import com.cunw.cloud.resource.dto.SmartPaperQuestionParamDTO;
import com.cunw.cloud.resource.dto.XinHuaQuestionDTO;
import com.cunw.cloud.resource.model.Question;
import com.cunw.cloud.resource.model.ResourceQuestionKnowledge;
import com.cunw.cloud.resource.model.XinHuaQuestionType;
import com.cunw.cloud.resource.model.question.QueryKnowledge;
import com.cunw.cloud.resource.model.question.QuestionType;
import com.cunw.cloud.familydesk.common.dto.QuestionAnswerDTO;
import com.cunw.cloud.familydesk.common.model.Paper;
import com.cunw.cloud.familydesk.common.model.PaperItems;
import com.cunw.cloud.familydesk.common.model.StudySmartAssembly;
import com.cunw.cloud.familydesk.common.model.StudySmartAssemblyQuestion;
import com.cunw.cloud.familydesk.resource.service.IPaperItemsService;
import com.cunw.cloud.familydesk.resource.service.IPaperService;
import com.cunw.cloud.familydesk.sysmanage.mapper.StudySmartAssemblyMapper;
import com.cunw.cloud.familydesk.sysmanage.service.IStudySmartAssemblyQuestionService;
import com.cunw.cloud.familydesk.sysmanage.service.IStudySmartAssemblyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * StudySmartAssembly服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-17 02:10
 * 类描述
 * 修订历史：
 * 日期：2018-08-17
 * 作者：generator
 * 参考：
 * 描述：StudySmartAssembly服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@Service(value = "studySmartAssemblyService")
public class StudySmartAssemblyServiceImpl extends BaseServiceImpl<StudySmartAssembly, String> implements IStudySmartAssemblyService {

    @Autowired
    private StudySmartAssemblyMapper studySmartAssemblyMapper;

    @Autowired
    private IPaperService paperService;

    @Autowired
    private IPaperItemsService paperItemsService;

    @Autowired
    private IStudySmartAssemblyQuestionService studySmartAssemblyQuestionService;

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    @Value("${plat.server.url}")
    private String platServerUrl;


    @Override
    protected IBaseMapper<StudySmartAssembly, String> getMapper() {
        return studySmartAssemblyMapper;
    }

    /**
     * 保存答题结果
     * @param model
     */
    @Override
    public StudySmartAssemblyStatisticDTO saveStudySmartAssemblyAnswer(StudySmartAssembly model){
        String studySmartAssemblyId = genID();
        try{
            Paper paper = paperService.getById(model.getPaperId());
            //获取dir目录
            DirDTO dirDTO = getDirDTO(paper.getDirId());
            model.parse(studySmartAssemblyId,dirDTO.getStage(),dirDTO.getSubjectId(),dirDTO.getVersionId(),dirDTO.getBookId(),model.getAnswerList().size());
            int result = studySmartAssemblyMapper.insert(model);
            if(result > 0){
                //保存答案
                StudySmartAssemblyStatisticDTO resultStatistic = studySmartAssemblyQuestionService.saveStudySmartAssemblyQuestion(studySmartAssemblyId, model.getAnswerList());
                if(Objects.nonNull(resultStatistic)){
                    //更新分数
                    StudySmartAssembly studySmartAssembly = new StudySmartAssembly();
                    studySmartAssembly.setAssemblyId(studySmartAssemblyId);
                    studySmartAssembly.setScore(resultStatistic.getRightAnswerCount().intValue());
                    studySmartAssemblyMapper.updateByPrimaryKeySelective(studySmartAssembly);
                }
                return resultStatistic;
            }
        }catch (Exception e){
            log.error("saveStudyExamTest error:{}", e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    private DirDTO getDirDTO(String dirId){
        return ServerRestTemplate.get(resourceServerUrl + "/resource/dirs/allparentId/"+dirId, DirDTO.class);
    }

    public List<QuestionAnswerDTO> getPaperDetailList(String assemblyId){
        //获取答题集合
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("assemblyId", assemblyId);
        paramMap.put("orderby", "questOrder");  //按照答题顺序排序
        List<StudySmartAssemblyQuestion> studySmartAssemblyQuestionList = studySmartAssemblyQuestionService.query(paramMap);

        //调用资源接口获取答案是否正确
        List<String> questionIdList = studySmartAssemblyQuestionList.stream().map(x -> {return x.getQuestId();}).collect(Collectors.toList());
        List<AnswerDTO> answerDTOList = getAnswerList(questionIdList);
        Map<String, AnswerDTO> studyMap =  answerDTOList.stream().collect(Collectors.toMap(AnswerDTO::getQuestionId, a -> a,(k1, k2)->k1));

        List<QuestionAnswerDTO> questionAnswerDTOList = studySmartAssemblyQuestionList.stream().map(x -> {
            QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO();
            if(studyMap.containsKey(x.getQuestId())){
                questionAnswerDTO.parse(studyMap.get(x.getQuestId()), x);
            }
            return questionAnswerDTO;
        }).collect(Collectors.toList());
        uptQuestionAnswerDTOOptons(questionAnswerDTOList);
        if(CollectionUtils.isNotEmpty(questionAnswerDTOList)){
            //添加子题
            String questionIds = questionAnswerDTOList.stream().map(q -> q.getQuestId()).collect(Collectors.joining(","));
            Map<String,String> param =new HashMap<>();
            param.put("ids", questionIds);
            List<Question> questionList = ServerRestTemplate.getList(resourceServerUrl + "/resource/questions/querys", Question.class, param);
            if(CollectionUtils.isNotEmpty(questionList)){
                for (Question q : questionList){
                    for(QuestionAnswerDTO qa :questionAnswerDTOList){
                        if(q.getQuestId().equals(qa.getQuestId())){
                            qa.setSubsets(q.getSubsets());
                            break;
                        }
                    }
                }
            }
        }

        return questionAnswerDTOList;
    }

    /**
     * 为option添加[]
     * @param xinHuaQuestionDTOList
     */
    public void uptQuestionAnswerDTOOptons(List<QuestionAnswerDTO> xinHuaQuestionDTOList){
        if(CollectionUtils.isNotEmpty(xinHuaQuestionDTOList)){
            xinHuaQuestionDTOList.forEach(qa -> {
                if(!StringUtils.isEmpty(qa.getOptions()) && !qa.getOptions().startsWith("[")){
                    qa.setOptions("["+qa.getOptions()+"]");
                }
            });
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

    @Override
    public PageList<Paper> getAnsweredPaperIdList(Integer pageNum, Integer pageSize, String dirId, String paperType, String studentCode, String user){
        //试卷查询条件
        Map<String, Object> params = new HashMap<>(5);
        params.put("subject",dirId);
        params.put("paperType",paperType);
        params.put("crtUserCode",user);
        params.put("studentCode",studentCode);
        params.put("orderby", "order by crt_date desc");
        PageList<Paper> list = paperService.queryForPage(params, pageNum, pageSize);
        if(CollectionUtils.isNotEmpty(list.getList())){
            List<Paper> resultPaperList = list.getList();
            List<StudySmartAssembly> smartAssemblyList = queryByProperty("studentCode", studentCode);
            if(CollectionUtils.isNotEmpty(smartAssemblyList)){
                Map<String,StudySmartAssembly> smartAssemblyMap = smartAssemblyList.stream().collect(Collectors.toMap(StudySmartAssembly::getPaperId, Function.identity(),(v1, v2) ->v1));
                resultPaperList = resultPaperList.stream().map(x -> {
                    boolean isExist = smartAssemblyList.stream().anyMatch(y -> y.getPaperId().equals(x.getPaperId()));
                    if(isExist){
                        x.setAssemblyId(smartAssemblyMap.get(x.getPaperId()).getAssemblyId());
                        x.setIsAnswer(1);
                        x.setScore(smartAssemblyMap.get(x.getPaperId()).getScore());
                    }else {
                        x.setIsAnswer(0);
                    }
                    return x;
                }).collect(Collectors.toList());
                list.setList(resultPaperList);
            }
        }
        return list;
    }


    public List<QueryKnowledge> getNoAnswerPaperStatistics(String paperId){
        //根据试卷id获取所有的题目id
        List<PaperItems> paperItemsList = paperItemsService.queryByProperty("paperId", paperId);
        Map<String,String> questionOrderMap = new HashMap<>();

        for (int i=0; i<paperItemsList.size(); i++){
            int orderIndex = i+1;
            questionOrderMap.put(paperItemsList.get(i).getQuestId(),orderIndex+"");
        }

        List<String> quesitonIdList = paperItemsList.stream().map(x -> {
            return x.getQuestId();
        }).collect(Collectors.toList());
        //根据题目id获取所有的知识点集合
        List<ResourceQuestionKnowledge> questionKnowLedgeList = getQuestionKnowLedgeList(quesitonIdList);
        //根据获取的题目集合可以用难度分组,分析难度占比多少
        Map<String,List<String>> difficultMap = questionKnowLedgeList.stream().collect(Collectors.groupingBy(ResourceQuestionKnowledge::getKnowledgeName,Collectors.mapping(ResourceQuestionKnowledge::getQuestId, Collectors.toList())));
        //分析知识点
        int totalCount = questionKnowLedgeList.size();
        List<QueryKnowledge> queryKnowledgeList = new ArrayList<>();
        difficultMap.forEach((x,y) -> {
            float rate = (float)y.size()/totalCount;
            QueryKnowledge queryKnowledge = new QueryKnowledge();
            queryKnowledge.setAccuracyRate(rate);
            queryKnowledge.setKnowledgeNames(x);
            queryKnowledge.setScore(y.size());
            //获取题目id排序
            List<String> resultOrderList = y.stream().map(w -> {
                if(questionOrderMap.containsKey(w)){
                   return questionOrderMap.get(w);
                }
                return "";
            }).collect(Collectors.toList());
            String orderQuestStr = String.join(",", resultOrderList);
            queryKnowledge.setQuestIdStr(orderQuestStr);
            queryKnowledgeList.add(queryKnowledge);
        });
        return queryKnowledgeList;
    }


    @Override
    public List<QueryKnowledge> getAnsweredPaperStatistics(String paperId, String assemblyId){
        //根据试卷id获取所有的题目id
        List<PaperItems> paperItemsList = paperItemsService.queryByProperty("paperId", paperId);
        List<String> quesitonIdList = paperItemsList.stream().map(x -> {
            return x.getQuestId();
        }).collect(Collectors.toList());
        //根据题目id获取所有的知识点集合
        List<ResourceQuestionKnowledge> questionKnowLedgeList = getQuestionKnowLedgeList(quesitonIdList);
        //根据获取的题目集合可以用难度分组,分析难度占比多少
        Map<String,List<String>> difficultMap = questionKnowLedgeList.stream().collect(Collectors.groupingBy(ResourceQuestionKnowledge::getKnowledgeName,Collectors.mapping(ResourceQuestionKnowledge::getQuestId, Collectors.toList())));
        //分析知识点
        int totalCount = questionKnowLedgeList.size();
        List<QueryKnowledge> queryKnowledgeList = new ArrayList<>();
        difficultMap.forEach((x,y) -> {
            float rate = (float)y.size()/totalCount;
            QueryKnowledge queryKnowledge = new QueryKnowledge();
            queryKnowledge.setScorePercentage(rate);
            queryKnowledge.setKnowledgeNames(x);
            queryKnowledge.setScore(y.size());
            //获取题目id排序
            List<StudySmartAssemblyQuestion> assemblyQuestionList = studySmartAssemblyQuestionService.selectByQuestId(y,assemblyId);
            List<String> questionOrderList = assemblyQuestionList.stream().map(z -> {return z.getQuestOrder().toString();}).collect(Collectors.toList());
            String orderQuestStr = String.join(",", questionOrderList);
            queryKnowledge.setQuestIdStr(orderQuestStr);
            float rightRate = (float) assemblyQuestionList.stream().filter(w -> w.getIsRight() != null && w.getIsRight()==1).count() / totalCount;
            queryKnowledge.setAccuracyRate(rightRate);
            queryKnowledgeList.add(queryKnowledge);
        });
        return queryKnowledgeList;
    }

    @Override
    public List<QuestionType> getQuestionCountByType(String dirIds, String type, Integer difficult, String questionTypes) {
        List<QuestionType> questionTypeList = null;
        Map<String,Object> param =new HashMap<>();
        param.put("difficult", difficult);
        param.put("questionTypes", questionTypes);
        if("0".equals(type)){//章节
            List<String> chapters = ServerRestTemplate.getList(resourceServerUrl + "/resource/dirs/chapter/"+dirIds, String.class);
            if(CollectionUtils.isNotEmpty(chapters)){
                String chapterStr = chapters.stream().map(c -> c).collect(Collectors.joining(","));
                param.put("chapterIds", chapterStr);
                questionTypeList = ServerRestTemplate.getList(resourceServerUrl + "/resource/xinhuaquestions/chapter", QuestionType.class, param);
            }
//            param.put("chapterIds", dirIds);
//            questionTypeList = ServerRestTemplate.getList(resourceServerUrl + "/resource/xinhuaquestions/chapter", QuestionType.class, param);
        }else{//知识点
            //param.put("knowledgeId", dirId);
            questionTypeList = ServerRestTemplate.getList(resourceServerUrl + "/resource/xinhuaquestions/knowledge/"+dirIds, QuestionType.class, param);
        }
        return questionTypeList;
    }

    public List<ResourceQuestionKnowledge> getQuestionKnowLedgeList(List<String> questionIdList){
        List<ResourceQuestionKnowledge> questionKnowLedgeList = new ArrayList<>();
        if(CollectionUtils.isEmpty(questionIdList)){
            return questionKnowLedgeList;
        }
        Map<String,String> param =new HashMap<>();
        param.put("ids", String.join(",",questionIdList));
        questionKnowLedgeList = ServerRestTemplate.getList(resourceServerUrl + "/resourcequestionknowledges/knowledgequestionlist", ResourceQuestionKnowledge.class, param);
        return questionKnowLedgeList;
    }


    /**
     * 获取智能组卷题目
     * @param
     * @return
     */
    @Override
    public List<XinHuaQuestionDTO> getSmartPaperQuestion(SmartPaperQuestionParamDTO smartPaperQuestionParamDTO){
        List<XinHuaQuestionDTO> xinHuaQuestionDTOList = new ArrayList<>();
        Map<String,Object> param =new HashMap<>();
        param.put("difficultId", smartPaperQuestionParamDTO.getDifficultId());
        param.put("type", smartPaperQuestionParamDTO.getType());
        param.put("questCount", smartPaperQuestionParamDTO.getQuestCount());
        param.put("dirId", smartPaperQuestionParamDTO.getDirId());

        xinHuaQuestionDTOList = ServerRestTemplate.postList(resourceServerUrl + "/resource/questions/getNewSmartPaperQuestion", XinHuaQuestionDTO.class, param);

//        if(smartPaperQuestionParamDTO.getType().equals("0")){
//            List<String> chapters = ServerRestTemplate.getList(resourceServerUrl + "/resource/dirs/chapterSubset/"+smartPaperQuestionParamDTO.getDirId(), String.class);
//            if(CollectionUtils.isNotEmpty(chapters)){
//                String chapterStr = chapters.stream().map(c -> c).collect(Collectors.joining(","));
//                smartPaperQuestionParamDTO.setDirId(chapterStr);
//                xinHuaQuestionDTOList = ServerRestTemplate.postList(resourceServerUrl + "/resource/questions/getNewSmartPaperQuestion/"+chapterStr, XinHuaQuestionDTO.class, param);
//            }
//        }else{
//            List<String> knowledges = ServerRestTemplate.getList(resourceServerUrl + "/resource/xinhuaknowledgepoints/knowledgeSubset/"+smartPaperQuestionParamDTO.getDirId(), String.class);
//            if(CollectionUtils.isNotEmpty(knowledges)){
//                String knowledgesStr = knowledges.stream().map(c -> c).collect(Collectors.joining(","));
//                smartPaperQuestionParamDTO.setDirId(knowledgesStr);
//                xinHuaQuestionDTOList = ServerRestTemplate.postList(resourceServerUrl + "/resource/questions/getNewSmartPaperQuestion/"+knowledgesStr, XinHuaQuestionDTO.class, param);
//            }
//        }

        return xinHuaQuestionDTOList;
    }

    @Override
    public List<XinHuaQuestionType> selectByQuestType(Integer subjectId) {
        Map<String,Object> param =new HashMap<>();
        param.put("subjectId",subjectId);
        return ServerRestTemplate.getList(resourceServerUrl + "/resource/xinhuaquestiontypes/questType/"+subjectId, XinHuaQuestionType.class,param);
    }

}
