package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;

import java.util.Date;

public class SysSchedulerJob implements IBasePO<String> {
    /**
     * 任务id
     */
    private String jobId;

    /**
     * 定时任务名称：要求具有描述性。
     */
    private String jobName;

    /**
     * 定时任务分组
     */
    private String jobGroup;

    /**
     * 对任务的描述
     */
    private String jobDesc;

    /**
     * 定时任务的执行程序：由一个资源表达式定义：如spring:myBean#myFunction
     */
    private String proClass;

    /**
     * 任务类型：1-java程序 2-脚本 3-存储过程等。
     */
    private String jobType;

    /**
     * 一个Cron表达式：用于定义Job的运行时机。如0 0 30 * * ?
     */
    private String jobCron;

    /**
     * 定时任务的当前状态:1-未调度 2-暂停 3-运行中
     */
    private String jobStatus;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 任务最后一次的执行状态：失败或者成功
     */
    private String lastStatus;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建人
     */
    private String crtUserCode;

    /**
     * 创建机构
     */
    private String crtOrgCode;

    /**
     * 创建时间
     */
    private Date crtDate;

    /**
     * 修改人
     */
    private String updUserCode;

    /**
     * 修改机构
     */
    private String updOrgCode;

    /**
     * 修改日期
     */
    private Date updDate;

    /**
     * 是否服务启动 立即执行
     */
    private Integer workWhenStart;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.jobId;
    }

    @Override
    public void setId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc == null ? null : jobDesc.trim();
    }

    public String getProClass() {
        return proClass;
    }

    public void setProClass(String proClass) {
        this.proClass = proClass == null ? null : proClass.trim();
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType == null ? null : jobType.trim();
    }

    public String getJobCron() {
        return jobCron;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron == null ? null : jobCron.trim();
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus == null ? null : jobStatus.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus == null ? null : lastStatus.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCrtUserCode() {
        return crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode == null ? null : crtUserCode.trim();
    }

    public String getCrtOrgCode() {
        return crtOrgCode;
    }

    public void setCrtOrgCode(String crtOrgCode) {
        this.crtOrgCode = crtOrgCode == null ? null : crtOrgCode.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getUpdUserCode() {
        return updUserCode;
    }

    public void setUpdUserCode(String updUserCode) {
        this.updUserCode = updUserCode == null ? null : updUserCode.trim();
    }

    public String getUpdOrgCode() {
        return updOrgCode;
    }

    public void setUpdOrgCode(String updOrgCode) {
        this.updOrgCode = updOrgCode == null ? null : updOrgCode.trim();
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public Integer getWorkWhenStart() {
        return workWhenStart;
    }

    public void setWorkWhenStart(Integer workWhenStart) {
        this.workWhenStart = workWhenStart;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jobId=").append(jobId);
        sb.append(", jobName=").append(jobName);
        sb.append(", jobGroup=").append(jobGroup);
        sb.append(", jobDesc=").append(jobDesc);
        sb.append(", proClass=").append(proClass);
        sb.append(", jobType=").append(jobType);
        sb.append(", jobCron=").append(jobCron);
        sb.append(", jobStatus=").append(jobStatus);
        sb.append(", startTime=").append(startTime);
        sb.append(", lastStatus=").append(lastStatus);
        sb.append(", status=").append(status);
        sb.append(", crtUserCode=").append(crtUserCode);
        sb.append(", crtOrgCode=").append(crtOrgCode);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", updUserCode=").append(updUserCode);
        sb.append(", updOrgCode=").append(updOrgCode);
        sb.append(", updDate=").append(updDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}