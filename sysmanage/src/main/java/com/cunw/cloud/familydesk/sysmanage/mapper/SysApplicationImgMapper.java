package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.familydesk.common.model.SysApplicationImg;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysApplicationImgMapper extends IBaseMapper<SysApplicationImg, String> {
    void updateStatus(@Param("appId") String appId);
}