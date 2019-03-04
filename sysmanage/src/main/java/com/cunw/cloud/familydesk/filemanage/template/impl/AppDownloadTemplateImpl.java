package com.cunw.cloud.familydesk.filemanage.template.impl;

import com.cunw.cloud.familydesk.common.model.SysApplicationUpdateRecord;
import com.cunw.cloud.familydesk.filemanage.template.DownloadTemplate;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationDataService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationStudentService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationUpdateRecordService;
import com.cunw.cloud.framework.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("appDownloadTemplate")
public class AppDownloadTemplateImpl  extends DownloadTemplate{

    @Autowired
    ISysApplicationDataService sysApplicationDataService;
    @Autowired
    ISysApplicationStudentService applicationStudentService;

    @Autowired
    ISysApplicationUpdateRecordService sysApplicationUpdateRecordService;

    public boolean beforeDownload(Object... params) {
        String appId = params[0].toString();
        SysApplicationUpdateRecord updateRecord = sysApplicationUpdateRecordService.getNewestByAppId(appId);
        if(updateRecord==null){
            throw new BusinessException("app is not exists");
        }
        return true;
    }

    public void afterDownload(Object... params) {
        String appId = params[0].toString();
        String studentId = params[1].toString();
        sysApplicationDataService.addDownloadNum(appId);
        applicationStudentService.addApp(appId,studentId);
    }
}
