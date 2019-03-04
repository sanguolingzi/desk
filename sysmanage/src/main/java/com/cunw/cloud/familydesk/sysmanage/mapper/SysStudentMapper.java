package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.familydesk.common.model.SysStudent;
import com.cunw.cloud.familydesk.common.model.SysStudentVO;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysStudentMapper extends IBaseMapper<SysStudent, String> {
    void updateStatus(List<String> list);

    List<SysStudentVO> getAllSduByParentId(String id);

    int unBindStudentInfo(String studentId);
}