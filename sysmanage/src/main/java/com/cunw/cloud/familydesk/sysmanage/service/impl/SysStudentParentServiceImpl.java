package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysStudentParent;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysStudentParentMapper;
import com.cunw.cloud.familydesk.sysmanage.service.ISysStudentParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysStudentParent服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:59
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysStudentParent服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysStudentParentService")
public class SysStudentParentServiceImpl extends BaseServiceImpl<SysStudentParent, String> implements ISysStudentParentService {

    @Autowired
    private SysStudentParentMapper sysStudentParentMapper;

    @Override
    protected IBaseMapper<SysStudentParent, String> getMapper() {
        return sysStudentParentMapper;
    }

    @Override
    public void updateStatus(List<String> list) {
        sysStudentParentMapper.updateStatus(list);
    }
}
