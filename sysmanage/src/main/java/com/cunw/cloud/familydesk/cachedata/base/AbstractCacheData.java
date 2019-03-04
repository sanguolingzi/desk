package com.cunw.cloud.familydesk.cachedata.base;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractCacheData<T> implements ICacheData<T>{
    /**
     * 定义泛型的class
     * @return
     */
   public  Class getEntityClass(){
       return (Class<T>) ((ParameterizedType) getClass()
               .getGenericSuperclass()).getActualTypeArguments()[0];
   }

    /**
     * 初始化缓存数据业务
     */
    public abstract Object handleData();

}
