package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.exception.ParameterException;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.framework.utils.mapper.JsonMapper;
import com.cunw.cloud.familydesk.common.dto.StudyTestDTO;
import com.cunw.cloud.familydesk.common.dto.StudyTestParam;
import com.cunw.cloud.familydesk.common.dto.TestQuestion;
import com.cunw.cloud.familydesk.common.model.StudyTest;
import com.cunw.cloud.familydesk.sysmanage.service.IStudyTestService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * StudyTest RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：StudyTest RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@RestController
@RequestMapping("/${api.version}/study/tests")
public class StudyTestController extends BaseRESTfulController {

    @Autowired
    IStudyTestService studyTestService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("studytest:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<StudyTest> list = studyTestService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据StudyTest对象创建")
    @ApiImplicitParam(name = "studyTest", value = "详细实体studyTest", required = true, dataType = "StudyTest")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute StudyTest model){
        StudyTest studyTest = studyTestService.add(model);
        return success(studyTest);
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studytest:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        StudyTest studyTest = studyTestService.getById(id);
        return success(studyTest);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的studyTest信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "studyTest", value = "详细实体studyTest", required = true, dataType = "StudyTest")
    })
    @RequiresPermissions("studytest:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody StudyTest model){
        model.setId(id);
        StudyTest studyTest = studyTestService.modify(model);
        return success(studyTest);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studytest:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            studyTestService.delete(id);
        } else {
            studyTestService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentCode", value = "学生编码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "测验类型(0 教材，1 知识点)", required = false, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "章节或知识点id", required = false, dataType = "String")
    })
    @GetMapping(value = "/questions")
    public RESTfulResult getTestQuestion(String studentCode, Integer type, String subjectId, String id){
        if(StringUtils.isEmpty(studentCode) || type == null || StringUtils.isEmpty(subjectId) || StringUtils.isEmpty(id)){
            throw new ParameterException("参数为空");
        }
        List<TestQuestion> testQuestionList = studyTestService.getTestQuestion(studentCode, type, subjectId, id);
        return success(testQuestionList);
    }

    @ApiOperation(value="保存测验结果接口", notes="保存测验结果接口")
    @PostMapping(value = "")
    public RESTfulResult test(@Valid @ModelAttribute StudyTestParam param){
        List<Map> list = JsonMapper.nonEmptyMapper().fromJson(param.getAnswers(), JsonMapper.nonEmptyMapper().contructCollectionType(List.class, Map.class));
        StudyTestDTO studyTestDTO = studyTestService.addTest(param.getStudentCode(), param.getType(), param.getId(), param.getRank(), param.getUseTime(), list);
        return success(studyTestDTO);
    }

//    @ApiOperation(value="获取测验详情接口", notes="获取测验详情接口")
//    @GetMapping(value = "/{type}/{studentCode}/{id}/{rank}")
//    public RESTfulResult getTest(@PathVariable(name = "studentCode") String studentCode, @PathVariable(name = "type") Integer type, @PathVariable(name = "id") String id, @PathVariable(name = "rank") Integer rank){
//        log.info("[getTest] type={}, studentCode={}, id={}, rank={}", type, studentCode, id, rank);
//        return success(studyTestService.getTest(studentCode, type, id, rank));
//    }

    @ApiOperation(value="获取测验详情接口", notes="获取测验详情接口")
    @GetMapping(value = "/detail/{testId}")
    public RESTfulResult getTest(@PathVariable String testId){
        log.info("[getTest] testId={}", testId);
        return success(studyTestService.getTestDetail(testId));
    }

    @ApiOperation(value="获取测验的星级总和", notes="获取测验的星级总和")
    @GetMapping(value = "/sum/star/{studentCode}")
    public RESTfulResult getTestStar(@PathVariable String studentCode){
        log.info("call [getTestStar] studentCode={}", studentCode);
        return success(studyTestService.getTestStar(studentCode));
    }
}
