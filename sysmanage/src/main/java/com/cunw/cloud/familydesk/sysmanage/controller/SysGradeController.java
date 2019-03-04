package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.familydesk.common.model.SysStageGradeCache;
import com.cunw.cloud.familydesk.json.annotation.JSON;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.familydesk.sysmanage.service.ISysGradeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * SysGrade RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-25 10:21
 * 类描述
 * 修订历史：
 * 日期：2019-01-25
 * 作者：generator
 * 参考：
 * 描述：SysGrade RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/sysGrades")
public class SysGradeController extends BaseRESTfulController {

    @Autowired
    ISysGradeService sysGradeService;

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @GetMapping(value = "/{stage}/listByStage")
    @JSON(type = SysStageGradeCache.class,include = "gradeCode,gradeName")
    public RESTfulResult list(@PathVariable("stage") String stage){
        return success(sysGradeService.listByStage(stage));
    }

    @ApiOperation(value="获取列表", notes="分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "Integer")
    })
    @GetMapping(value = "/list")
    @JSON(type = SysStageGradeCache.class,include = "gradeCode,gradeName")
    public RESTfulResult list(){
        return success(sysGradeService.list());
    }
}
