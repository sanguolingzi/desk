package com.cunw.cloud.familydesk.sysmanage.auth.controller;

import com.cunw.cloud.familydesk.sysmanage.auth.shiro.token.LoginType;
import com.cunw.cloud.framework.auth.controller.AbstractAuthController;
import com.cunw.cloud.framework.auth.model.IUser;
import com.cunw.cloud.framework.auth.shiro.realm.UserPayload;
import com.cunw.cloud.framework.cache.Caches;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.utils.misc.IdGenerator;
import com.cunw.cloud.plat.common.model.SysParent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 功能/ 模块：
 *
 * @author 贺康 hekang@newcloudnet.com
 * @version 1.0-SNAPSHORT 2017/12/17 19:08
 *          类描述
 *          修订历史：
 *          日期：2017/12/17
 *          作者：贺康
 *          参考：
 *          描述：
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Slf4j
@RestController
@RequestMapping("")
public class SysParentAuthController extends AbstractAuthController {

    @Value("${access.token.ttl:604800000}")
    Long accessTokenTtl;

    @Value("${refresh.token.ttl:1814400000}")
    Long refreshTokenTtl;

    @Value("${resource.version.url}")
    String resourceServerUrl;

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    Caches caches;

    @Override
    protected IUser getUserById(String id) {
        //return sysTeacherService.getById(id);
        SysParent sysParent = new SysParent();
        sysParent.setId("test");
        sysParent.setParentName("testName");
        return sysParent;
    }

    @Override
    protected IUser login(String loginName, String password) {
        //SysTeacher teacher = sysTeacherService.login(loginName, password);
        //return teacher;
        SysParent sysParent = new SysParent();
        sysParent.setId("test");
        sysParent.setParentName("testName");
        return sysParent;
    }

    @Override
    protected Object getPayload(IUser user) {
        //String orgCode = sysTeacherService.getSchoolCode(user.getLoginName()) + "," + user.getOrgCode();
        UserPayload payload = new UserPayload(user.getId(), user.getLoginName()+"_"+LoginType.FAMILYDESK.toString(), null, user.getName(), LoginType.FAMILYDESK.ordinal());
        return payload;
    }

    @Override
    protected String getCacheRefVaildPre() {
        return "parent";
    }

    @PostMapping("/parents/auth/login")
    @Override
    public RESTfulResult applyToken(@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
        log.info("login:name:{},password:{}",loginName,password);
        return super.applyToken(loginName, password);
    }

    @Override
    protected void handleResult(Map map) {
        //map.put("menus", sysMenuService.queryTeacherSystemSideTree());
       // map.put("resourceServerUrl", resourceServerUrl);
    }

    @Override
    protected void handleLogout(UserPayload user) {

        //sysTeacherService.logout(user.getId());
    }

    /**
     * AccessToken有效时间，默认1小时
     * @return
     */
    protected long getAccessTokenTtl(){
        return accessTokenTtl == null ? super.getAccessTokenTtl() : accessTokenTtl;
    }

    /**
     * RefreshToken有效时间，默认3周
     * @return
     */
    protected long getRefreshTokenTtl(){
        return refreshTokenTtl == null ? super.getAccessTokenTtl() : refreshTokenTtl;
    }

}
