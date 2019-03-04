package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.common.model.SysStudentParent;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStudentParentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SysStudentParent RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:59
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysStudentParent RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysStudentParents")
public class SysStudentParentController extends BaseRESTfulController {

    @Autowired
    ISysStudentParentService sysStudentParentService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @GetMapping(value = "/")
    public RESTfulResult list(Integer pageNum, Integer pageSize){
        PageList<SysStudentParent> list = sysStudentParentService.queryForPage(null, pageNum, pageSize);
        return success(list);
    }
}
