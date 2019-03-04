package com.cunw.cloud.familydesk.sysmanage.controller;

import com.cunw.cloud.framework.controller.BaseController;
import com.cunw.cloud.familydesk.common.model.SysSchedulerJob;
import com.cunw.cloud.familydesk.sysmanage.service.ISysSchedulerJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/${api.version}/quartz/job")
public class QuartzJobController extends BaseController {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ISysSchedulerJobService sysSchedulerJobService;

    /**
     * 初始化定时任务
     */
    @PostConstruct
    public void init(){
        try {
            addJob();
        } catch (Exception e) {
            log.error("init job fail__*{}",e.getMessage());
        }
    }


    public void addJob() throws Exception {
        //查询任务
        HashMap<String, Object> params = new HashMap(2);
        params.put("jobStatus","1");
        params.put("status", "1");
        List<SysSchedulerJob> sysSchedulerJobs = sysSchedulerJobService.query(params);

        if (sysSchedulerJobs != null && !sysSchedulerJobs.isEmpty()) {
            for (int i = 0; i < sysSchedulerJobs.size(); i++) {
                SysSchedulerJob sysSchedulerJob = sysSchedulerJobs.get(i);
                //任务名称
                String jobName = sysSchedulerJob.getJobName();
                //任务组
                String jobGroup = sysSchedulerJob.getJobGroup();
                //任务程序
                String proClass = sysSchedulerJob.getProClass();
                //任务表达式
                String jobCron = sysSchedulerJob.getJobCron();

                //开始执行时间
                Date startTime = sysSchedulerJob.getStartTime();

                //构建job信息
                JobDetail jobDetail = JobBuilder.newJob(getClass(proClass).getClass())
                        .withIdentity(jobName, jobGroup).build();

                //表达式调度构建器(即任务执行的时间)
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobCron);

                //按新的cronExpression表达式构建一个新的trigger
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
                        .withSchedule(scheduleBuilder).startAt(startTime)
                        .build();

                //这个设置是让定时任务根据时间表达式到的下一个周期开始执行 默认配置下会立即执行此任务
                //状态位 0 表示不立即执行
                if(sysSchedulerJob.getWorkWhenStart() == 0){
                    ((CronTriggerImpl)trigger).setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
                }

                try {
                    //告诉quartz使用定义的触发器trigger安排执行任务job
                    scheduler.scheduleJob(jobDetail, trigger);
                } catch (SchedulerException e) {
                    log.info("scheduler fail {}",e.getMessage());
                    throw e;
                }

                // 启动调度器
                scheduler.start();
                log.info("start scheduler...............");
            }
        }
    }

    @PostMapping(value = "/pausejob")
    public void pausejob(@RequestParam(value = "jobName") String jobName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        jobPause(jobName, jobGroupName);
    }

    public void jobPause(String jobName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
    }


    @PostMapping(value = "/resumejob")
    public void resumejob(@RequestParam(value = "jobName") String jobName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        jobresume(jobName, jobGroupName);
    }

    public void jobresume(String jobName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
    }


    @PostMapping(value = "/reschedulejob")
    public void rescheduleJob(@RequestParam(value = "jobName") String jobName,
                              @RequestParam(value = "jobGroupName") String jobGroupName,
                              @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        jobreschedule(jobName, jobGroupName, cronExpression);
    }

    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
    }


    @PostMapping(value = "/deletejob")
    public void deletejob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        jobdelete(jobClassName, jobGroupName);
    }

    public void jobdelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @GetMapping(value = "/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {

        return null;
    }

    public static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }


}