package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.cachedata.data.SchoolCacheData;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysSchool;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysSchoolMapper;
import com.cunw.cloud.familydesk.sysmanage.service.ISysSchoolService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * SysSchool服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysSchool服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysSchoolService")
public class SysSchoolServiceImpl extends BaseServiceImpl<SysSchool, String> implements ISysSchoolService {

    @Autowired
    private SysSchoolMapper sysSchoolMapper;

    @Autowired
    SchoolCacheData schoolCacheData;


    @Override
    protected IBaseMapper<SysSchool, String> getMapper() {
        return sysSchoolMapper;
    }

    @Override
    public SysSchool getSysSchoolBySchoolCode(String schoolCode) {
        if(schoolCode == null)
            return null;

        //先从缓存中获取
        List<SysSchool> list = schoolCacheData.getListData();
        if(list != null && !list.isEmpty()){
            for(SysSchool school:list){
                if(schoolCode.equals(school.getSchoolCode()))
                    return school;
            }
        }


        //缓存中不存在 则直接查询数据库 何时出现这种情况？
        Map<String,Object> paraMap = Maps.newHashMap();
        paraMap.put("schoolCode",schoolCode);
        paraMap.put("status",1);
        SysSchool sysSchool  = getByParams(paraMap);
        return sysSchool;
    }

    @Override
    public void clearAndSetSchoolData(List<SysSchool> list) {
        Map<String,Object> paraMap= Maps.newHashMap();
        List<SysSchool> currentData = sysSchoolMapper.selectByParameters(paraMap);
        if(currentData != null && !currentData.isEmpty()){
            currentData.stream().forEach(sysSchool -> {
                sysSchoolMapper.deleteByPrimaryKey(sysSchool.getId());
            });
        }
        sysSchoolMapper.batchInsert(list);
        //刷新缓存
        schoolCacheData.init();
    }
}
