package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.familydesk.common.model.SysGrade;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysGradeMapper extends IBaseMapper<SysGrade, String> {

    List<SysGrade> listByStage(String stage);
}