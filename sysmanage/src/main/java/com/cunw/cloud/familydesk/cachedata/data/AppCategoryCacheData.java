package com.cunw.cloud.familydesk.cachedata.data;

import com.cunw.cloud.familydesk.cachedata.base.CacheDataTemplate;
import com.cunw.cloud.sysmanage.general.sysdict.model.CodeNameSysDict;
import com.cunw.cloud.sysmanage.general.sysdict.service.ISysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
@Slf4j
public class AppCategoryCacheData extends CacheDataTemplate<CodeNameSysDict> {

    @Autowired
    private ISysDictService sysDictService;

    @PostConstruct
    public void init(){
        try{
            this.initData();
        }catch (Exception e){
            log.error(this.getCacheName()+" init error",e);
        }
    }

    @Override
    public Class getEntityClass() {
        return CodeNameSysDict.class;
    }

    @Override
    public Object handleData() {
        List<CodeNameSysDict> list = sysDictService.queryResCodeNames("applicationCategory");
        return list;
    }

}
