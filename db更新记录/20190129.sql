INSERT INTO sys_scheduler_job(job_id,job_name,job_group,pro_class,job_type,job_cron,start_time,work_when_start)
VALUES (UUID(), '文件清理定时任务', 'clearBusiFileInfo', 'com.cunw.cloud.familydesk.sysmanage.job.ClearBusiFileInfo', '1', '0 0 3 1/1 * ? *', '2019-01-28 11:27:05',0);
INSERT INTO sys_scheduler_job(job_id,job_name,job_group,pro_class,job_type,job_cron,start_time,work_when_start)
VALUES (UUID(), '同步平台学校数据', 'syncSchoolInfoJob', 'com.cunw.cloud.familydesk.sysmanage.job.SyncSchoolInfoJob', '1', '0 0 1 1/1 * ? *', '2019-01-28 11:27:05',0);