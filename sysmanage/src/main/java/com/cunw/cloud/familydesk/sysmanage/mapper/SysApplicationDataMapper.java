package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.familydesk.common.model.SysApplicationData;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysApplicationDataMapper extends IBaseMapper<SysApplicationData, String> {

    void  addPraise(@Param("appId") String appId);

    void  reducePraise(@Param("appId") String appId);

    void addDownloadNum(@Param("appId") String appId);

}