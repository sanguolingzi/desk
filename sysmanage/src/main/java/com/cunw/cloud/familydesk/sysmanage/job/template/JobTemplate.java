package com.cunw.cloud.familydesk.sysmanage.job.template;

import com.cunw.cloud.familydesk.common.model.SysSchedulerJob;
import com.cunw.cloud.familydesk.sysmanage.service.ISysSchedulerJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class JobTemplate implements Job {

    @Autowired
    private ISysSchedulerJobService sysSchedulerJobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        String jobGroup = jobExecutionContext.getJobDetail().getKey().getGroup();
        log.info("----- "+this.getClass().getSimpleName()+"  --------jobName"+jobName+"    jobGroup:"+jobGroup);

        SysSchedulerJob sysSchedulerJob = new SysSchedulerJob();
        sysSchedulerJob.setJobName(jobName);
        sysSchedulerJob.setJobGroup(jobGroup);
        try{
            handleBusiness();
            sysSchedulerJob.setLastStatus("成功");
        }catch (Exception e){
            log.error(" job "+this.getClass().getSimpleName()+"--------jobName"+jobName+"    jobGroup:"+jobGroup +"error",e);
            sysSchedulerJob.setLastStatus("失败");
        }
        sysSchedulerJobService.updateJobWorkTime(sysSchedulerJob);
    }

    public abstract void handleBusiness() throws Exception;
}
