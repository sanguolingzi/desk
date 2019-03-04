package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.familydesk.common.model.AppBindDesk;
import com.cunw.cloud.framework.exception.BusinessException;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.SysParentDeviceBind;

/**
 * SysParentDeviceBind服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysParentDeviceBind服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface ISysParentDeviceBindService extends IBaseService<SysParentDeviceBind, String>{

    boolean appBindDesk(AppBindDesk appBindDesk) throws Exception;
}