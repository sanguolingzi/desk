package com.cunw.cloud.familydesk.resource.service.impl;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.PracticeItems;
import com.cunw.cloud.familydesk.resource.mapper.PracticeItemsMapper;
import com.cunw.cloud.familydesk.resource.service.IPracticeItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PracticeItems服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-02-26 03:38
 * 类描述
 * 修订历史：
 * 日期：2018-02-26
 * 作者：generator
 * 参考：
 * 描述：PracticeItems服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "practiceItemsService")
public class PracticeItemsServiceImpl extends BaseServiceImpl<PracticeItems, String> implements IPracticeItemsService {

    @Autowired
    private PracticeItemsMapper practiceItemsMapper;

    @Override
    protected IBaseMapper<PracticeItems, String> getMapper() {
        return practiceItemsMapper;
    }

    public int batchInsert(List<PracticeItems> list){
        return practiceItemsMapper.batchInsert(list);
    }

    public int deleteByParameters(Map<String, Object> parameters){
        return practiceItemsMapper.deleteByParameters(parameters);
    }
}
