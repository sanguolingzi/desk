package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.familydesk.json.annotation.JSON;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.familydesk.common.model.SysStage;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * SysStage RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-25 10:21
 * 类描述
 * 修订历史：
 * 日期：2019-01-25
 * 作者：generator
 * 参考：
 * 描述：SysStage RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysStages")
public class SysStageController extends BaseRESTfulController {

    @Autowired
    ISysStageService sysStageService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @GetMapping(value = "/")
    @JSON(type = SysStage.class,filter = "status")
    public RESTfulResult list(){
        List<SysStage> list = sysStageService.getStageList();
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据SysStage对象创建")
    @ApiImplicitParam(name = "sysStage", value = "详细实体sysStage", required = true, dataType = "SysStage")
    @RequiresPermissions("sysstage:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute SysStage model){
        SysStage sysStage = sysStageService.add(model);
        return success(sysStage);
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("sysstage:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        SysStage sysStage = sysStageService.getById(id);
        return success(sysStage);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的sysStage信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "sysStage", value = "详细实体sysStage", required = true, dataType = "SysStage")
    })
    @RequiresPermissions("sysstage:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody SysStage model){
        model.setId(id);
        SysStage sysStage = sysStageService.modify(model);
        return success(sysStage);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("sysstage:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            sysStageService.delete(id);
        } else {
            sysStageService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }
}
