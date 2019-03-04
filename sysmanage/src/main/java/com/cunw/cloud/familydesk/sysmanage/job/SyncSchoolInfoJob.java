package com.cunw.cloud.familydesk.sysmanage.job;

import com.cunw.cloud.familydesk.common.model.SysSchool;
import com.cunw.cloud.familydesk.sysmanage.job.template.JobTemplate;
import com.cunw.cloud.familydesk.sysmanage.service.ISysSchoolService;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SyncSchoolInfoJob extends JobTemplate{

    @Value("${plat.server.url}")
    private String platServerUrl;

    @Autowired
    private ISysSchoolService sysSchoolService;

    @Override
    public void handleBusiness() throws Exception {
        List<SysSchool> list = ServerRestTemplate.getList(platServerUrl+"/sys/schools", SysSchool.class);
        if(list == null || list.isEmpty())
            return;

        sysSchoolService.clearAndSetSchoolData(list);

    }
}
