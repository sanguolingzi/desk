package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.familydesk.common.model.SysApplicationStudent;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationStudentService;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.model.SysApplicationData;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationDataService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * SysApplicationData RESTful接口：
 *
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationData RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysApplicationDatas")
public class SysApplicationDataController extends BaseRESTfulController {

    @Autowired
    ISysApplicationDataService sysApplicationDataService;

    @Autowired
    private ISysApplicationStudentService sysApplicationStudentService;

    @ApiOperation(value = "获取列表", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("sysapplicationdata:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize) {
        PageList<SysApplicationData> list = sysApplicationDataService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value = "创建", notes = "根据SysApplicationData对象创建")
    @ApiImplicitParam(name = "sysApplicationData", value = "详细实体sysApplicationData", required = true, dataType = "SysApplicationData")
    @RequiresPermissions("sysapplicationdata:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute SysApplicationData model) {
        SysApplicationData sysApplicationData = sysApplicationDataService.add(model);
        return success(sysApplicationData);
    }

    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("sysapplicationdata:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id) {
        SysApplicationData sysApplicationData = sysApplicationDataService.getById(id);
        return success(sysApplicationData);
    }

    @ApiOperation(value = "更新详细信息", notes = "根据url的id来指定更新对象，并根据传过来的sysApplicationData信息来更新详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sysApplicationData", value = "详细实体sysApplicationData", required = true, dataType = "SysApplicationData")
    })
    @RequiresPermissions("sysapplicationdata:update")
    @PutMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody SysApplicationData model) {
        model.setId(id);
        SysApplicationData sysApplicationData = sysApplicationDataService.modify(model);
        return success(sysApplicationData);
    }

    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("sysapplicationdata:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id) {
        if (StringUtils.indexOf(id, ",") == -1) {
            sysApplicationDataService.delete(id);
        } else {
            sysApplicationDataService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }

    @ApiOperation(value = "更新应用好评信息", notes = "更新应用好评信息")
    @PostMapping(value = "/praise/{appId}")
    public RESTfulResult praise(@PathVariable String appId, String studentId) {
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(studentId)) {
            return failed("请输入好评的应用或者好评的人");
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("studentId", studentId);
        parameters.put("appId", appId);
        SysApplicationStudent applicationStudent = sysApplicationStudentService.getByParams(parameters);
        if (applicationStudent == null) {
            return failed("", "找不到此应用");
        }
        Integer doEvaluate = applicationStudent.getDoEvaluate();
        if (doEvaluate != 1) {
            return failed("", "请勿重复好评");
        }
        sysApplicationDataService.addPraise(appId);
        applicationStudent.setDoEvaluate(0);
        sysApplicationStudentService.modify(applicationStudent);
        return success();

    }

}
