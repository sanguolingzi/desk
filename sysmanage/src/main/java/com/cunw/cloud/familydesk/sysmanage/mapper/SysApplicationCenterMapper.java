package com.cunw.cloud.familydesk.sysmanage.mapper;

import com.cunw.cloud.familydesk.common.model.SysApplicationCenter;
import com.cunw.cloud.familydesk.common.model.SysApplicationCenterVO;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysApplicationCenterMapper extends IBaseMapper<SysApplicationCenter, String> {
    List<SysApplicationCenterVO> selectForPage(Map<String, Object> parameters);
}