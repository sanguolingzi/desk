package com.cunw.cloud.familydesk.cachedata.base;

import com.cunw.cloud.framework.cache.ValueWrapper;
import com.cunw.cloud.framework.cache.server.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class CacheDataTemplate<T> extends AbstractCacheData<T>{
    @Autowired
    private RedisCache redisCache;
    @Override
    public void removeData() {
        redisCache.del(getCacheName());
    }

    @Override
    public List<T> getListData() {
        ValueWrapper wrapper = redisCache.getListWrapper(getCacheName(),getEntityClass());
        return wrapper!=null?wrapper.getValue():null;
    }

    @Override
    public T getData() {
        ValueWrapper wrapper = redisCache.getValueWrapper(getCacheName(),getEntityClass());
        return wrapper!=null?wrapper.getValue():null;
    }

    @Override
    public void initData() {
        redisCache.setValueWrapper(getCacheName(),handleData());
    }

    public String getCacheName(){
        return this.getClass().getSimpleName()+"_cache";
    }

    @Override
    public T getData(Object condition) {
        return null;
    }
}
