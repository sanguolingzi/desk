package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.familydesk.common.model.SysStudentBind;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.SysParent;

import java.util.List;

/**
 * SysParent服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysParent服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface ISysParentService extends IBaseService<SysParent, String>{

    /**
     * 获取家长的绑定学生关系
     * 业务参数 只需要parentId 这个参数在生成的jwt内容中
     * @return
     */
     List<SysStudentBind> getSysStudentBind();
}