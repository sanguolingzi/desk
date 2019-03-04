package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.familydesk.common.model.SysApplicationCenterVO;
import com.cunw.cloud.familydesk.common.model.SysApplicationStudent;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysApplicationStudentMapper extends IBaseMapper<SysApplicationStudent, String> {

    /**
     * 根据卸载的appId 以及 studentId 删除学生应用关系
     * @param sysApplicationStudent
     * @return
     */
    int updateByAppIdAndStudentId(SysApplicationStudent sysApplicationStudent);

    List<SysApplicationCenterVO> selectForPage(Map<String, Object> parameters);
}