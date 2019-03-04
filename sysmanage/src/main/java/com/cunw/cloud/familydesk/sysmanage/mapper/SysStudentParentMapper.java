package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.familydesk.common.model.SysStudentParent;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysStudentParentMapper extends IBaseMapper<SysStudentParent, String> {
    void updateStatus(List<String> list);
}