package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.model.SysApplicationImg;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationImgService;
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
 * SysApplicationImg RESTful接口：
 *
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationImg RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysApplicationImgs")
public class SysApplicationImgController extends BaseRESTfulController {

    @Autowired
    ISysApplicationImgService sysApplicationImgService;

    @ApiOperation(value = "获取列表", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize) {
        PageList<SysApplicationImg> list = sysApplicationImgService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value = "创建", notes = "根据SysApplicationImg对象创建")
    @ApiImplicitParam(name = "sysApplicationImg", value = "详细实体sysApplicationImg", required = true, dataType = "SysApplicationImg")
    @RequiresPermissions("sysapplicationimg:create")
    @PostMapping(value = "/")
    public RESTfulResult add(@ModelAttribute SysApplicationImg model) {
        SysApplicationImg sysApplicationImg = sysApplicationImgService.add(model);
        return success(sysApplicationImg);
    }

    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("sysapplicationimg:view")
    @GetMapping(value = "/{id}")
    public RESTfulResult get(@PathVariable String id) {
        SysApplicationImg sysApplicationImg = sysApplicationImgService.getById(id);
        return success(sysApplicationImg);
    }

    @ApiOperation(value = "更新详细信息", notes = "根据url的id来指定更新对象，并根据传过来的sysApplicationImg信息来更新详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sysApplicationImg", value = "详细实体sysApplicationImg", required = true, dataType = "SysApplicationImg")
    })
    @RequiresPermissions("sysapplicationimg:update")
    @PutMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public RESTfulResult update(@PathVariable String id, @RequestBody SysApplicationImg model) {
        model.setId(id);
        SysApplicationImg sysApplicationImg = sysApplicationImgService.modify(model);
        return success(sysApplicationImg);
    }

    @ApiOperation(value = "删除", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "String")
    @RequiresPermissions("sysapplicationimg:delete")
    @DeleteMapping(value = "/{id}")
    public RESTfulResult delete(@PathVariable String id) {
        if (StringUtils.indexOf(id, ",") == -1) {
            sysApplicationImgService.delete(id);
        } else {
            sysApplicationImgService.delete(Arrays.asList(id.split(",")));
        }
        return success();
    }
}
