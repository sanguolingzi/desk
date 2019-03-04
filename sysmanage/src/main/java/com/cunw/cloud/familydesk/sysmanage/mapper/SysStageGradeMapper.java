package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.familydesk.common.model.SysStageGrade;
import com.cunw.cloud.familydesk.common.model.SysStageGradeCache;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysStageGradeMapper extends IBaseMapper<SysStageGrade, String> {
        List<SysStageGradeCache> selectStageGradeCache();
}