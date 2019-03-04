package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysApplicationData;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysApplicationDataMapper;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SysApplicationData服务实现：
 *
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationData服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysApplicationDataService")
public class SysApplicationDataServiceImpl extends BaseServiceImpl<SysApplicationData, String> implements ISysApplicationDataService {

    @Autowired
    private SysApplicationDataMapper sysApplicationDataMapper;

    @Override
    protected IBaseMapper<SysApplicationData, String> getMapper() {
        return sysApplicationDataMapper;
    }



    public void addPraise(String appId) {
        sysApplicationDataMapper.addPraise(appId);
    }

    public void reducePraise(String appId) {
        sysApplicationDataMapper.reducePraise(appId);
    }

    public void addDownloadNum(String appId) {
        sysApplicationDataMapper.addDownloadNum(appId);
    }

    @Override
    public SysApplicationData selectByPrimary(String id) {
        return sysApplicationDataMapper.selectByPrimaryKey(id);
    }
}
