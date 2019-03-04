package com.cunw.cloud.familydesk.cachedata.data;

import com.cunw.cloud.familydesk.cachedata.base.CacheDataTemplate;
import com.cunw.cloud.sysmanage.general.sysdict.model.SysDict;
import com.cunw.cloud.sysmanage.general.sysdict.service.ISysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
@Slf4j
public class FileTypeCacheData extends CacheDataTemplate<SysDict> {

    @Autowired
    private ISysDictService sysDictService;

    private Map<String,SysDict> dataMap = null;

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
        return SysDict.class;
    }

    @Override
    public Object handleData() {
        List<SysDict> list = sysDictService.queryDictNoRoot("fileType");
        return list;
    }

    @Override
    public SysDict getData(Object condition) {
        return dataMap.getOrDefault(condition,null);
    }

    @Override
    public void initData() {
        super.initData();
        dataMap = this.getListData().stream().collect(Collectors.toMap(SysDict::getDictName, a -> a,(k1,k2)->k1));
    }
}
