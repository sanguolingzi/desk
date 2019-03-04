package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.exception.ParameterException;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.model.MaterialMaster;
import com.cunw.cloud.familydesk.sysmanage.service.IMaterialMasterService;
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
 * MaterialMaster RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-08-13 02:19
 * 类描述
 * 修订历史：
 * 日期：2018-08-13
 * 作者：generator
 * 参考：
 * 描述：MaterialMaster RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@RestController
@RequestMapping("/${api.version}/study/materialmasters")
public class MaterialMasterController extends BaseRESTfulController {

    @Autowired
    IMaterialMasterService materialMasterService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @RequiresPermissions("materialmaster:view")
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<MaterialMaster> list = materialMasterService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value="创建", notes="根据MaterialMaster对象创建")
    @ApiImplicitParam(name = "materialMaster", value = "详细实体materialMaster", required = true, dataType = "MaterialMaster")
    @RequiresPermissions("materialmaster:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute MaterialMaster model){
        //MaterialMaster materialMaster = materialMasterService.add(model);
        //return success(materialMaster);
        return success();
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("materialmaster:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id){
        MaterialMaster materialMaster = materialMasterService.getById(id);
        return success(materialMaster);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的materialMaster信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
        @ApiImplicitParam(name = "materialMaster", value = "详细实体materialMaster", required = true, dataType = "MaterialMaster")
    })
    @RequiresPermissions("materialmaster:update")
    @PutMapping(value = "/{id}", produces  = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody MaterialMaster model){
        model.setId(id);
        MaterialMaster materialMaster = materialMasterService.modify(model);
        return success(materialMaster);
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("materialmaster:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id){
        if (StringUtils.indexOf(id, ",") == -1) {
            materialMasterService.delete(id);
        } else {
            materialMasterService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentCode", value = "学生编码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pDirId", value = "父目录（上级目录）", required = false, dataType = "String")
    })
    @GetMapping(value = "/chapters")
    public RESTfulResult queryChapters(String studentCode, String pDirId){
        log.info("call [ queryChapters ] sutdentCode={}, pDirID={}", studentCode, pDirId);
        if (StringUtils.isEmpty(studentCode)){
            throw new ParameterException("学生编号不能为空");
        }
        /*if (StringUtils.isEmpty(pDirId)){
            throw new ParameterException("上级id不能为空");
        }*/
        return success(materialMasterService.queryChapters(studentCode, pDirId));
    }

}
