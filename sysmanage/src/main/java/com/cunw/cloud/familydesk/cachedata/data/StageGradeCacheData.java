package com.cunw.cloud.familydesk.cachedata.data;

import com.cunw.cloud.familydesk.cachedata.base.CacheDataTemplate;
import com.cunw.cloud.familydesk.common.model.SysStageGrade;
import com.cunw.cloud.familydesk.common.model.SysStageGradeCache;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStageGradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class StageGradeCacheData extends CacheDataTemplate<SysStageGradeCache> {

    @Autowired
    ISysStageGradeService sysStageGradeService;

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
        return sysStageGradeService.selectStageGradeCache();
    }



    public SysStageGradeCache getSysStageGradeCacheByGradeCode(String gradeCode){

        if(gradeCode == null)
            return new SysStageGradeCache();

        List<SysStageGradeCache> list = getListData();

        if(list == null || list.isEmpty())
            return new SysStageGradeCache();



        for(SysStageGradeCache sysStageGradeCache:list){
            if(gradeCode.equals(sysStageGradeCache.getGradeCode()))
                return sysStageGradeCache;
        }
        return new SysStageGradeCache();
    }
}
