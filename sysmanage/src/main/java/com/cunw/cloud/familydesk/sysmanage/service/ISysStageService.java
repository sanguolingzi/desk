package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.SysStage;

import java.util.List;

/**
 * SysStage服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-25 10:21
 * 类描述
 * 修订历史：
 * 日期：2019-01-25
 * 作者：generator
 * 参考：
 * 描述：SysStage服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface ISysStageService extends IBaseService<SysStage, String>{

    List<SysStage> getStageList();
}