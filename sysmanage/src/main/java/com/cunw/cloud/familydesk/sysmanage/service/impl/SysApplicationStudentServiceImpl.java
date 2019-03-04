package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.common.model.SysApplicationCenterVO;
import com.cunw.cloud.framework.exception.BusinessException;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysApplicationStudent;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysApplicationStudentMapper;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationStudentService;
import com.cunw.cloud.framework.service.pagehelper.PageHelper;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import com.cunw.cloud.framework.utils.misc.IdGenerator;
import com.google.common.collect.Maps;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;
import java.util.Map;

/**
 * SysApplicationStudent服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationStudent服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysApplicationStudentService")
public class SysApplicationStudentServiceImpl extends BaseServiceImpl<SysApplicationStudent, String> implements ISysApplicationStudentService {

    @Autowired
    private SysApplicationStudentMapper sysApplicationStudentMapper;
    @Autowired
    private IdGenerator idGenerator;

    @Value("${server.static.url}")
    private String  serverPath;

    @Override
    protected IBaseMapper<SysApplicationStudent, String> getMapper() {
        return sysApplicationStudentMapper;
    }

    @Override
    public PageList<SysApplicationCenterVO> selectForPage(Map<String, Object> parameters, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        parameters.put("serverPath",serverPath);
        List<SysApplicationCenterVO> rows = sysApplicationStudentMapper.selectForPage(parameters);
        PageList pageInfo = new PageList(rows);
        return pageInfo;
    }

    public void addApp(String appId, String studentId){
        //未删除状态
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("appId",appId);
        parameters.put("studentId",studentId);
        parameters.put("status",1);
        List<SysApplicationStudent> applicationStudents=sysApplicationStudentMapper.selectByParameters(parameters);
        if(applicationStudents.size()>0){
            return;
        }
        SysApplicationStudent  applicationStudent=new SysApplicationStudent();
        applicationStudent.setAppStuId(idGenerator.getNextStr());
        applicationStudent.setAppId(appId);
        applicationStudent.setStudentId(studentId);
        applicationStudent.setDoEvaluate(1);
        applicationStudent.setDeskId("");
        sysApplicationStudentMapper.insertSelective(applicationStudent);
    }

    @Override
    public boolean uninstallApp(SysApplicationStudent sysApplicationStudent) throws Exception {
        if(StringUtils.isNullOrEmpty(sysApplicationStudent.getAppId()))
            throw new MissingServletRequestParameterException("appId","");

        if(StringUtils.isNullOrEmpty(sysApplicationStudent.getStudentId()))
            throw new MissingServletRequestParameterException("studentId","");

        int result = sysApplicationStudentMapper.updateByAppIdAndStudentId(sysApplicationStudent);
        if(result > 0)
            return true;
        throw new BusinessException("删除学生应用关系失败!");
    }
}
