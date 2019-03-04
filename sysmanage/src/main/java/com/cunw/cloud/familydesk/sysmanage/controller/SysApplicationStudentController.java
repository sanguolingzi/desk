package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.familydesk.common.model.SysApplicationCenterVO;
import com.cunw.cloud.familydesk.json.annotation.JSON;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationCenterService;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.model.SysApplicationStudent;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationStudentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * SysApplicationStudent RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationStudent RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysApplicationStudents")
public class SysApplicationStudentController extends BaseRESTfulController {

    @Autowired
    ISysApplicationStudentService sysApplicationStudentService;

    @Autowired
    ISysApplicationCenterService applicationCenterService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @GetMapping(value = "/")
    @JSON(type=SysApplicationCenterVO.class,filter = "fileId")
    public RESTfulResult list(String studentId,Integer pageNum, Integer pageSize) throws Exception{
        if(StringUtils.isEmpty(studentId)){
            throw new MissingServletRequestParameterException("studentId","");
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("studentId",studentId);
        PageList<SysApplicationCenterVO> list= sysApplicationStudentService.selectForPage(parameters, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据SysApplicationStudent对象创建")
    @ApiImplicitParam(name = "sysApplicationStudent", value = "详细实体sysApplicationStudent", required = true, dataType = "SysApplicationStudent")
    @RequiresPermissions("sysapplicationstudent:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute SysApplicationStudent model){
        SysApplicationStudent sysApplicationStudent = sysApplicationStudentService.add(model);
        return success(sysApplicationStudent);
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("sysapplicationstudent:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        SysApplicationStudent sysApplicationStudent = sysApplicationStudentService.getById(id);
        return success(sysApplicationStudent);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的sysApplicationStudent信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "sysApplicationStudent", value = "详细实体sysApplicationStudent", required = true, dataType = "SysApplicationStudent")
    })
    @RequiresPermissions("sysapplicationstudent:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody SysApplicationStudent model){
        model.setId(id);
        SysApplicationStudent sysApplicationStudent = sysApplicationStudentService.modify(model);
        return success(sysApplicationStudent);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("sysapplicationstudent:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            sysApplicationStudentService.delete(id);
        } else {
            sysApplicationStudentService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }

    @ApiOperation(value="卸载应用", notes="卸载应用")
    @PostMapping(value = "/uninstallApp")
    public RESTfulResult uninstallApp(@ModelAttribute SysApplicationStudent sysApplicationStudent) throws Exception{
        sysApplicationStudentService.uninstallApp(sysApplicationStudent);
        return success("success");
    }

}
