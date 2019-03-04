package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.model.StudyTestAnswer;
import com.cunw.cloud.familydesk.sysmanage.service.IStudyTestAnswerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * StudyTestAnswer RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：StudyTestAnswer RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/studytestanswers")
public class StudyTestAnswerController extends BaseRESTfulController {

    @Autowired
    IStudyTestAnswerService studyTestAnswerService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("studytestanswer:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<StudyTestAnswer> list = studyTestAnswerService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据StudyTestAnswer对象创建")
    @ApiImplicitParam(name = "studyTestAnswer", value = "详细实体studyTestAnswer", required = true, dataType = "StudyTestAnswer")
    @RequiresPermissions("studytestanswer:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute StudyTestAnswer model){
        StudyTestAnswer studyTestAnswer = studyTestAnswerService.add(model);
        return success(studyTestAnswer);
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studytestanswer:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        StudyTestAnswer studyTestAnswer = studyTestAnswerService.getById(id);
        return success(studyTestAnswer);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的studyTestAnswer信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "studyTestAnswer", value = "详细实体studyTestAnswer", required = true, dataType = "StudyTestAnswer")
    })
    @RequiresPermissions("studytestanswer:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody StudyTestAnswer model){
        model.setId(id);
        StudyTestAnswer studyTestAnswer = studyTestAnswerService.modify(model);
        return success(studyTestAnswer);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("studytestanswer:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            studyTestAnswerService.delete(id);
        } else {
            studyTestAnswerService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }
}
