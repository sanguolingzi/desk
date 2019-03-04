package com.cunw.cloud.familydesk.cachedata.base;

import java.util.List;

public interface ICacheData<T> {
     List<T> getListData();

     T getData();

     /**
      * 指定条件返回对应数据 由子类自己实现业务规则
      * @param condition
      * @return
      */
     T getData(Object condition);

     void initData();

     void removeData();
}
