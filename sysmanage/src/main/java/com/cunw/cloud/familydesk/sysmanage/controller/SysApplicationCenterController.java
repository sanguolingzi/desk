package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.familydesk.common.model.*;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationDataService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationUpdateRecordService;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationCenterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * SysApplicationCenter RESTful接口：
 *
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationCenter RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysApplicationCenters")
public class SysApplicationCenterController extends BaseRESTfulController {

    @Autowired
    ISysApplicationCenterService sysApplicationCenterService;

    @Autowired
    ISysApplicationUpdateRecordService sysApplicationUpdateRecordService;

    @Autowired
    ISysApplicationDataService sysApplicationDataService;

    @ApiOperation(value = "获取列表", notes = "分页查询")
    @GetMapping(value = "/list")
    public RESTfulResult list(String stageId, String appCategoryId, String appName,Integer pageNum, Integer pageSize) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stageId", stageId);
        parameters.put("appCategoryId", appCategoryId);
        parameters.put("appName", appName);
        PageList<SysApplicationCenterVO> list = sysApplicationCenterService.selectForPage(parameters, pageNum, pageSize);
        return success(list);
    }

    @ApiOperation(value = "创建", notes = "根据SysApplicationCenter对象创建")
    @PostMapping(value = "/modifyApp")
   public RESTfulResult modifyApp(@Valid SysApplicationUploadVO applicationUploadVO) {
        SysApplicationCenterInfo info = sysApplicationCenterService.modifyApp(applicationUploadVO);
        return success(info);
    }


    @ApiOperation(value = "更新应用版本信息", notes = "根据SysApplicationCenter对象创建")
    @PostMapping(value = "/modifyAppVersion")
    public RESTfulResult modifyAppVersion(String appId,String appFileId,String version,String description) {
         sysApplicationCenterService.setSysApplicationUpdateRecord(appFileId
        ,appId,description,version);
        return success("success");
    }

    @ApiOperation(value = "更新详细信息", notes = "根据url的id来指定更新对象，并根据传过来的sysApplicationCenter信息来更新详细信息")
    @PostMapping(value = "/{id}")
    public RESTfulResult update(@PathVariable String id, SysApplicationCenter model) {
        model.setId(id);
        model.setAppLogo(null);
        SysApplicationCenter sysApplicationCenter = sysApplicationCenterService.modify(model);
        return success(sysApplicationCenter);
    }

    @ApiOperation(value = "获取应用详细信息", notes = "根据url的id来获取详细信息")
    @GetMapping(value = "/getApplicationInfo/{appId}")
    public RESTfulResult getApplicationInfo(@PathVariable String appId)  throws Exception{
        SysApplicationCenter sysApplicationCenter = sysApplicationCenterService.getById(appId);
        if (sysApplicationCenter == null) {
            return failed("请输入正确的应用id");
        }
        SysApplicationCenterInfo sysApplicationCenterInfo = sysApplicationCenterService.getAppInfoById(appId);
        return success(sysApplicationCenterInfo);
    }

}
