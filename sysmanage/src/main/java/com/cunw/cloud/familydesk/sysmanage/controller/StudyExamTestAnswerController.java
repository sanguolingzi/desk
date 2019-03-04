package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.model.StudyExamTestAnswer;
import com.cunw.cloud.familydesk.sysmanage.service.IStudyExamTestAnswerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * StudyExamTestAnswer RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-15 03:11
 * 类描述
 * 修订历史：
 * 日期：2018-08-15
 * 作者：generator
 * 参考：
 * 描述：StudyExamTestAnswer RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@RestController
@RequestMapping("/${api.version}/studyexamtestanswers")
public class StudyExamTestAnswerController extends BaseRESTfulController {

    @Autowired
    IStudyExamTestAnswerService studyExamTestAnswerService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("studyexamtestanswer:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<StudyExamTestAnswer> list = studyExamTestAnswerService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据StudyExamTestAnswer对象创建")
    @ApiImplicitParam(name = "studyExamTestAnswer", value = "详细实体studyExamTestAnswer", required = true, dataType = "StudyExamTestAnswer")
    @RequiresPermissions("studyexamtestanswer:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute StudyExamTestAnswer model){
        StudyExamTestAnswer studyExamTestAnswer = studyExamTestAnswerService.add(model);
        return success(studyExamTestAnswer);
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studyexamtestanswer:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        StudyExamTestAnswer studyExamTestAnswer = studyExamTestAnswerService.getById(id);
        return success(studyExamTestAnswer);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的studyExamTestAnswer信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "studyExamTestAnswer", value = "详细实体studyExamTestAnswer", required = true, dataType = "StudyExamTestAnswer")
    })
    @RequiresPermissions("studyexamtestanswer:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody StudyExamTestAnswer model){
        model.setId(id);
        StudyExamTestAnswer studyExamTestAnswer = studyExamTestAnswerService.modify(model);
        return success(studyExamTestAnswer);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studyexamtestanswer:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            studyExamTestAnswerService.delete(id);
        } else {
            studyExamTestAnswerService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }


    /*@ApiOperation(value="获取相关知识点的题目id集合", notes="")
    @PostMapping(value = "/questionIds")
    public RESTfulResult getQuestionIdsKnowledges(@RequestParam String questId){
        List<String> idList = new ArrayList<String>();
        if(questId.contains(",")){
            String[] idStr = questId.split(",");
            idList = Arrays.asList(idStr);
        }else{
            idList.add(questId);
        }
        List<String> questionIds = studyExamTestAnswerService.getQuesList(idList);
        return success(questionIds);
    }*/

//    @ApiOperation(value="获取本月答题统计详情", notes="根据url的studentCode，subjectId来获取")
//    @GetMapping(value = "/month/test/answer/{studentCode}")
//    public RESTfulResult monthTestAnswerCount(@PathVariable String studentCode){
//        log.info("call monthTestAnswerCount method param studentCode: ({})",studentCode);
//        if(StringUtils.isEmpty(studentCode)){
//            return failed("","参数为空");
//        }
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("studentCode", studentCode);
//        //params.put("subjectId", subjectId);
//
//        List<TestAnswer> testAnswers = studyExamTestAnswerService.getMonthTestAnswerCount(params);
//        log.info("call monthTestAnswerCount method result: ({})", JsonMapper.nonEmptyMapper().toJson(testAnswers));
//        return success(testAnswers);
//    }

}
