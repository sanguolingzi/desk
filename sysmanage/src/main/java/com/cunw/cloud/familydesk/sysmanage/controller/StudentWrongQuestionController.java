package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.resource.model.QuestionAnswer;
import com.cunw.cloud.familydesk.common.model.StudentWrongQuestion;
import com.cunw.cloud.familydesk.sysmanage.service.IStudentWrongQuestionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StudentWrongQuestion RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 03:44
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：StudentWrongQuestion RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@RestController
@RequestMapping("/${api.version}/studentwrongquestions")
public class StudentWrongQuestionController extends BaseRESTfulController {

    @Autowired
    IStudentWrongQuestionService studentWrongQuestionService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("studentwrongquestion:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<StudentWrongQuestion> list = studentWrongQuestionService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据StudentWrongQuestion对象创建")
    @ApiImplicitParam(name = "studentWrongQuestion", value = "详细实体studentWrongQuestion", required = true, dataType = "StudentWrongQuestion")
    //@RequiresPermissions("studentwrongquestion:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute StudentWrongQuestion model){
        log.info("call add insertWrongQuestion method param model: ({})", JsonMapper.nonEmptyMapper().toJson(model));
        return studentWrongQuestionService.insertWrongQuestion(model);
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studentwrongquestion:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        StudentWrongQuestion studentWrongQuestion = studentWrongQuestionService.getById(id);
        return success(studentWrongQuestion);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的studentWrongQuestion信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "studentWrongQuestion", value = "详细实体studentWrongQuestion", required = true, dataType = "StudentWrongQuestion")
    })
    @RequiresPermissions("studentwrongquestion:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody StudentWrongQuestion model){
        model.setId(id);
        StudentWrongQuestion studentWrongQuestion = studentWrongQuestionService.modify(model);
        return success(studentWrongQuestion);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    //@RequiresPermissions("studentwrongquestion:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            studentWrongQuestionService.delete(id);
        } else {
            studentWrongQuestionService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }

    @ApiOperation(value="获取科目错题本列表", notes="根据url的studentCode来获取")
    @GetMapping(value = "/subject/{studentCode}")
    public RESTfulResult listQuestions(@PathVariable String studentCode, Integer stage){
        log.info("call subjectlistQuestions method param studentCode={}, stage={}",studentCode, stage);
        if(StringUtils.isEmpty(studentCode)){
            return failed("","参数为空");
        }
        return success(studentWrongQuestionService.getSubjectWrongQuestions(studentCode, stage));
    }

    @ApiOperation(value="获取学科下面纵知识点错题列表", notes="根据url的studentCode，subjectId来获取")
    @GetMapping(value = "/subject/{studentCode}/{subjectId}")
    public RESTfulResult listKnowledgeQuestions(@PathVariable String studentCode, @PathVariable String subjectId, Integer pageNum, Integer pageSize){
        log.info("call listKnowledgeQuestions method param studentCode: ({}), subjectId: ({})",studentCode, subjectId);
        if(StringUtils.isEmpty(studentCode)){
            return failed("","参数为空");
        }
        return success(studentWrongQuestionService.getKnowledgeWrongQuestionListBySubject(studentCode, subjectId, pageNum, pageSize));
    }

    @ApiOperation(value="获取学科下面册别错题列表", notes="根据url的studentCode，subjectId来获取")
    @GetMapping(value = "/subject/book/{studentCode}/{subjectId}")
    public RESTfulResult listBookQuestions(@PathVariable String studentCode, @PathVariable String subjectId){
        log.info("call listBookQuestions method param studentCode: ({}), subjectId: ({})",studentCode, subjectId);
        if(StringUtils.isEmpty(studentCode)){
            return failed("","参数为空");
        }
        return success(studentWrongQuestionService.getSubjectBookWrongQuestions(studentCode, subjectId));
    }

    @ApiOperation(value="获取册别下面知识点错题本列表", notes="根据url的studentCode来获取")
    @GetMapping(value = "/book/knowledge/{studentCode}/{subjectId}/{bookId}")
    public RESTfulResult knowledgeQuestions(@PathVariable String studentCode, @PathVariable String subjectId, @PathVariable String bookId, Integer pageNum, Integer pageSize){
        log.info("call bookKnowledgeQuestions method param studentCode={}, subjectId={}, bookId={}",studentCode, subjectId, bookId);
        if(StringUtils.isEmpty(studentCode)){
            return failed("","参数为空");
        }
        return success(studentWrongQuestionService.getKnowledgeWrongQuestionList(studentCode, subjectId, bookId, pageNum, pageSize));
    }

    @ApiOperation(value="获取知识点错题本详情", notes="根据url的studentCode，knowledgeId获取")
    @GetMapping(value = "/knowledge/view")
    public RESTfulResult viewKnowledgeQuestions(String studentCode, String subjectId, String knowledgeId){
        log.info("call viewKnowledgeQuestions method param studentCode={}, subjectId={}， knowledgeId={}",studentCode, subjectId, knowledgeId);
        if(StringUtils.isEmpty(studentCode)){
            return failed("","参数为空");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("studentCode", studentCode);
        params.put("subjectId", subjectId);
        params.put("knowledgeId", knowledgeId);

        List<QuestionAnswer> questionAnswers = studentWrongQuestionService.getKnowledgeWrongQuestionDetails(params, knowledgeId);

        log.info("call viewKnowledgeQuestions method result: ({})", JsonMapper.nonEmptyMapper().toJson(questionAnswers));
        return success(questionAnswers);
    }

    @ApiOperation(value="获取科目错题本详情", notes="根据url的studentCode，subjectId来获取")
    @GetMapping(value = "/subject/view/{studentCode}/{dirId}")
    public RESTfulResult viewQuestions(@PathVariable String studentCode, @PathVariable String dirId, String startDate, String endDate){
        log.info("call subjectviewQuestions method param studentCode: ({}), dirId: ({}), startDate: ({}), endDate: ({})",studentCode, dirId, startDate, endDate);
        if(StringUtils.isEmpty(studentCode)){
            return failed("","参数为空");
        }
        return success(studentWrongQuestionService.getWrongQuestionDetails(studentCode, dirId, startDate, endDate));
    }

    @ApiOperation(value="添加错题标签", notes="添加错题标签")
    @GetMapping(value = "/tar/{id}")
    public RESTfulResult addTar(@PathVariable String id, String label){
        log.info("call subjectviewQuestions method param studentCode: ({}), dirId: ({}), startDate: ({}), endDate: ({})");
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(label)){
            return failed("","参数为空");
        }
        return studentWrongQuestionService.updateStudentWrongQuestion(id, label);
    }
}
