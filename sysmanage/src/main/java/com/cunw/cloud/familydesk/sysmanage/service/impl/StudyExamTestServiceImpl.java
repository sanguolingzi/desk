package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.common.dto.DownLoadFileDTO;
import com.cunw.cloud.familydesk.sysmanage.api.handle.PlatGatewayHandle;
import com.cunw.cloud.familydesk.sysmanage.api.handle.ResourceGatewayHandle;
import com.cunw.cloud.familydesk.sysmanage.mapper.StudyExamTestMapper;
import com.cunw.cloud.familydesk.sysmanage.service.IStudyExamTestAnswerService;
import com.cunw.cloud.familydesk.sysmanage.service.IStudyExamTestService;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.plat.common.model.MyPaper;
import com.cunw.cloud.resource.dto.AnswerDTO;
import com.cunw.cloud.resource.dto.DirDTO;
import com.cunw.cloud.resource.model.Dir;
import com.cunw.cloud.resource.model.RealPaper;
import com.cunw.cloud.resource.model.question.QueryQuestion;

import com.cunw.cloud.familydesk.common.dto.AnswerParam;
import com.cunw.cloud.familydesk.common.dto.StudyExamTestParam;
import com.cunw.cloud.familydesk.common.dto.StudyExamTestStatisticDTO;
import com.cunw.cloud.familydesk.common.dto.SubjectDTO;
import com.cunw.cloud.familydesk.common.model.StudyExamTest;
import com.cunw.cloud.familydesk.common.model.StudyExamTestAnswer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * StudyExamTest服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-15 03:10
 * 类描述
 * 修订历史：
 * 日期：2018-08-15
 * 作者：generator
 * 参考：
 * 描述：StudyExamTest服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@Service(value = "studyExamTestService")
public class StudyExamTestServiceImpl extends BaseServiceImpl<StudyExamTest, String> implements IStudyExamTestService {

    @Autowired
    private StudyExamTestMapper studyExamTestMapper;

    @Autowired
    private IStudyExamTestAnswerService studyExamTestAnswerService;

    @Override
    protected IBaseMapper<StudyExamTest, String> getMapper() {
        return studyExamTestMapper;
    }

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    @Value("${resource.server.url}")
    private String resourceUrl;


    @Value("${plat.server.url}")
    private String platServerUrl;

    @Autowired
    private PlatGatewayHandle platGatewayHandle;

    @Autowired
    private ResourceGatewayHandle resourceGatewayHandle;


    @Override
    public List<QueryQuestion> getAnswerCount(String paperId) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("paperId",paperId);
        List<QueryQuestion>  paperDifficultList = ServerRestTemplate.getList(resourceServerUrl + "/paperquestions/paperDifficult?paperId="+paperId, QueryQuestion.class);
        return paperDifficultList;
    }

    /*@Override
    public List<KnowledgeParamDTO> getKnowledgeAnalysis(String paperId) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("paperId",paperId);
        //根据试卷ID查找知识点
       List<String>  paperKnowledgeList= ServerRestTemplate.getList(resourceServerUrl + "/paperquestions/paperKnowledge/{paperId}", String.class, paramMap);


        return null;
    }*/

    /**
     * 保存答题结果
     * @param studyExamTestParam
     */
    @Transactional
    @Override
    public StudyExamTestStatisticDTO saveStudyExamTest(StudyExamTestParam studyExamTestParam){
        boolean isSuccess = false;
        String studyExamTestId = genID();
        long rightAnswerCount = 0;
        long wrongAnswerCount = 0;
        Long score = 0l;
        try{
            StudyExamTest studyExamTest = new StudyExamTest();
            long startSecond = studyExamTestParam.getStartTime().getTime();
            long endSecond = studyExamTestParam.getEndTime().getTime();
            int useTime = (int)((endSecond - startSecond) / 1000);//计算用时多久
            studyExamTest.setId(studyExamTestId);
            studyExamTest.setUseTime(useTime);
            studyExamTest.setStudentCode(studyExamTestParam.getStudentCode());
            studyExamTest.setPaperId(studyExamTestParam.getPaperId());
            studyExamTest.setStartTime(studyExamTestParam.getStartTime());
            studyExamTest.setEndTime(studyExamTestParam.getEndTime());
            studyExamTest.setCrtDate(new java.util.Date());
            studyExamTest.setPaperName(studyExamTestParam.getPaperName());
            //获取dir的上级目录
            DirDTO dirDTO = ServerRestTemplate.get(resourceServerUrl + "/resource/dirs/allparentId/"+studyExamTestParam.getDirId(), DirDTO.class);
            studyExamTest.setStage(dirDTO.getStage());
            studyExamTest.setSubjectId(dirDTO.getSubjectId());
            int result = studyExamTestMapper.insert(studyExamTest);
            if(result > 0){
                //保存答案
                if(CollectionUtils.isNotEmpty(studyExamTestParam.getAnswerList())){
                    //调用资源接口获取答案是否正确
                    List<String> answerList = studyExamTestParam.getAnswerList().stream().map(x -> {return x.getQuestionId();}).collect(Collectors.toList());
                    Map<String,String> param =new HashMap<>();
                    param.put("qustionStr", JsonMapper.nonEmptyMapper().toJson(answerList));
                    List<AnswerDTO> answerDTOList = ServerRestTemplate.getList(resourceServerUrl + "/resource/questiondetails/questionanswers", AnswerDTO.class, param);
                    int index = 1;
                    List<StudyExamTestAnswer> studyExamTestAnswerList = new ArrayList<>();
                    for (AnswerParam answerParam:studyExamTestParam.getAnswerList()) {
                        StudyExamTestAnswer studyExamTestAnswer = new StudyExamTestAnswer();
                        studyExamTestAnswer.setId(genID());
                        studyExamTestAnswer.setTestId(studyExamTestId);
                        studyExamTestAnswer.setStudentCode(studyExamTestParam.getStudentCode());
                        studyExamTestAnswer.setQuestId(answerParam.getQuestionId());
                        studyExamTestAnswer.setQuestOrder(index);
//                        studyExamTestAnswer.setAnswerTime(answerParam.getAnswerTime());
                        studyExamTestAnswer.setAnswer(answerParam.getAnswer());
                        for (AnswerDTO rightAnswer : answerDTOList) {
                            if(rightAnswer.getQuestionId().equals(answerParam.getQuestionId())){
                                if(StringUtils.isEmpty(answerParam.getAnswer())){
                                    studyExamTestAnswer.setIsRight(0);  //错误
                                }else if(StringUtils.isEmpty(rightAnswer.getAnswer()) && StringUtils.isNotEmpty(rightAnswer.getAnswerJson())){
                                    if(rightAnswer.getAnswerJson().contains(answerParam.getAnswer())){
                                        studyExamTestAnswer.setIsRight(1);  //正确
                                        studyExamTestAnswer.setScore(1);
                                    }else{
                                        studyExamTestAnswer.setIsRight(0);  //错误
                                        studyExamTestAnswer.setScore(0);
                                    }
                                }else if(StringUtils.isNotEmpty(rightAnswer.getAnswer())){
                                    if(rightAnswer.getAnswer().equals(answerParam.getAnswer())){
                                        studyExamTestAnswer.setIsRight(1);  //正确
                                        studyExamTestAnswer.setScore(1);
                                    }else{
                                        studyExamTestAnswer.setIsRight(0);  //错误
                                        studyExamTestAnswer.setScore(0);
                                    }
                                }else {
                                    studyExamTestAnswer.setIsRight(0);  //错误
                                    studyExamTestAnswer.setScore(0);
                                }
                                studyExamTestAnswer.setRightAnswer(rightAnswer.getAnswer());//正确答案
                                break;
                            }
                        }
                        studyExamTestAnswerList.add(studyExamTestAnswer);
                        index++;
                    }
                    studyExamTestAnswerService.batchAdd(studyExamTestAnswerList);
                    //计算总分
                    score = studyExamTestAnswerList.stream().filter(x -> x.getIsRight() != null && x.getIsRight()==1).count();
                    rightAnswerCount = studyExamTestAnswerList.stream().filter(x -> x.getIsRight() != null && x.getIsRight()==1).count();
                    wrongAnswerCount = studyExamTestAnswerList.stream().filter(x -> x.getIsRight() != null && x.getIsRight()!=1).count();
                    studyExamTest.setScore(score.intValue());
                    studyExamTestMapper.updateByPrimaryKey(studyExamTest);
                    isSuccess = true;
                }
            }
        }catch (Exception e){
            log.error("saveStudyExamTest error:{}",e.getLocalizedMessage());
            e.printStackTrace();
        }
        if(isSuccess){
            //如果保存成功给返回是答题数
            StudyExamTestStatisticDTO statisticDTO = new StudyExamTestStatisticDTO(studyExamTestId, rightAnswerCount, wrongAnswerCount, score);
            return statisticDTO;
        }
        return null;
    }

    @Override
    public List<SubjectDTO> getPaperStatistics(String stage, String studentCode){
        List<StudyExamTest> studyExamTestList = queryByProperty("studentCode", studentCode);
        //获取该学段下的所有科目
        Map<String,String> param =new HashMap<>();
        param.put("pDirId", stage);
        List<Dir> answerDTOList = ServerRestTemplate.getList(resourceServerUrl + "/resource/dirs/"+stage+"/list", Dir.class, param);
        List<SubjectDTO> subjectList = answerDTOList.stream().map(x -> {
            return new SubjectDTO(x.getDirId(), x.getDirName(), 0L);
        }).collect(Collectors.toList());

        if(CollectionUtils.isNotEmpty(studyExamTestList)){
            //分组,统计某科目下做了几套试卷
            Map<String,Long> studyExamTestMap = studyExamTestList.stream().collect(Collectors.groupingBy(StudyExamTest::getSubjectId,Collectors.counting()));
            return subjectList.stream().map(x -> {
                if(studyExamTestMap.containsKey(x.getSubjectId())){
                    x.setPaperCount(studyExamTestMap.get(x.getSubjectId()));
                }
                return x;
            }).collect(Collectors.toList());
        }
        return subjectList;
    }

//    public List<BookDTO> getPaperBookStatistics(String subjectId, String studentCode){
//        //获取该科目下的集合
//        List<StudyExamTest> studyExamTestList = queryByProperty("subjectId", subjectId);
//        //以册别分组,统计某册别下做了几套试卷
//        Map<String,Long> studyExamTestMap = studyExamTestList.stream().collect(Collectors.groupingBy(StudyExamTest::getBookid,Collectors.counting()));
//        //获取该版本下的册别
//        List<BookDTO> bookDTOList = new ArrayList<>();
//
//        List<BookDTO> resultbookDTOList = new ArrayList<>();
//        for (BookDTO book : bookDTOList) {
//            if(studyExamTestMap.containsKey(book.getBookId())){
//                resultbookDTOList.add(book);
//            }
//        }
//        return resultbookDTOList;
//    }

    @Override
    public PageList<StudyExamTest> getPaperList(String subjectId, String studentCode, Integer pageNum, Integer pageSize){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("subjectId", subjectId);
        paramMap.put("studentCode", studentCode);
        return queryForPage(paramMap, pageNum, pageSize);
    }

    @Override
    public List<StudyExamTestAnswer> getPaperDetailList(String testId){
        //获取答题集合
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("testId", testId);
        paramMap.put("orderby", "questOrder");  //按照答题顺序排序
        List<StudyExamTestAnswer> studyExamTestAnswerList = studyExamTestAnswerService.query(paramMap);

        //获取试卷明细，去资源系统查询
        List<String> questionIdList = studyExamTestAnswerList.stream().map(x -> {return x.getQuestId();}).collect(Collectors.toList());
        List<AnswerDTO> answerDTOList = getAnswerList(questionIdList);

        Map<String, AnswerDTO> studyMap =  answerDTOList.stream().collect(Collectors.toMap(AnswerDTO::getQuestionId, a -> a,(k1, k2)->k1));
        studyExamTestAnswerList = studyExamTestAnswerList.stream().map(x -> {
            if(studyMap.containsKey(x.getQuestId())){
                AnswerDTO answerDTO = studyMap.get(x.getQuestId());
                x.setRightAnswer(answerDTO.getAnswer());
                x.setExplanation(answerDTO.getExplanation());
                x.setStem(answerDTO.getStem());
                x.setOptions(answerDTO.getOptions());
                x.setType(answerDTO.getType());
                x.setTypeName(answerDTO.getTypeName());
                x.setDifficultName(answerDTO.getDifficultName());
            }
            return x;
        }).collect(Collectors.toList());
        return studyExamTestAnswerList;
    }

    @Override
    public PageList<RealPaper> getRealPaperList(String subjectId, Integer pageNum, Integer pageSize) {
        StringBuilder platJson = new StringBuilder();
        platJson.append("{\"userCode\":").append("\"").append(this.getCurrUser().getLoginName()).append("\"").append("}");
        log.info("[getRealPaperList]  call plat [GetRealPapersProcessService] param={} ", platJson.toString());
        //获取已下载真题试卷
        List<MyPaper> myPaperList = platGatewayHandle.postList("getRealPapers", platJson.toString(), MyPaper.class);
        log.info("[getRealPaperList]  call plat [GetRealPapersProcessService] Downloaded paper={} ", JsonMapper.nonDefaultMapper().toJson(myPaperList));

        StringBuilder resourceJson = new StringBuilder();
        if(CollectionUtils.isNotEmpty(myPaperList)){
            String paperIds = myPaperList.stream().map(paper -> paper.getPaperId()).collect(Collectors.joining(","));
            resourceJson.append("{\"subjectId\":").append("\"").append(subjectId).append("\"").append(",\"paperIds\":").append("\"").append(paperIds).append("\"").append(",\"pageNum\":").append(pageNum).append(",\"pageSize\":").append(pageSize).append("}");
        }else{
            resourceJson.append("{\"subjectId\":").append("\"").append(subjectId).append("\"").append(",\"pageNum\":").append(pageNum).append(",\"pageSize\":").append(pageSize).append("}");
        }

        //获取未下载的真题试卷
        log.info("[getRealPaperList]  call resource [getNotDownloadedPaper] param={} ", resourceJson.toString());
        RESTfulResult resTfulResult = resourceGatewayHandle.post("getNotDownloadedPaper", resourceJson.toString());
        log.info("[getRealPaperList]  call resource [getNotDownloadedPaper] result={} ", JsonMapper.nonDefaultMapper().toJson(resTfulResult));
        if(resTfulResult.getData() != null){
            LinkedHashMap linkedHashMap = (LinkedHashMap) resTfulResult.getData();
            //PageList<RealPaper> pagePaperList = new PageList<RealPaper>((List<RealPaper>)linkedHashMap.get("list"),(int)linkedHashMap.get("total"),(int)linkedHashMap.get("pages"), (int)linkedHashMap.get("pageNum"), (int)linkedHashMap.get("pageSize"));
            PageList<RealPaper> pagePaperList = new PageList<RealPaper>((List<RealPaper>)linkedHashMap.get("list"),linkedHashMap);
            return pagePaperList;
        }
        return null;
    }

    @Override
    public List<com.cunw.cloud.resource.model.Question> getPaperDetailInfo(String paperId) {
        StringBuilder resourceJson = new StringBuilder();
        resourceJson.append("{\"paperId\":").append("\"").append(paperId).append("\"").append("}");
        //获取资源- 试卷题目详情
        List<com.cunw.cloud.resource.model.Question> questionList = resourceGatewayHandle.postList("getRealPaperDetail", resourceJson.toString(), com.cunw.cloud.resource.model.Question.class);
        return questionList;
    }

    public List<AnswerDTO> getAnswerList(List<String> questionIdList){
        Map<String,String> param =new HashMap<>();
        param.put("qustionStr", JsonMapper.nonEmptyMapper().toJson(questionIdList));
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        answerDTOList = ServerRestTemplate.getList(resourceServerUrl + "/resource/questiondetails/questionanswers", AnswerDTO.class, param);
        return answerDTOList;
    }

    @Override
    public PageList<RealPaper> getMyPaper(String subjectId, Integer pageNum, Integer pageSize) {

        StringBuilder platJson = new StringBuilder();
        platJson.append("{\"userCode\":").append("\"").append(this.getCurrUser().getLoginName()).append("\"").append(",\"subjectId\":").append("\"").append(subjectId).append("\"").append(",\"pageNum\":").append(pageNum).append(",\"pageSize\":").append(pageSize).append("}");

        //获取已下载真题试卷
        RESTfulResult resTfulResult = platGatewayHandle.post("getPageRealPapers", platJson.toString());

        if(resTfulResult.getData() != null){
            LinkedHashMap linkedHashMap = (LinkedHashMap) resTfulResult.getData();
            List<LinkedHashMap> myPaperList = (List<LinkedHashMap>) linkedHashMap.get("list");
            if(CollectionUtils.isNotEmpty(myPaperList)){
                StringBuilder paperIds = new StringBuilder();
                StringBuilder subjectIds = new StringBuilder();
                for(int i = 0; i < myPaperList.size(); i++){
                    if(i == myPaperList.size() -1){
                        paperIds.append(myPaperList.get(i).get("paperId"));
                        subjectIds.append(myPaperList.get(i).get("subjectId"));
                    }else{
                        paperIds.append(myPaperList.get(i).get("paperId")).append(",");
                        subjectIds.append(myPaperList.get(i).get("subjectId")).append(",");
                    }
                }
                String dirId = ServerRestTemplate.get(resourceServerUrl + "/resource/dirs/queryDirId/"+subjectIds.toString(), String.class);

                log.info("===="+JsonMapper.nonEmptyMapper().toJson(dirId));
                StringBuilder resourceJson = new StringBuilder();
                resourceJson.append("{\"paperIds\":").append("\"").append(paperIds.toString()).append("\"").append(",\"subjecId\":").append("\"").append(dirId).append("\"").append("}");

                //获取试卷题目数量价格及下载次数
                List<RealPaper> realPaperList = resourceGatewayHandle.postList("getPaperInfo", resourceJson.toString(), RealPaper.class);
                if(CollectionUtils.isNotEmpty(realPaperList)){
                    PageList<RealPaper> pagePaperList = new PageList<RealPaper>(realPaperList, linkedHashMap);
                    return pagePaperList;
                }
            }
        }
        return new PageList();
    }

    public RESTfulResult getDownloadPaperUrl(String paperId){
        RESTfulResult result = new RESTfulResult();
        //检查是否有下载权限(调用平台)
        StringBuilder platJson = new StringBuilder();
        platJson.append("{\"userCode\":").append("\"").append(this.getCurrUser().getLoginName()).append("\"");
        platJson.append(", \"paperId\":\"").append(paperId).append("\"").append("}");
        log.info("[getRealPaperList]  call plat [GetRealPapersProcessService] param={} ", platJson.toString());
        List<MyPaper> myPaperList = platGatewayHandle.postList("getRealPapers", platJson.toString(), MyPaper.class);
        if(CollectionUtils.isNotEmpty(myPaperList)){
            boolean isExist = myPaperList.stream().anyMatch(x -> paperId.equals(x.getPaperId()));
            if(isExist){
                //导出试卷
                StringBuilder platJson1 = new StringBuilder();
                platJson1.append("{\"paperId\":\"").append(paperId).append("\"").append("}");
                log.info("[getDownloadPaperUrlList]  call plat [getDownloadPaperUrlList] param={} ", platJson.toString());
                String url = resourceGatewayHandle.post("GetDownloadPaperUrl", platJson1.toString(), String.class);
                if(StringUtils.isNotEmpty(url)){
                    DownLoadFileDTO downLoadFileDTO = new DownLoadFileDTO(StringUtils.substringAfterLast(url,"/"), StringUtils.substringAfterLast(url,"."), resourceUrl + url);
                    result.setData(downLoadFileDTO);
                }else{
                    result.setCode("1");
                    result.setMessage("下载失败");
                }
                return result;
            }else{
                result.setCode("-1");
                result.setMessage("未下载");
                return result;
            }
        }
        result.setMessage("未购买");
        result.setCode("-1");
        return result;
    }

}
