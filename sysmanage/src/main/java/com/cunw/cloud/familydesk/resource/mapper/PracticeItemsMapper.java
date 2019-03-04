package com.cunw.cloud.familydesk.resource.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.PracticeItems;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PracticeItemsMapper extends IBaseMapper<PracticeItems, String> {
    int deleteByParameters(Map<String, Object> parameters);
}