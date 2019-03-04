package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.exception.ParameterException;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.dto.AnswerParam;
import com.cunw.cloud.familydesk.common.dto.StudyExamTestParam;
import com.cunw.cloud.familydesk.common.dto.StudyExamTestStatisticDTO;
import com.cunw.cloud.familydesk.common.model.StudyExamTest;
import com.cunw.cloud.familydesk.sysmanage.service.IStudyExamTestService;
import com.cunw.cloud.familydesk.sysmanage.service.IStudySmartAssemblyService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * StudyExamTest RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-15 03:10
 * 类描述
 * 修订历史：
 * 日期：2018-08-15
 * 作者：generator
 * 参考：
 * 描述：StudyExamTest RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@RestController
@RequestMapping("/${api.version}/studyexamtests")
public class StudyExamTestController extends BaseRESTfulController {

    @Autowired
    IStudyExamTestService studyExamTestService;

    @Autowired
    IStudySmartAssemblyService studySmartAssemblyService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("studyexamtest:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<StudyExamTest> list = studyExamTestService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据StudyExamTest对象创建")
    @ApiImplicitParam(name = "studyExamTest", value = "详细实体studyExamTest", required = true, dataType = "StudyExamTest")
    @RequiresPermissions("studyexamtest:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute StudyExamTest model){
        StudyExamTest studyExamTest = studyExamTestService.add(model);
        return success(studyExamTest);
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studyexamtest:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        StudyExamTest studyExamTest = studyExamTestService.getById(id);
        return success(studyExamTest);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的studyExamTest信息来更新详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "studyExamTest", value = "详细实体studyExamTest", required = true, dataType = "StudyExamTest")
    })
    @RequiresPermissions("studyexamtest:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody StudyExamTest model){
        model.setId(id);
        StudyExamTest studyExamTest = studyExamTestService.modify(model);
        return success(studyExamTest);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studyexamtest:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            studyExamTestService.delete(id);
        } else {
            studyExamTestService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }

    @ApiOperation(value="保存答题结果", notes="根据StudyExamTestParam对象保存")
    @PostMapping(value = "/studyexamtestanswer")
    public RESTfulResult saveStudyExamTestAnswer(@ModelAttribute StudyExamTestParam studyExamTestParam, @RequestParam String answerStr){
        log.info("call saveStudyExamTestAnswer method studyExamTestParam = {}", answerStr);
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<AnswerParam> answerList = mapper.readValue(answerStr, new TypeReference<List<AnswerParam>>() {});
            studyExamTestParam.setAnswerList(answerList);
            StudyExamTestStatisticDTO studyExamTestStatisticDTO = studyExamTestService.saveStudyExamTest(studyExamTestParam);
            return success(studyExamTestStatisticDTO);
        } catch (IOException e) {
            return failed();
        }
    }

    @ApiOperation(value="查询答题统计", notes="查询答题统计")
    @GetMapping(value = "/{stage}/{studentCode}/papersubjectstatistics")
    public RESTfulResult getPaperSubjectStatistics(@PathVariable String stage, @PathVariable String studentCode){
        if(StringUtils.isEmpty(stage) || StringUtils.isEmpty(studentCode)){
            throw new ParameterException("参数错误");
        }
        return success(studyExamTestService.getPaperStatistics(stage, studentCode));
    }

    @ApiOperation(value="获取测试结果", notes="获取测试结果")
    @GetMapping(value = "/{subjectId}/{studentCode}/testresult")
    public RESTfulResult getPaperTestResult(@PathVariable String subjectId, @PathVariable String studentCode,
                                            @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        if(StringUtils.isEmpty(subjectId) || StringUtils.isEmpty(studentCode)){
            throw new ParameterException("参数错误");
        }
        return success(studyExamTestService.getPaperList(subjectId, studentCode, pageNum, pageSize));
    }

    @ApiOperation(value="获取难度结构分析", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "paperId", value = "paper_Id", required = true, dataType = "String")
    // @RequiresPermissions("studyexamtest:view")
    @GetMapping(value = "/answer/{paperId}")
    public RESTfulResult getAnswerCount(@PathVariable String paperId){
        return success(studyExamTestService.getAnswerCount(paperId));
    }

    /*@ApiOperation(value="获取知识点分析", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "paperId", value = "paper_Id", required = true, dataType = "String")
    // @RequiresPermissions("studyexamtest:view")
    @GetMapping(value = "/knowledgeAnalysis/{paperId}")
    public RESTfulResult getKnowledgeAnalysis(@PathVariable String paperId){
        return success(studyExamTestService.getKnowledgeAnalysis(paperId));
    }*/

    @ApiOperation(value="获取考前冲刺答题详情", notes="获取考前冲刺答题详情")
    @GetMapping(value = "/{testId}/paperdetaillist")
    public RESTfulResult getPaperDetailList(@PathVariable String testId){
        if(StringUtils.isEmpty(testId)){
            throw new ParameterException("参数错误");
        }
        return success(studyExamTestService.getPaperDetailList(testId));
    }

    @ApiOperation(value="获取未下载真题试卷列表", notes="获取未下载真题试卷列表")
    @GetMapping(value = "/paper/subject/{subjectId}")
    public RESTfulResult queryPaperInfoList(@PathVariable String subjectId, Integer pageNum, Integer pageSize){
        if(StringUtils.isEmpty(subjectId)){
            throw new ParameterException("参数错误");
        }
        return success(studyExamTestService.getRealPaperList(subjectId, pageNum, pageSize));
    }

	@ApiOperation(value="获取试卷下载路径", notes="获取试卷下载路径")
    @GetMapping(value = "/downloadpaperurl/{paperId}")
    public RESTfulResult downloadpaperurl(@PathVariable String paperId){
        if(StringUtils.isEmpty(paperId)){
            throw new ParameterException("参数错误");
        }
        return studyExamTestService.getDownloadPaperUrl(paperId);
    }

    @ApiOperation(value="获取我的试卷列表", notes="获取我的试卷列表")
    @GetMapping(value = "/mypaper/{subjectId}")
    public RESTfulResult getMyPaper(@PathVariable String subjectId, Integer pageNum, Integer pageSize){
        if(StringUtils.isEmpty(subjectId)){
            throw new ParameterException("参数错误");
        }
        return success(studyExamTestService.getMyPaper(subjectId, pageNum, pageSize));
    }

	@ApiOperation(value="获取真题试卷题目详情", notes="获取真题试卷题目详情")
    @GetMapping(value = "/paper/detail/{paperId}")
    public RESTfulResult queryPaperDetailInfo(@PathVariable String paperId){
        if(StringUtils.isEmpty(paperId)){
            throw new ParameterException("参数错误");
        }
        return success(studyExamTestService.getPaperDetailInfo(paperId));
    }
}
