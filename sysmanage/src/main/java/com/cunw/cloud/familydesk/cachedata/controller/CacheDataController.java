package com.cunw.cloud.familydesk.cachedata.controller;

import com.cunw.cloud.familydesk.cachedata.base.CacheDataTemplate;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.spring.provider.ApplicationContextProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cacheData")
public class CacheDataController  extends BaseRESTfulController {

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    @ApiOperation(value="获取指定缓存值")
    @GetMapping(value = "/getCacheData")
    public RESTfulResult getCacheData(@RequestParam("cacheName") String cacheName) {
        CacheDataTemplate cacheDataTemplate  = applicationContextProvider.getBean(cacheName);
        return success(cacheDataTemplate.getData());
    }

    @ApiOperation(value="获取指定缓存值(集合)")
    @GetMapping(value = "/getListCacheData")
    public RESTfulResult getListCacheData(@RequestParam("cacheName") String cacheName) {
        CacheDataTemplate cacheDataTemplate  = applicationContextProvider.getBean(cacheName);
        return success(cacheDataTemplate.getListData());
    }


    @ApiOperation(value="刷新指定缓存")
    @GetMapping(value = "/cacheRefresh")
    public RESTfulResult cacheRefresh(@RequestParam("cacheName") String cacheName) {
        CacheDataTemplate cacheDataTemplate  = applicationContextProvider.getBean(cacheName);
        cacheDataTemplate.initData();
        return success("success");
    }

    @ApiOperation(value="删除指定缓存")
    @GetMapping(value = "/cacheRemove")
    public RESTfulResult cacheRemove(@RequestParam("cacheName") String cacheName) {
        CacheDataTemplate cacheDataTemplate  = applicationContextProvider.getBean(cacheName);
        cacheDataTemplate.removeData();
        return success("success");
    }
}
