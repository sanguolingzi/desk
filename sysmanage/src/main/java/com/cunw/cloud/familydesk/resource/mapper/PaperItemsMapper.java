package com.cunw.cloud.familydesk.resource.mapper;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.familydesk.common.model.PaperItems;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PaperItemsMapper extends IBaseMapper<PaperItems, String> {
    int deleteByParameters(Map<String, Object> parameters);
}