package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.exception.ParameterException;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.resource.dto.SmartPaperQuestionParamDTO;
import com.cunw.cloud.familydesk.common.dto.AnswerParam;
import com.cunw.cloud.familydesk.common.model.StudySmartAssembly;
import com.cunw.cloud.familydesk.sysmanage.service.IStudySmartAssemblyService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * StudySmartAssembly RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-17 02:10
 * 类描述
 * 修订历史：
 * 日期：2018-08-17
 * 作者：generator
 * 参考：
 * 描述：StudySmartAssembly RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/studysmartassemblys")
public class StudySmartAssemblyController extends BaseRESTfulController {

    @Autowired
    IStudySmartAssemblyService studySmartAssemblyService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("studysmartassembly:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<StudySmartAssembly> list = studySmartAssemblyService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据StudySmartAssembly对象创建")
    @ApiImplicitParam(name = "studySmartAssembly", value = "详细实体studySmartAssembly", required = true, dataType = "StudySmartAssembly")
    @RequiresPermissions("studysmartassembly:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute StudySmartAssembly model){
        StudySmartAssembly studySmartAssembly = studySmartAssemblyService.add(model);
        return success(studySmartAssembly);
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studysmartassembly:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        StudySmartAssembly studySmartAssembly = studySmartAssemblyService.getById(id);
        return success(studySmartAssembly);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的studySmartAssembly信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "studySmartAssembly", value = "详细实体studySmartAssembly", required = true, dataType = "StudySmartAssembly")
    })
    @RequiresPermissions("studysmartassembly:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody StudySmartAssembly model){
        model.setId(id);
        StudySmartAssembly studySmartAssembly = studySmartAssemblyService.modify(model);
        return success(studySmartAssembly);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studysmartassembly:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            studySmartAssemblyService.delete(id);
        } else {
            studySmartAssemblyService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }

    @ApiOperation(value="保存答题结果", notes="根据StudyExamTestParam对象保存")
    @PostMapping(value = "/studysmartassemblyanswer")
    public RESTfulResult saveStudySmartAssemblyAnswer(@ModelAttribute StudySmartAssembly model, @RequestParam String answerStr){
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<AnswerParam> answerList = mapper.readValue(answerStr, new TypeReference<List<AnswerParam>>() {});
            model.setAnswerList(answerList);
            model.setCrtUserCode(getLoginName());
            return success(studySmartAssemblyService.saveStudySmartAssemblyAnswer(model));
        } catch (IOException e) {
            return failed();
        }
    }

    @ApiOperation(value="获取智能组卷答题详情", notes="获取智能组卷答题详情")
    @GetMapping(value = "/{assemblyId}/paperDetailList")
    public RESTfulResult getPaperDetailList(@PathVariable String assemblyId){
        if(StringUtils.isEmpty(assemblyId)){
            throw new ParameterException("参数错误");
        }
        return success(studySmartAssemblyService.getPaperDetailList(assemblyId));
    }

    @ApiOperation(value="读取组卷的试卷列表", notes="根据试卷目录、试卷类别、创建人分页查询我的试卷列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "dirId", value = "试卷目录", required = false, dataType = "String"),
            @ApiImplicitParam(name = "paperType", value = "试卷类别", required = false, dataType = "String")
    })
    //@RequiresPermissions("paper:view")
    @RequestMapping(value = "/studysmartpaperlist", method = RequestMethod.GET)
    public RESTfulResult getStudySmartPaperList(Integer pageNum, Integer pageSize, String dirId, String paperType, String studentCode){
        return success(studySmartAssemblyService.getAnsweredPaperIdList(pageNum,pageSize,dirId,
                paperType,studentCode,getLoginName()));
    }



    @ApiOperation(value="未答试卷知识点分析", notes="未答试卷知识点分析")
    @GetMapping(value = "/paper/statistics/noanswer")
    public RESTfulResult queryNoAnswerPaperStatistics(@RequestParam String paperId){
        return success(studySmartAssemblyService.getNoAnswerPaperStatistics(paperId));
    }

    @ApiOperation(value="已答试卷知识点分析", notes="已答试卷知识点分析")
    @GetMapping(value = "/paper/statistics/answered")
    public RESTfulResult queryQnsweredPaperStatistics(@RequestParam String paperId, @RequestParam String assemblyId){
        return success(studySmartAssemblyService.getAnsweredPaperStatistics(paperId, assemblyId));
    }

    @ApiOperation(value="根据知识点或章节ID获取题目类型数量", notes="根据知识点或章节ID获取题目类型数量")
    @GetMapping(value = "/questTypeAndCount/{dirIds}")
    public RESTfulResult queryQuestionCountByType(@PathVariable String dirIds, String type, Integer difficult, String questionTypes){
        return success(studySmartAssemblyService.getQuestionCountByType(dirIds, type, difficult, questionTypes));
    }

    @ApiOperation(value="获取智能组卷题库题目", notes="根据题型、题型难度、题型数量组合条件随机抽题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "SmartPaperQuestionParamDTO", value = "目录id", required = false, dataType = "SmartPaperQuestionParamDTO"),
    })
    @RequestMapping(value = "/getSmartPaperQuestion", method = RequestMethod.POST)
    public RESTfulResult listQuestion(@ModelAttribute SmartPaperQuestionParamDTO smartPaperQuestionParamDTO){
        return success(studySmartAssemblyService.getSmartPaperQuestion(smartPaperQuestionParamDTO));
    }

    @ApiOperation(value="根据科目id查询题目类型", notes="根据科目id查询题目类型")
    @ApiImplicitParam(paramType = "path", name = "subjectId", value = "subjectId", required = true, dataType = "Integer")
    @GetMapping(value = "/questType/{subjectId}")
    public RESTfulResult getQuestType(@PathVariable Integer subjectId){
        return success(studySmartAssemblyService.selectByQuestType(subjectId));
    }

}
