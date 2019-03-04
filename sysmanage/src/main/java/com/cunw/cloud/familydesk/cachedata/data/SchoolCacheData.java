package com.cunw.cloud.familydesk.cachedata.data;

import com.cunw.cloud.familydesk.cachedata.base.CacheDataTemplate;
import com.cunw.cloud.familydesk.common.model.SysSchool;
import com.cunw.cloud.familydesk.sysmanage.service.ISysSchoolService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SchoolCacheData extends CacheDataTemplate<SysSchool> {

    @Autowired
    ISysSchoolService sysSchoolService;

    @PostConstruct
    public void init(){
        try{
            this.initData();
        }catch (Exception e){
            log.error(this.getCacheName()+" init error",e);
        }
    }

    @Override
    public Object handleData() {
        Map<String,Object> paraMap = Maps.newHashMap();
        paraMap.put("status",1);
        List<SysSchool> list =  sysSchoolService.query(paraMap);
        return list;
    }
}
