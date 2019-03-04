package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.common.model.SysApplicationImg;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysApplicationImgMapper;
import com.cunw.cloud.familydesk.sysmanage.service.ISysApplicationImgService;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysApplicationImg服务实现：
 *
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationImg服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysApplicationImgService")
public class SysApplicationImgServiceImpl extends BaseServiceImpl<SysApplicationImg, String> implements ISysApplicationImgService {

    @Autowired
    private SysApplicationImgMapper sysApplicationImgMapper;


    @Override
    protected IBaseMapper<SysApplicationImg, String> getMapper() {
        return sysApplicationImgMapper;
    }

    public void updateStatus(String appId) {
        sysApplicationImgMapper.updateStatus(appId);
    }

}
