package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.familydesk.common.model.*;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.framework.service.pagehelper.PageList;
import java.util.Map;

/**
 * SysApplicationCenter服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationCenter服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface ISysApplicationCenterService extends IBaseService<SysApplicationCenter, String>{

    PageList<SysApplicationCenterVO> selectForPage(Map<String, Object> parameters, Integer pageNum, Integer pageSize);

    SysApplicationCenterInfo getAppInfoById(String appId)  throws Exception;

    SysApplicationCenterInfo modifyApp(SysApplicationUploadVO applicationUploadVO);

    SysApplicationUpdateRecord setSysApplicationUpdateRecord(String appFileId, String appId, String description, String version);



}