package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.familydesk.common.model.SysSchool;
import com.cunw.cloud.familydesk.sysmanage.service.ISysSchoolService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStudentService;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.familydesk.common.model.SysStudent;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassAssessmentReport RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-12-22 09:36
 * 类描述
 * 修订历史：
 * 日期：2018-12-26
 * 作者：generator
 * 参考：
 * 描述：ClassAssessmentReport RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/classAssessmentReport")
public class ClassAssessmentReportController extends BaseRESTfulController {


    @Autowired
    ISysStudentService sysStudentService;

    @Autowired
    ISysSchoolService sysSchoolService;



    @ApiOperation(value="获取班级考评排名列表")
    @GetMapping(value = "/assessmentRankList")
    public RESTfulResult assessmentRankList(String studentId, String startDate
            , String endDate){
        SysStudent sysStudent = sysStudentService.selectByPrimaryKey(studentId);
        List list = null;
        if(sysStudent != null
                && sysStudent.getSyncFlag() == 1){
            SysSchool sysSchool = sysSchoolService.getSysSchoolBySchoolCode(sysStudent.getSyncSchoolCode());
            if(sysSchool != null){
                Map<String,Object> paraMap = Maps.newHashMap();
                paraMap.put("classCode",sysStudent.getSyncClassCode());
                paraMap.put("startDate",startDate);
                paraMap.put("endDate",endDate);

                String url = sysSchool.getUrl()+"v1/classAssessmentReport/assessmentRankList";
                list= ServerRestTemplate.getList(url, HashMap.class,paraMap);
            }
        }
        return success(list);
    }


    @ApiOperation(value="获取班级评分根据考评模板分组列表")
    @GetMapping(value = "/assessmentForClass")
    public RESTfulResult assessmentForClass(String studentId, String startDate
            , String endDate){
        SysStudent sysStudent = sysStudentService.selectByPrimaryKey(studentId);
        Map<String,Object> dataMap = null;
        if(sysStudent != null
                && sysStudent.getSyncFlag() == 1){
            SysSchool sysSchool = sysSchoolService.getSysSchoolBySchoolCode(sysStudent.getSyncSchoolCode());
            if(sysSchool != null){
                Map<String,Object> paraMap = Maps.newHashMap();
                paraMap.put("classCode",sysStudent.getSyncClassCode());
                paraMap.put("startDate",startDate);
                paraMap.put("endDate",endDate);
                paraMap.put("studentCode",sysStudent.getSyncStudentCode());
                String url = sysSchool.getUrl()+"v1/classAssessmentReport/assessmentForClass";
                dataMap= ServerRestTemplate.get(url, HashMap.class,paraMap);
            }
        }
        return success(dataMap);
    }


    @ApiOperation(value="根据考评类型获取详细列表", notes="分页查询")
    @GetMapping(value = "/assessmentForDetailList")
    public RESTfulResult assessmentForDetailList(String studentId, String templateType, Integer pageSize, Integer pageNum,
                                                 String startDate, String endDate) throws Exception{

        SysStudent sysStudent = sysStudentService.selectByPrimaryKey(studentId);
        PageList pageList = null;
        if(sysStudent != null
                && sysStudent.getSyncFlag() == 1){
            SysSchool sysSchool = sysSchoolService.getSysSchoolBySchoolCode(sysStudent.getSyncSchoolCode());
            if(sysSchool != null){
                Map<String,Object> paraMap = Maps.newHashMap();
                paraMap.put("classCode",sysStudent.getSyncClassCode());
                paraMap.put("startDate",startDate);
                paraMap.put("endDate",endDate);
                paraMap.put("templateType",templateType);
                paraMap.put("pageSize",pageSize);
                paraMap.put("pageNum",pageNum);
                paraMap.put("studentCode",sysStudent.getSyncStudentCode());
                String url = sysSchool.getUrl()+"v1/classAssessmentReport/assessmentForDetailList";
                pageList = ServerRestTemplate.getPageList(url, HashMap.class,paraMap);
            }
        }
        return success(pageList);
    }

    /*
    @ApiOperation(value="班级报表-个人报表")
    @GetMapping(value = "/assessmentForStudent")
    public RESTfulResult assessmentForStudent(String classCode, String startDate, String endDate) throws Exception{
        Map<String,Object> paraMap = Maps.newHashMap();
        paraMap.put("classCode",classCode);
        paraMap.put("startDate",startDate);
        paraMap.put("endDate",endDate);
        List<Map<String,Object>> list = classAssessmentReportService.selectAssessmentForStudent(paraMap);
        return success(list);
    }

    @ApiOperation(value="班级报表-小组报表")
    @GetMapping(value = "/assessmentForGroup")
    public RESTfulResult assessmentForGroup(String classCode, String groupCategoryId, String startDate, String endDate) throws Exception{
        Map<String,Object> paraMap = Maps.newHashMap();
        paraMap.put("classCode",classCode);
        paraMap.put("groupCategoryId",groupCategoryId);
        paraMap.put("startDate",startDate);
        paraMap.put("endDate",endDate);
        List<Map<String,Object>> list = classAssessmentReportService.selectAssessmentForGroup(paraMap);
        return success(list);
    }
    */
}
