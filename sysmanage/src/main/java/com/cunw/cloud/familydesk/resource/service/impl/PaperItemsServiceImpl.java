package com.cunw.cloud.familydesk.resource.service.impl;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.PaperItems;
import com.cunw.cloud.familydesk.resource.mapper.PaperItemsMapper;
import com.cunw.cloud.familydesk.resource.service.IPaperItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PaperItems服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-02-26 03:38
 * 类描述
 * 修订历史：
 * 日期：2018-02-26
 * 作者：generator
 * 参考：
 * 描述：PaperItems服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "paperItemsService")
public class PaperItemsServiceImpl extends BaseServiceImpl<PaperItems, String> implements IPaperItemsService {

    @Autowired
    private PaperItemsMapper paperItemsMapper;

    @Override
    protected IBaseMapper<PaperItems, String> getMapper() {
        return paperItemsMapper;
    }

    public int batchInsert(List<PaperItems> list){
        return paperItemsMapper.batchInsert(list);
    }

    public int deleteByParameters(Map<String, Object> parameters){
        return paperItemsMapper.deleteByParameters(parameters);
    }
}
