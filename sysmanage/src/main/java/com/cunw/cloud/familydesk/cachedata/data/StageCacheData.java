package com.cunw.cloud.familydesk.cachedata.data;

import com.cunw.cloud.familydesk.cachedata.base.CacheDataTemplate;
import com.cunw.cloud.familydesk.common.model.SysStage;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStageService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Component
public class StageCacheData extends CacheDataTemplate<SysStage> {

    @Autowired
    ISysStageService sysStageService;

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
        return sysStageService.query(paraMap);
    }
}
