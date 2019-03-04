package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysSchedulerJob;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysSchedulerJobMapper;
import com.cunw.cloud.familydesk.sysmanage.service.ISysSchedulerJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysSchedulerJob服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-04-04 10:38
 * 类描述
 * 修订历史：
 * 日期：2018-04-04
 * 作者：generator
 * 参考：
 * 描述：SysSchedulerJob服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value="sysSchedulerJobService")
public class SysSchedulerJobServiceImpl extends BaseServiceImpl<SysSchedulerJob, String> implements ISysSchedulerJobService {

    @Autowired
    private SysSchedulerJobMapper sysSchedulerJobMapper;

    @Override
    protected IBaseMapper<SysSchedulerJob, String> getMapper() {
        return sysSchedulerJobMapper;
    }

    @Override
    public void updateJobWorkTime(SysSchedulerJob sysSchedulerJob) {
        sysSchedulerJobMapper.updateJobWorkTime(sysSchedulerJob);
    }
}
