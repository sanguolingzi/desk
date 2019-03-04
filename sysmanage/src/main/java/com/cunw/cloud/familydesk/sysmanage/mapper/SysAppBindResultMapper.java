package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.familydesk.common.model.SysAppBindResult;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysAppBindResultMapper extends IBaseMapper<SysAppBindResult, String> {
    /**
     * 获取扫码结果中最新的一条数据 orderby crt_date limit 1实现
     * @param deviceId
     * @return
     */
    SysAppBindResult getAppBindResult(String deviceId);

    int updateAppBindResult(SysAppBindResult sysAppBindResult);
}