package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.model.SysApplicationUpdateRecord;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationUpdateRecordService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * SysApplicationUpdateRecord RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationUpdateRecord RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysApplicationUpdateRecords")
public class SysApplicationUpdateRecordController extends BaseRESTfulController {

    @Autowired
    ISysApplicationUpdateRecordService sysApplicationUpdateRecordService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("sysapplicationupdaterecord:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<SysApplicationUpdateRecord> list = sysApplicationUpdateRecordService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建应用", notes="根据SysApplicationUpdateRecord对象和imgFileIds创建")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute SysApplicationUpdateRecord model){
        SysApplicationUpdateRecord sysApplicationUpdateRecord = sysApplicationUpdateRecordService.add(model);
        return success(sysApplicationUpdateRecord);
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("sysapplicationupdaterecord:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        SysApplicationUpdateRecord sysApplicationUpdateRecord = sysApplicationUpdateRecordService.getById(id);
        return success(sysApplicationUpdateRecord);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的sysApplicationUpdateRecord信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "sysApplicationUpdateRecord", value = "详细实体sysApplicationUpdateRecord", required = true, dataType = "SysApplicationUpdateRecord")
    })
    @RequiresPermissions("sysapplicationupdaterecord:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody SysApplicationUpdateRecord model){
        model.setId(id);
        SysApplicationUpdateRecord sysApplicationUpdateRecord = sysApplicationUpdateRecordService.modify(model);
        return success(sysApplicationUpdateRecord);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("sysapplicationupdaterecord:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            sysApplicationUpdateRecordService.delete(id);
        } else {
            sysApplicationUpdateRecordService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }

}
