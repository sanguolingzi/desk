package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.familydesk.common.model.SysSchool;
import com.cunw.cloud.familydesk.common.model.SysStudent;
import com.cunw.cloud.familydesk.sysmanage.dto.ExamSendInfo;
import com.cunw.cloud.familydesk.sysmanage.service.ISysSchoolService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStudentService;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/${api.version}/examInfo")
public class ExamSendInfoController {

    @Autowired
    ISysStudentService sysStudentService;

    @Autowired
    ISysSchoolService sysSchoolService;

    @GetMapping("/all")
    public PageList<ExamSendInfo> getAll(String stuId, Integer pageNum, Integer pageSize) {
        SysStudent sysStudent = sysStudentService.selectByPrimaryKey(stuId);
        PageList<ExamSendInfo> list = null;
        if (null != sysStudent) {
            SysSchool sysSchool = sysSchoolService.getSysSchoolBySchoolCode(sysStudent.getSyncSchoolCode());
            Map<String, Object> map = new HashMap<>();
            map.put("stuCode", sysStudent.getSyncStudentCode());
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            String url = sysSchool.getUrl() + "v1/class/examsendinfos/all";
            return ServerRestTemplate.getPageList(url, ExamSendInfo.class, map);
        }
        return list;
    }

    @GetMapping("/get")
    public ExamSendInfo getOne(String stuId, String id) {
        SysStudent sysStudent = sysStudentService.selectByPrimaryKey(stuId);
        if (null != sysStudent) {
            SysSchool sysSchool = sysSchoolService.getSysSchoolBySchoolCode(sysStudent.getSyncSchoolCode());
            String url = sysSchool.getUrl() + "v1/class/examsendinfos/" + id;
            return ServerRestTemplate.get(url, ExamSendInfo.class);
        }
        return null;
    }
}
