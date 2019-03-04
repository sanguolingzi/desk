package com.cunw.cloud.familydesk.sysmanage.service;

import com.cunw.cloud.familydesk.common.model.SysApplicationCenterVO;
import com.cunw.cloud.framework.service.IBaseService;
import com.cunw.cloud.familydesk.common.model.SysApplicationStudent;
import com.cunw.cloud.framework.service.pagehelper.PageList;

import java.util.Map;

/**
 * SysApplicationStudent服务接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:43
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：SysApplicationStudent服务接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public interface ISysApplicationStudentService extends IBaseService<SysApplicationStudent, String>{

    /**
     * 查询学生拥有应用
     * @param parameters
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageList<SysApplicationCenterVO> selectForPage(Map<String, Object> parameters, Integer pageNum, Integer pageSize);

    void addApp(String appId,String studentId);

    /**
     * 课桌端卸载应用调用接口  删除应用和学生关系
     * @param sysApplicationStudent
     * @return
     */
    boolean uninstallApp(SysApplicationStudent sysApplicationStudent) throws Exception;

}