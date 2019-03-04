package com.cunw.cloud.familydesk.resource.service.impl;

import com.cunw.cloud.framework.auth.shiro.realm.UserPayloadUtils;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.resource.dto.XinHuaQuestionDTO;
import com.cunw.cloud.resource.model.Question;
import com.cunw.cloud.resource.model.QuestionAnswer;
import com.cunw.cloud.resource.model.question.QueryQuestion;
import com.cunw.cloud.familydesk.common.model.Paper;
import com.cunw.cloud.familydesk.common.model.PaperItems;
import com.cunw.cloud.familydesk.common.model.PracticeItems;
import com.cunw.cloud.familydesk.resource.mapper.PaperMapper;
import com.cunw.cloud.familydesk.resource.service.IPaperItemsService;
import com.cunw.cloud.familydesk.resource.service.IPaperService;
import com.cunw.cloud.familydesk.resource.service.IPracticeItemsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Paper服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-02-26 03:38
 * 类描述
 * 修订历史：
 * 日期：2018-02-26
 * 作者：generator
 * 参考：
 * 描述：Paper服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "paperService")
public class PaperServiceImpl extends BaseServiceImpl<Paper, String> implements IPaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private IPaperItemsService paperItemsService;

    @Autowired
    private IPracticeItemsService practiceItemsService;


    @Value("${resource.version.url}")
    String resourceServerUrl;

    @Override
    protected IBaseMapper<Paper, String> getMapper() {
        return paperMapper;
    }

    @Override
    public RESTfulResult addPaper(Paper paper){
        RESTfulResult resTfulResult = new RESTfulResult();
        ObjectMapper mapper = new ObjectMapper();
        paper.setId(genID());
        paper.setStatus("1");
        paper.setCrtUserCode(UserPayloadUtils.getCurrUser().getLoginName());
        paper.setSetPeople(paper.getCrtUserCode());
        paper.setDownloadNum(0);
        paper.setCrtDate(new Date());
        try{
            int result = paperMapper.insert(paper);
            if(result > 0){
                String newpaperId = paper.getPaperId();
                if(!CollectionUtils.isEmpty(paper.getPaperItems())){
                    List<PaperItems> paperItemList = paper.getPaperItems().stream().map(item -> {
                        item.setPaperId(newpaperId);
                        return item;
                    }).collect(Collectors.toList());

                    paperItemsService.batchAdd(paperItemList);

                    resTfulResult.setCode("0");
                    resTfulResult.setSucceedMessage("试卷保存成功");
                    resTfulResult.setSucceed();
                }
            }
        } catch (Exception e) {
            resTfulResult.setCode("-1");
            resTfulResult.setFailedMessage(e.getMessage());
            resTfulResult.setFailed();
            e.printStackTrace();
        }

        return resTfulResult;
    }

    @Override
    public void addPractices(Paper paper){
        //新增随堂练习
        paper.setId(genID());
        int result = paperMapper.insert(paper);
        if(result>0){
            if (CollectionUtils.isNotEmpty(paper.getPracticeItems())) {
                List<PracticeItems> practiceItemList = paper.getPracticeItems().stream().map(items -> {
                    items.setPaperId(paper.getPaperId());
                    return items;
                }).collect(Collectors.toList());
                try{
                    practiceItemsService.batchAdd(practiceItemList);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public Paper getPaperDetail(String paperId){
        //查询试卷
        Paper paper = paperMapper.selectByPrimaryKey(paperId);
        //查询试卷题目
        List<PaperItems> paperItemslist  = paperItemsService.queryByProperty("paperId",paperId);
        paper.setPaperItems(paperItemslist);
        //查询题目详情
        for (int i=0;i<paperItemslist.size();i++){
            PaperItems paperItem = paperItemslist.get(i);
            XinHuaQuestionDTO xinHuaQuestionDTO = ServerRestTemplate.get(resourceServerUrl + "/resource/questions/getQuestion/" + paperItem.getQuestId(), XinHuaQuestionDTO.class);
            Question question = new Question();
            question.parse(xinHuaQuestionDTO);
            question.setPaperId(paperItem.getPaperId());
            paperItem.setQuestions(question);
        }
        return paper;
    }

    @Override
    public Paper getPracticePaperDetail(String paperId){
        //查询试卷
        Paper paper = paperMapper.selectByPrimaryKey(paperId);
        //查询试卷题目
        List<PracticeItems> paperItemslist  = practiceItemsService.queryByProperty("paperId",paperId);
        paper.setPracticeItems(paperItemslist);
        //查询题目详情
        for (int i=0;i<paperItemslist.size();i++){
            PracticeItems practiceItem = paperItemslist.get(i);
            Question question = ServerRestTemplate.get(resourceServerUrl + "/resource/questions/getQuestion/" + practiceItem.getQuestId(), Question.class);
            practiceItem.setQuestions(question);
        }
        return paper;
    }

    public PageList<Paper> getParentPaperList(Map<String, Object> params, Integer pageNum, Integer pageSize, String studentCode){
        PageList<Paper> paperList = queryForPage(params, pageNum, pageSize);
        if(paperList.getList() != null){
//            List<Paper> resultPaper =  paperList.getList().stream().map()
        }
        return null;
    }

    @Override
    public List<QueryQuestion> getDifficultStatistic(String paperId){
        //根据试卷id获取所有的题目id
        List<PaperItems> paperItemsList = paperItemsService.queryByProperty("paperId", paperId);
        List<String> quesitonIdList = paperItemsList.stream().map(x -> {
            return x.getQuestId();
        }).collect(Collectors.toList());
        //根据题目id获取所有的题目集合
        List<QuestionAnswer> questionAnswerList = getQuestionAnswerList(quesitonIdList);
        //根据获取的题目集合可以用难度分组,分析难度占比多少
        Map<String,Long> difficultMap = questionAnswerList.stream().collect(Collectors.groupingBy(QuestionAnswer::getDifficultName,Collectors.counting()));
        //分析难度
        int totalCount = quesitonIdList.size();
        List<QueryQuestion> queryQuestionList = new ArrayList<>();
        difficultMap.forEach((x,y) -> {
            float rate = (float)y/totalCount;
            QueryQuestion queryQuestion = new QueryQuestion();
            queryQuestion.setDifficultPercentage(rate);
            queryQuestion.setCount(y.intValue());
            queryQuestion.setDifficultName(x);
            queryQuestionList.add(queryQuestion);
        });
        return queryQuestionList;
    }

    public List<QuestionAnswer> getQuestionAnswerList(List<String> questionIdList){
        List<QuestionAnswer> questionAnswerList = new ArrayList<>();
        if(CollectionUtils.isEmpty(questionIdList)){
            return questionAnswerList;
        }
        Map<String,String> param =new HashMap<>();
        param.put("ids", String.join(",",questionIdList));
        questionAnswerList = ServerRestTemplate.postList(resourceServerUrl + "/resource/questions/getQuestion/answer", QuestionAnswer.class, param);
        return questionAnswerList;
    }

}
