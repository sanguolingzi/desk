package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.familydesk.sysmanage.service.ISysAppBindResultService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * SysAppBindResult RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-22 03:41
 * 类描述
 * 修订历史：
 * 日期：2019-01-22
 * 作者：generator
 * 参考：
 * 描述：SysAppBindResult RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysAppBindResults")
public class SysAppBindResultController extends BaseRESTfulController {

    @Autowired
    ISysAppBindResultService sysAppBindResultService;


    @ApiOperation(value="获取App绑定结果")
    @GetMapping(value = "/getAppBindResult")
    public RESTfulResult get(@RequestParam("deviceNo") String deviceNo){
        return success(sysAppBindResultService.getAppBindResult(deviceNo));
    }

}
