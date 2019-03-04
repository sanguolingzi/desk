package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.SysSchedulerJob;

/**
 * SysSchedulerJob服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2018-04-04 10:38
 * 类描述
 * 修订历史：
 * 日期：2018-04-04
 * 作者：generator
 * 参考：
 * 描述：SysSchedulerJob服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface ISysSchedulerJobService extends IBaseService<SysSchedulerJob, String> {

    /**
     * 根据jobName 和 jobGroup 修改upd_date 字段 用来记录上次任务的执行时间 以及上次执行结果(非业务结果)
     */
    void updateJobWorkTime(SysSchedulerJob sysSchedulerJob);
}