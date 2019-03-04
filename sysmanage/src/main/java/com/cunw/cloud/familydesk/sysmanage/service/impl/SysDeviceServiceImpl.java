package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysDevice;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysDeviceMapper;
import com.cunw.cloud.familydesk.sysmanage.service.ISysDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysDevice服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysDevice服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysDeviceService")
public class SysDeviceServiceImpl extends BaseServiceImpl<SysDevice, String> implements ISysDeviceService {

    @Autowired
    private SysDeviceMapper sysDeviceMapper;

    @Override
    protected IBaseMapper<SysDevice, String> getMapper() {
        return sysDeviceMapper;
    }
}
