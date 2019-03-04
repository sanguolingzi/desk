package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.cachedata.data.StageGradeCacheData;
import com.cunw.cloud.familydesk.common.model.SysStageGradeCache;
import com.cunw.cloud.familydesk.sysmanage.service.ISysGradeService;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysGrade;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysGradeMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SysGrade服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-25 10:21
 * 类描述
 * 修订历史：
 * 日期：2019-01-25
 * 作者：generator
 * 参考：
 * 描述：SysGrade服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysGradeService")
public class SysGradeServiceImpl extends BaseServiceImpl<SysGrade, String> implements ISysGradeService {

    @Autowired
    private SysGradeMapper sysGradeMapper;

    @Autowired
    private StageGradeCacheData stageGradeCacheData;

    @Override
    protected IBaseMapper<SysGrade, String> getMapper() {
        return sysGradeMapper;
    }

    @Override
    public List<SysStageGradeCache> listByStage(String stage) {
        List<SysStageGradeCache> list = list();
        if(list != null){
            return list.stream().filter(sysStageGradeCache -> {
                if(sysStageGradeCache.getStageId().equals(stage))
                    return true;
                return false;
            }).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    @Override
    public List<SysStageGradeCache> list() {
        return stageGradeCacheData.getListData();
    }
}
