package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.familydesk.common.model.AppBindDesk;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.familydesk.sysmanage.service.ISysParentDeviceBindService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SysParentDeviceBind RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysParentDeviceBind RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysParentDeviceBinds")
public class SysParentDeviceBindController extends BaseRESTfulController {

    @Autowired
    ISysParentDeviceBindService sysParentDeviceBindService;


    @ApiOperation(value="App扫码绑定课桌")
    @PostMapping(value = "/appBind")
    public RESTfulResult add(@RequestBody AppBindDesk appBindDesk) throws Exception{
        return success(sysParentDeviceBindService.appBindDesk(appBindDesk));
    }

}
