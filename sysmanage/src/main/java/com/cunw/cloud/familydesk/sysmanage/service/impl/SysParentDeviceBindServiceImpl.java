package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.common.model.*;
import com.cunw.cloud.familydesk.sysmanage.auth.shiro.token.LoginType;
import com.cunw.cloud.familydesk.sysmanage.service.*;
import com.cunw.cloud.familydesk.utils.PinYinUtil;
import com.cunw.cloud.framework.auth.shiro.constant.ShiroConstant;
import com.cunw.cloud.framework.auth.shiro.jwt.JwtUtils;
import com.cunw.cloud.framework.auth.shiro.realm.UserPayload;
import com.cunw.cloud.framework.cache.Caches;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.exception.BusinessException;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysParentDeviceBindMapper;
import com.cunw.cloud.framework.utils.misc.IdGenerator;
import com.cunw.cloud.framework.utils.text.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingServletRequestParameterException;
import com.cunw.cloud.framework.utils.base.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * SysParentDeviceBind服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysParentDeviceBind服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysParentDeviceBindService")
@Slf4j
public class SysParentDeviceBindServiceImpl extends BaseServiceImpl<SysParentDeviceBind, String> implements ISysParentDeviceBindService {

    @Autowired
    private SysParentDeviceBindMapper sysParentDeviceBindMapper;

    @Autowired
    private ISysParentService sysParentService;

    @Autowired
    private ISysStudentService sysStudentService;

    @Autowired
    private ISysStudentParentService sysStudentParentService;

    @Autowired
    private ISysDeviceService sysDeviceService;

    @Autowired
    private ISysAppBindResultService sysAppBindResultService;

    @Override
    protected IBaseMapper<SysParentDeviceBind, String> getMapper() {
        return sysParentDeviceBindMapper;
    }

    /**
     * 整体思想
     * 允许重复扫码 但是不允许关系重复 故这里需要捕获主键或者唯一索引的重复异常
     * @param appBindDesk
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean appBindDesk(AppBindDesk appBindDesk) throws Exception {
        validateParam(appBindDesk);
        log.info(" appBindDeskInfo:"+appBindDesk.toString());
        SysDevice sysDevice =  sysDeviceService.getByProperty("deviceNo",appBindDesk.getDeviceNo());
        if(sysDevice == null)
            throw new BusinessException("设备号不存在!");
        SysParent sysParent = new SysParent();
        try{
            //添加家长
            sysParent.setParentId(appBindDesk.getParentId());
            sysParent.setParentName(appBindDesk.getParentName());
            sysParent.setPhone(appBindDesk.getPhone());
            sysParent.setSex(appBindDesk.getSex());
            sysParentService.addSelective(sysParent);
        }catch (Exception e){//同一个家长多次扫码 家长信息只有一个 这里会有主键重复异常 需要特别处理
            if( e instanceof DuplicateKeyException){
                log.warn(" appBindDesk parent exists  prentId:"+appBindDesk.getParentId());
            }else{
                log.error(" appBindDesk addParent error sysParent:"+sysParent,e);
                throw new BusinessException("绑定设备失败!");
            }
        }

        //添加 家长设备关系
        try{
            SysParentDeviceBind sysParentDeviceBind = new SysParentDeviceBind();
            sysParentDeviceBind.setId(genID());
            sysParentDeviceBind.setDeviceId(sysDevice.getDeviceId());
            sysParentDeviceBind.setParentId(sysParent.getParentId());
            sysParentDeviceBindMapper.insertSelective(sysParentDeviceBind);
        }catch (Exception e){
            if( e instanceof DuplicateKeyException){//一个家长一个设备关系 不允许重复 故这里捕获异常
                log.warn(" appBindDesk parent desk relation exists  prentId:"+appBindDesk.getParentId() +" deviceNo:"+appBindDesk.getDeviceNo());
            }else{
                log.error(" appBindDesk addParentDevice error sysParent:"+sysParent +" ....deviceNo:"+appBindDesk.getDeviceNo(),e);
                throw new BusinessException("绑定设备失败!");
            }
        }

        List<SysStudentBind> sysStudentBindList = sysParentService.getSysStudentBind();
        if(sysStudentBindList != null && !sysStudentBindList.isEmpty()){
            for(SysStudentBind sysStudentBind:sysStudentBindList){
                SysStudent sysStudent = new SysStudent();
                String id = genID();
                sysStudent.setStudentId(id);
                sysStudent.setStudentCode(id);

                Object studentName = sysStudentBind.getStudent().get("studentName");
                sysStudent.setStudentName(studentName==null?"":studentName.toString());
                sysStudent.setStudentNamePy(PinYinUtil.getPingYin(sysStudent.getStudentName()));
                sysStudent.setSchoolName(sysStudentBind.getSchoolName());
                Object sex = sysStudentBind.getStudent().get("sex");

                //性别
                sysStudent.setSex(sex==null?1:Integer.parseInt(sex.toString()));
                sysStudent.setSyncFlag(1);
                //班级code
                Object classCode = sysStudentBind.getStudent().get("classCode");
                sysStudent.setSyncClassCode(classCode==null?null:classCode.toString());


                //年级code
                Object gradeCode = sysStudentBind.getStudent().get("grade");
                sysStudent.setGradeCode(gradeCode==null?null:gradeCode.toString());

                //学生Id
                Object studentId = sysStudentBind.getStudent().get("studentId");
                sysStudent.setSyncStudentId(studentId==null?null:studentId.toString());

                //学校code
                String schoolCode = sysStudentBind.getSchoolCode();
                sysStudent.setSyncSchoolCode(schoolCode);

                //学生code
                Object studentCode = sysStudentBind.getStudent().get("studentCode");
                sysStudent.setSyncStudentCode(studentCode==null?null:studentCode.toString());

                //添加学生信息
                try{
                    sysStudentService.addSelective(sysStudent);


                    //绑定学生家长信息
                    SysStudentParent sysStudentParent = new SysStudentParent();
                    sysStudentParent.setId(genID());
                    sysStudentParent.setParentId(sysParent.getParentId());
                    sysStudentParent.setStudentId(sysStudent.getStudentId());
                    sysStudentParentService.addSelective(sysStudentParent);
                }catch (Exception e){//学生已存在 唯一索引syncStudentCode重复不处理
                    if(!(e instanceof  DuplicateKeyException)){
                        log.error(" appBindDesk addStudent error sysStudent:"+sysStudent,e);
                        throw new BusinessException("绑定设备失败!");
                    }
                }
            }

        }
        //处理成功 新增信息到绑定处理结果表
        SysAppBindResult sysAppBindResult = new SysAppBindResult();
        sysAppBindResult.setParentId(sysParent.getParentId());
        sysAppBindResult.setDeviceId(sysDevice.getDeviceId());
        sysAppBindResult.setExpiredTime(5);
        sysAppBindResult.setBindResultId(genID());
        sysAppBindResultService.addSelective(sysAppBindResult);
        return true;
    }


    private void validateParam(AppBindDesk appBindDesk) throws Exception{

        //参数校验

        if(StringUtils.isEmpty(appBindDesk.getDeviceNo()))
            throw new MissingServletRequestParameterException("绑定设备no","");

        if(StringUtils.isEmpty(appBindDesk.getParentId()))
            throw new MissingServletRequestParameterException("绑定家长id","");

        //if(StringUtils.isEmpty(appBindDesk.getParentName()))
        //    throw new MissingServletRequestParameterException("绑定家长姓名","");

        if(StringUtils.isEmpty(appBindDesk.getPhone()))
            throw new MissingServletRequestParameterException("绑定家长手机号","");

        //if(appBindDesk.getSex() == null || StringUtils.isEmpty(appBindDesk.getSex().toString()))
        //    throw new MissingServletRequestParameterException("绑定家长性别","");

        /*
        if(appBindDesk.getStudentList()!=null && !appBindDesk.getStudentList().isEmpty()){

            List<SysStudent> sysStudentList = appBindDesk.getStudentList();
            for(SysStudent sysStudent:sysStudentList){
                if(StringUtils.isEmpty(sysStudent.getSyncStudentId()))
                    throw new MissingServletRequestParameterException("绑定学生id","");

                if(StringUtils.isEmpty(sysStudent.getSyncStudentCode()))
                    throw new MissingServletRequestParameterException("绑定学生code","");

                if(StringUtils.isEmpty(sysStudent.getSyncClassCode()))
                    throw new MissingServletRequestParameterException("绑定学生班级code","");

                if(StringUtils.isEmpty(sysStudent.getSyncSchoolCode()))
                    throw new MissingServletRequestParameterException("绑定学生学校code","");

                if(StringUtils.isEmpty(sysStudent.getSchoolName()))
                    throw new MissingServletRequestParameterException("绑定学生学校名称","");

                if(StringUtils.isEmpty(sysStudent.getStudentName()))
                    throw new MissingServletRequestParameterException("绑定学生姓名","");

                if(StringUtils.isEmpty(sysStudent.getGradeCode()))
                    throw new MissingServletRequestParameterException("绑定学生年级code","");

                if(sysStudent.getSex() == null || StringUtils.isEmpty(sysStudent.getSex().toString()))
                    throw new MissingServletRequestParameterException("绑定学生性别","");

                if(sysStudent.getAge() == null || StringUtils.isEmpty(sysStudent.getAge().toString()))
                    throw new MissingServletRequestParameterException("绑定学生年龄","");

            }
        }
        */
    }
}
