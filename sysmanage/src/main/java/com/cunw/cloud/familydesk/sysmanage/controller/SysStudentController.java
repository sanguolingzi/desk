package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.familydesk.common.model.*;
import com.cunw.cloud.familydesk.sysmanage.pojo.StudentInfoPOJO;
import com.cunw.cloud.familydesk.sysmanage.service.IStudyExamTestAnswerService;
import com.cunw.cloud.familydesk.sysmanage.service.IStudyTestService;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStudentService;
import com.cunw.cloud.framework.exception.ParameterException;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.resource.dto.ResourcesParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysStudent RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:59
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysStudent RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@RestController
@RequestMapping("/${api.version}/sysStudents")
public class SysStudentController extends BaseRESTfulController {

    @Autowired
    ISysStudentService sysStudentService;

    @Autowired
    IStudyExamTestAnswerService studyExamTestAnswerService;

    @Autowired
    IStudyTestService studyTestService;

    @PostMapping(value = "/add")
    public RESTfulResult add(@Valid @RequestBody StudentInfoPOJO studentInfo) throws Exception{
        SysStudentVO sysStudent = sysStudentService.save(studentInfo);
        return success(sysStudent);
    }

    @DeleteMapping(value = "/del/{id}")
    public RESTfulResult del(@PathVariable String id){
        sysStudentService.updateStatus(id);
        return success();
    }

    @PutMapping("/update")
    public RESTfulResult update(@RequestBody StudentInfoPOJO studentInfo){
        SysStudent sysStudent = sysStudentService.updateInfo(studentInfo);
        return success(sysStudent);
    }

    @GetMapping("/get/{id}")
    public RESTfulResult get(@PathVariable String id){
        SysStudent sysStudent = sysStudentService.getById(id);
        return success(sysStudent);
    }

    /**
     * 根据家长信息查所有的孩子信息
     * @param id
     * @return
     */
    @GetMapping("/all/{id}")
    public RESTfulResult all(@PathVariable String id){
        List<SysStudentVO> list = sysStudentService.getAllSduByParentId(id);
        return success(list);
    }

    /**
     * 手动绑定学生信息
     * @param bindStudentInfo
     * @return
     */
    @ApiOperation(value="手动绑定学生信息")
    @PostMapping("/bindStudentInfo")
    public RESTfulResult bindStudentInfo(@Valid @RequestBody BindStudentInfo bindStudentInfo){
        return success(sysStudentService.bindStudentInfo(bindStudentInfo));
    }

    /**
     * 解除绑定学生信息
     * 由App发起 后续需要完善用户身份信息
     * @param parentId
     * @param studentId
     * @return
     */
    @ApiOperation(value="解除绑定学生信息")
    @PostMapping("/unBindStudentInfo")
    public RESTfulResult unBindStudentInfo(@RequestParam("parentId") String parentId, @RequestParam("studentId") String studentId)
    throws Exception{
        if(StringUtils.isEmpty(parentId))
            throw new MissingServletRequestParameterException("家长id","");

        if(StringUtils.isEmpty(studentId))
            throw new MissingServletRequestParameterException("学生id","");

        return success(sysStudentService.unBindStudentInfo(parentId,studentId));
    }


    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stage", value = "学段", required = false, dataType = "String"),
            @ApiImplicitParam(name = "subjectId", value = "科目ID", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "versionId", value = "版本ID", required = false, dataType = "String")
    })
    @GetMapping(value = "/resource/cloudfiles")
    public RESTfulResult queryCloudfile(@ModelAttribute ResourcesParam para){
        if(StringUtils.isEmpty(para.getDirId()))
            throw new ParameterException("dirId不能为空！");
        Map<String, Object> params = new HashMap<>(9);
        params.put("dirId", para.getDirId());
        params.put("type", para.getType());
        params.put("fileType", para.getFileType());
        params.put("title", para.getTitle());
        params.put("pageNum", para.getPageNum());
        params.put("pageSize", para.getPageSize());
        return success(sysStudentService.queryCloudfile(getCurrUser().getLoginName(),params));
    }

    @ApiOperation(value="获取本月答题统计详情", notes="根据url的studentCode，subjectId来获取")
    @GetMapping(value = "/{studentCode}/statistics/answermonth")
    public RESTfulResult monthTestAnswerCount(@PathVariable String studentCode){
        log.info("call monthTestAnswerCount method param studentCode: ({})",studentCode);
        if(StringUtils.isEmpty(studentCode)){
            return failed("","参数为空");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("studentCode", studentCode);
        //params.put("subjectId", subjectId);

        List<TestAnswer> testAnswers = studyExamTestAnswerService.getMonthTestAnswerCount(params);
        log.info("call monthTestAnswerCount method result: ({})", JsonMapper.nonEmptyMapper().toJson(testAnswers));
        return success(testAnswers);
    }

    @ApiOperation(value="答题难易度统计", notes="根据url的studentCode来统计")
    @GetMapping(value = "/{studentCode}/statistics/learningprogress")
    public RESTfulResult questionDifficultCount(@PathVariable String studentCode, String subjectId){
        log.info("call questionDifficultCount method param studentCode: ({}), subjectId: ({})", studentCode, subjectId);
        if(StringUtils.isEmpty(studentCode)){
            return failed("","参数为空");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("studentCode", studentCode);
        params.put("subjectId", subjectId);

        //List<QuestionDifficult> questionDifficults = studyExamTestAnswerService.getQuestionDifficultCount(params);
        List<QuestionRankCount> questionDifficults = studyTestService.getQuestionRankCount(params);
        log.info("call questionDifficultCount method result: ({})", JsonMapper.nonEmptyMapper().toJson(questionDifficults));
        return success(questionDifficults);
    }

    @ApiOperation(value="知识点掌握程度统计", notes="根据url的studentCode, subjectId来统计")
    @GetMapping(value = "/{studentCode}/statistics/knowledge/master")
    public RESTfulResult knowledgeMasterCount(@PathVariable String studentCode, String subjectId){
        log.info("call knowledgeMasterCount method param studentCode: ({}), subjectId: ({})", studentCode, subjectId);
        if(StringUtils.isEmpty(studentCode)){
            return failed("","studentCode 参数为空");
        }
        if(StringUtils.isEmpty(subjectId)){
            return failed("","subjectId 参数为空");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("studentCode", studentCode);
        params.put("subjectId", subjectId);

        List<KnowledgeMasterCount> knowledgeMasterCounts = studyTestService.getKnowledgeMasterCount(params);
        log.info("call knowledgeMasterCount method result: ({})", JsonMapper.nonEmptyMapper().toJson(knowledgeMasterCounts));
        return success(knowledgeMasterCounts);
    }

    @ApiOperation(value="知识点排名", notes="分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentCode", value = "学生编码", required = false, dataType = "String"),
    })
    @GetMapping(value = "/statistics/ranking")
    public RESTfulResult queryKnowledgeRanking(@RequestParam String studentCode,@RequestParam String knowledgeId){
        return success(sysStudentService.getQueryKnowledgeRanking(studentCode,knowledgeId));
    }
}
