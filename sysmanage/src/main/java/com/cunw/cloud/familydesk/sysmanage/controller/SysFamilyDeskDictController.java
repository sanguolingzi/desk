package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.sysmanage.general.sysdict.model.CodeNameSysDict;
import com.cunw.cloud.sysmanage.general.sysdict.service.ISysDictService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * sysFamilyDeskDict 根据type 查询sysdict
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysDevice RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysFamilyDeskDict")
public class SysFamilyDeskDictController extends BaseRESTfulController {

    @Autowired
    ISysDictService sysDictService;
    @ApiOperation(value="获取应用分类")
    @GetMapping(value = "/getAppCategory")
    public RESTfulResult getAppCategory(){
        List<CodeNameSysDict> list = sysDictService.queryResCodeNames("applicationCategory");
        return success(list);
    }
}
