package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.SysApplicationImg;

/**
 * SysApplicationImg服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationImg服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface ISysApplicationImgService extends IBaseService<SysApplicationImg, String>{
    void updateStatus(String appId);

}