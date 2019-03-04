package com.cunw.cloud.familydesk.sysmanage.service.impl;

import com.cunw.cloud.familydesk.common.model.AppBindDeskResult;
import com.cunw.cloud.familydesk.common.model.SysDevice;
import com.cunw.cloud.familydesk.common.model.SysParent;
import com.cunw.cloud.familydesk.sysmanage.auth.shiro.token.LoginType;
import com.cunw.cloud.familydesk.sysmanage.service.ISysDeviceService;
import com.cunw.cloud.familydesk.sysmanage.service.ISysParentService;
import com.cunw.cloud.framework.auth.shiro.constant.ShiroConstant;
import com.cunw.cloud.framework.auth.shiro.jwt.JwtUtils;
import com.cunw.cloud.framework.auth.shiro.realm.UserPayload;
import com.cunw.cloud.framework.cache.Caches;
import com.cunw.cloud.framework.mapper.IBaseMapper;
import com.cunw.cloud.framework.service.impl.BaseServiceImpl;
import com.cunw.cloud.familydesk.common.model.SysAppBindResult;
import com.cunw.cloud.familydesk.sysmanage.mapper.SysAppBindResultMapper;
import com.cunw.cloud.familydesk.sysmanage.service.ISysAppBindResultService;
import com.cunw.cloud.framework.utils.base.StringUtils;
import com.cunw.cloud.framework.utils.misc.IdGenerator;
import com.cunw.cloud.framework.utils.text.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * SysAppBindResult服务实现：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-22 03:41
 * 类描述
 * 修订历史：
 * 日期：2019-01-22
 * 作者：generator
 * 参考：
 * 描述：SysAppBindResult服务实现
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Service(value = "sysAppBindResultService")
@Slf4j
public class SysAppBindResultServiceImpl extends BaseServiceImpl<SysAppBindResult, String> implements ISysAppBindResultService {

    @Autowired
    private SysAppBindResultMapper sysAppBindResultMapper;

    @Autowired
    private ISysDeviceService sysDeviceService;

    @Autowired
    private ISysParentService sysParentService;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    Caches caches;

    @Value("${access.token.ttl:604800000}")
    Long accessTokenTtl;

    @Value("${refresh.token.ttl:1814400000}")
    Long refreshTokenTtl;

    @Override
    protected IBaseMapper<SysAppBindResult, String> getMapper() {
        return sysAppBindResultMapper;
    }


    @Override
    public AppBindDeskResult getAppBindResult(String deviceNo) {
        AppBindDeskResult appBindDeskResult = new AppBindDeskResult();
        appBindDeskResult.setStatus(0);
        if(StringUtils.isEmpty(deviceNo)){
            return appBindDeskResult;
        }

        SysDevice sysDevice = sysDeviceService.getByProperty("deviceNo",deviceNo);
        if(sysDevice == null){
            log.error(" getAppBindResult sysDevice not exist deviceNo:"+deviceNo);
            return  appBindDeskResult;
        }

        SysAppBindResult sysAppBindResult = sysAppBindResultMapper.getAppBindResult(sysDevice.getDeviceId());

        if(sysAppBindResult != null){
            SysParent sysParent  = sysParentService.getById(sysAppBindResult.getParentId());
            appBindDeskResult.setStatus(1);
            appBindDeskResult.setParentId(sysParent.getParentId());
            appBindDeskResult.setParentName(sysParent.getParentName());
            appBindDeskResult.setSex(sysParent.getSex());
            appBindDeskResult.setPhone(sysParent.getPhone());

            //生成accessToken
            UserPayload payload = new UserPayload(sysParent.getParentId(), sysParent.getParentId()+"_"+LoginType.FAMILYDESK.toString(), null, sysParent.getParentName(), LoginType.FAMILYDESK.ordinal());
            String accessToken = JwtUtils.sign(payload,accessTokenTtl);


            //同一个parentId扫多个课桌 会清空前一个登录信息 类似于  后登陆的信息会覆盖前面登录的信息 前面登录的信息会失效
            // 生成刷新id，用于刷新黑名单
            String refreshId = idGenerator.randomBase64(32);
            String refreshToken = JwtUtils.sign(refreshId, sysParent.getParentId(), refreshTokenTtl);
            // 重新登录时，把前一个access token失效
            caches.getDefaultCache().put(ShiroConstant.JWT_ACCESS_VALID_PRE + sysParent.getParentId(), accessToken);
            // 重新登录时，把前一个刷新id失效（加入黑名单）
            String beforeRefreshId = (String)caches.getDefaultCache().get(ShiroConstant.JWT_REFRESH_VALID_PRE + sysParent.getParentId(), String.class);
            if (beforeRefreshId != null){
                caches.getDefaultCache().put(ShiroConstant.JWT_REFRESH_INVALID_PRE + MD5Utils.INSTANCE.getMD5ofStr(beforeRefreshId), beforeRefreshId);
            }
            caches.getDefaultCache().put(ShiroConstant.JWT_REFRESH_VALID_PRE  + sysParent.getParentId(), refreshId);

            appBindDeskResult.setAccessToken(accessToken);
            appBindDeskResult.setRefreshToken(refreshToken);

            //更新信息
            String id =sysAppBindResult.getId();
            sysAppBindResult = new SysAppBindResult();
            sysAppBindResult.setId(id);
            sysAppBindResult.setAccessToken(accessToken);
            sysAppBindResult.setRefreshToken(refreshToken);
            sysAppBindResultMapper.updateAppBindResult(sysAppBindResult);
        }

        return appBindDeskResult;
    }

    public static void main(String[] ars){
        //3153600000000
        Long l = new Long(3650l*24l*60l*60l*1000l);
        System.out.println(l);
        System.out.println(36500*24*60*60*1000);
    }
}
