package com.cunw.cloud.familydesk.resource.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.Paper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperMapper extends IBaseMapper<Paper, String> {
}