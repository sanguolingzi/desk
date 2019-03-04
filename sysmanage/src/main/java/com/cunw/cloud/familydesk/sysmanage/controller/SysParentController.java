package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.familydesk.sysmanage.service.ISysParentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SysParent RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysParent RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysParents")
public class SysParentController extends BaseRESTfulController {

    @Autowired
    ISysParentService sysParentService;

    @ApiOperation(value="调用plat服务 获取家长绑定学生 返回学生的详细信息", notes="分页查询")
    @GetMapping(value = "/getStudentDetail")
    public RESTfulResult list(){
        return success( sysParentService.getSysStudentBind());
    }
}
