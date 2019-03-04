package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.exception.ParameterException;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.model.KnowledgeMaster;
import com.cunw.cloud.familydesk.sysmanage.service.IKnowledgeMasterService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * KnowledgeMaster RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：KnowledgeMaster RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/study/knowledgemasters")
public class KnowledgeMasterController extends BaseRESTfulController {

    @Autowired
    IKnowledgeMasterService knowledgeMasterService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("knowledgemaster:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<KnowledgeMaster> list = knowledgeMasterService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据KnowledgeMaster对象创建")
    @ApiImplicitParam(name = "knowledgeMaster", value = "详细实体knowledgeMaster", required = true, dataType = "KnowledgeMaster")
    @RequiresPermissions("knowledgemaster:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute KnowledgeMaster model){
//        KnowledgeMaster knowledgeMaster = knowledgeMasterService.add(model);
//        return success(knowledgeMaster);
          return success();
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("knowledgemaster:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        KnowledgeMaster knowledgeMaster = knowledgeMasterService.getById(id);
        return success(knowledgeMaster);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的knowledgeMaster信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "knowledgeMaster", value = "详细实体knowledgeMaster", required = true, dataType = "KnowledgeMaster")
    })
    @RequiresPermissions("knowledgemaster:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody KnowledgeMaster model){
        model.setId(id);
        KnowledgeMaster knowledgeMaster = knowledgeMasterService.modify(model);
        return success(knowledgeMaster);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("knowledgemaster:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            knowledgeMasterService.delete(id);
        } else {
            knowledgeMasterService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentCode", value = "学生编码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "stage", value = "学段", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "subjectId", value = "学科", required = false, dataType = "String"),
            @ApiImplicitParam(name = "upid", value = "父目录（上级目录）", required = false, dataType = "String")
    })
    @GetMapping(value = "/knowledges")
    public RESTfulResult queryKnowledges(String studentCode, String subjectId, String upid){
        if (StringUtils.isEmpty(studentCode)){
            throw new ParameterException("学生编号不能为空");
        }
        /*if (StringUtils.isEmpty(subjectId)) {
            throw new ParameterException("学科不能为空！");
        }*/
        return success(knowledgeMasterService.queryKnowledges(studentCode, subjectId, upid));
    }
}
