package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.common.model.SysStageGradeCache;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStageGradeService;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysStageGrade;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysStageGradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysStageGrade服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-25 10:21
 * 类描述
 * 修订历史：
 * 日期：2019-01-25
 * 作者：generator
 * 参考：
 * 描述：SysStageGrade服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysStageGradeService")
public class SysStageGradeServiceImpl extends BaseServiceImpl<SysStageGrade, String> implements ISysStageGradeService {

    @Autowired
    private SysStageGradeMapper sysStageGradeMapper;

    @Override
    protected IBaseMapper<SysStageGrade, String> getMapper() {
        return sysStageGradeMapper;
    }

    @Override
    public List<SysStageGradeCache> selectStageGradeCache() {
        return sysStageGradeMapper.selectStageGradeCache();
    }
}
