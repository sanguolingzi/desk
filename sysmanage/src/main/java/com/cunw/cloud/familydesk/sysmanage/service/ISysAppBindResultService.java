package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.familydesk.common.model.AppBindDeskResult;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.SysAppBindResult;

/**
 * SysAppBindResult服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-22 03:41
 * 类描述
 * 修订历史：
 * 日期：2019-01-22
 * 作者：generator
 * 参考：
 * 描述：SysAppBindResult服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface ISysAppBindResultService extends IBaseService<SysAppBindResult, String>{

    /**
     * 课桌轮询获取app对课桌的绑定情况
     * @param deviceNo  课桌设备号
     * @return
     */
    AppBindDeskResult getAppBindResult(String deviceNo);
}