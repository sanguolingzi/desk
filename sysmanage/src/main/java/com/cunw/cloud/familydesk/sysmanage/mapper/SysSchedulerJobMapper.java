package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.SysSchedulerJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysSchedulerJobMapper extends IBaseMapper<SysSchedulerJob, String> {
     void updateJobWorkTime(SysSchedulerJob sysSchedulerJob);
}