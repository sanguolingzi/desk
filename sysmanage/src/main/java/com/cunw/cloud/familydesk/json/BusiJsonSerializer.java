package com.cunw.cloud.familydesk.json;

import com.cunw.cloud.familydesk.json.filter.JSONFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class BusiJsonSerializer {
    ObjectMapper mapper = new ObjectMapper();
    JSONFilter jsonFilter  = new JSONFilter();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @param clazz 需要设置规则的Class
     * @param include 转换时包含哪些字段
     * @param filter 转换时过滤哪些字段
     */
    public void filter(Class<?> clazz, String include, String filter) {
        if (clazz == null) return;
        if (include != null && include.length() > 0) {
            jsonFilter.include(clazz,include.split(","));
        } else if (filter !=null && filter.length() > 0) {
            jsonFilter.filter(clazz,filter.split(","));
        }
        mapper.addMixIn(clazz, JSONFilter.class);
    }

    public String toJson(Object object) throws JsonProcessingException {
        mapper.setFilterProvider(jsonFilter);
        mapper.setDateFormat(simpleDateFormat);
        return mapper.writeValueAsString(object);
    }
}
