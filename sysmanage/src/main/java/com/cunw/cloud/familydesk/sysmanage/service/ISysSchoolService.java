package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.SysSchool;

import java.util.List;

/**
 * SysSchool服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysSchool服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface ISysSchoolService extends IBaseService<SysSchool, String>{

    SysSchool getSysSchoolBySchoolCode(String schoolCode);

    /**
     * 清空本地库的学校信息数据 插入平台端获取的最新学校数据 更新本地缓存
     * @param list
     */
    void clearAndSetSchoolData(List<SysSchool> list);
}