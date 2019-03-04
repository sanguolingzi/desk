package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.cachedata.data.StageCacheData;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStageService;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysStage;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysStageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysStage服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-25 10:21
 * 类描述
 * 修订历史：
 * 日期：2019-01-25
 * 作者：generator
 * 参考：
 * 描述：SysStage服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysStageService")
public class SysStageServiceImpl extends BaseServiceImpl<SysStage, String> implements ISysStageService {

    @Autowired
    private SysStageMapper sysStageMapper;

    @Autowired
    private StageCacheData stageCacheData;

    @Override
    protected IBaseMapper<SysStage, String> getMapper() {
        return sysStageMapper;
    }

    @Override
    public List<SysStage> getStageList() {
        return stageCacheData.getListData();
    }
}
