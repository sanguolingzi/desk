package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysApplicationUpdateRecord;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysApplicationUpdateRecordMapper;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationUpdateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysApplicationUpdateRecord服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationUpdateRecord服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysApplicationUpdateRecordService")
public class SysApplicationUpdateRecordServiceImpl extends BaseServiceImpl<SysApplicationUpdateRecord, String> implements ISysApplicationUpdateRecordService {

    @Autowired
    private SysApplicationUpdateRecordMapper sysApplicationUpdateRecordMapper;

    @Override
    protected IBaseMapper<SysApplicationUpdateRecord, String> getMapper() {
        return sysApplicationUpdateRecordMapper;
    }

    public SysApplicationUpdateRecord getNewestByAppId(String appId){
        return  sysApplicationUpdateRecordMapper.getNewestByAppId(appId);
    }

    public void updateStatus(String appId){
        sysApplicationUpdateRecordMapper.updateStatus(appId);
    }
}
