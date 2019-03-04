package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.sysmanage.service.ISysStudentParentUnbindService;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysStudentParentUnbind;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysStudentParentUnbindMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * sysStudentParentUnbind服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-02-20 12:50
 * 类描述
 * 修订历史：
 * 日期：2019-02-20
 * 作者：generator
 * 参考：
 * 描述：sysStudentParentUnbind服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysStudentParentUnbindService")
public class SysStudentParentUnbindServiceImpl extends BaseServiceImpl<SysStudentParentUnbind, String> implements ISysStudentParentUnbindService {

    @Autowired
    private SysStudentParentUnbindMapper SysStudentParentUnbindMapper;

    @Override
    protected IBaseMapper<SysStudentParentUnbind, String> getMapper() {
        return SysStudentParentUnbindMapper;
    }
}
