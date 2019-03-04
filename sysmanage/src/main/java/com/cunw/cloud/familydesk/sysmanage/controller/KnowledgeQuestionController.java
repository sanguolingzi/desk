package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.model.KnowledgeQuestion;
import com.cunw.cloud.familydesk.sysmanage.service.IKnowledgeQuestionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * KnowledgeQuestion RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-14 06:09
 * 类描述
 * 修订历史：
 * 日期：2018-08-14
 * 作者：generator
 * 参考：
 * 描述：KnowledgeQuestion RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/knowledgequestions")
public class KnowledgeQuestionController extends BaseRESTfulController {

    @Autowired
    IKnowledgeQuestionService knowledgeQuestionService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("knowledgequestion:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<KnowledgeQuestion> list = knowledgeQuestionService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据KnowledgeQuestion对象创建")
    @ApiImplicitParam(name = "knowledgeQuestion", value = "详细实体knowledgeQuestion", required = true, dataType = "KnowledgeQuestion")
    @RequiresPermissions("knowledgequestion:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute KnowledgeQuestion model){
//        KnowledgeQuestion knowledgeQuestion = knowledgeQuestionService.add(model);
//        return success(knowledgeQuestion);
        return success();
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("knowledgequestion:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        KnowledgeQuestion knowledgeQuestion = knowledgeQuestionService.getById(id);
        return success(knowledgeQuestion);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的knowledgeQuestion信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "knowledgeQuestion", value = "详细实体knowledgeQuestion", required = true, dataType = "KnowledgeQuestion")
    })
    @RequiresPermissions("knowledgequestion:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody KnowledgeQuestion model){
        model.setId(id);
        KnowledgeQuestion knowledgeQuestion = knowledgeQuestionService.modify(model);
        return success(knowledgeQuestion);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("knowledgequestion:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            knowledgeQuestionService.delete(id);
        } else {
            knowledgeQuestionService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }
}
