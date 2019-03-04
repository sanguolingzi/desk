package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.sysmanage.service.ISysParentService;
import com.cunw.cloud.familydesk.common.model.SysStudentBind;
import com.cunw.cloud.framework.controller.client.ServerRestTemplate;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysParent;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysParentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * SysParent服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-21 02:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-21
 * 作者：generator
 * 参考：
 * 描述：SysParent服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysParentService")
public class SysParentServiceImpl extends BaseServiceImpl<SysParent, String> implements ISysParentService {

    /**
     * 这里 平台服务 暂时只有一个 后续可能需要维护一个平台服务列表
     */
    @Value("${plat.server.url}")
    private String platServerUrl;

    @Autowired
    private SysParentMapper sysParentMapper;

    @Override
    protected IBaseMapper<SysParent, String> getMapper() {
        return sysParentMapper;
    }


    @Override
    public List<SysStudentBind> getSysStudentBind() {
        String url = platServerUrl+"/sys/binds/getStudentDetail";
        List<SysStudentBind>  list= ServerRestTemplate.getList(url,SysStudentBind.class);
        return list;
    }
}
