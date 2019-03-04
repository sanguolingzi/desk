package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.familydesk.common.model.SysApplicationUpdateRecord;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysApplicationUpdateRecordMapper extends IBaseMapper<SysApplicationUpdateRecord, String> {

    SysApplicationUpdateRecord getNewestByAppId(@Param("appId") String appId);

    void updateStatus(@Param("appId")String appId);
}