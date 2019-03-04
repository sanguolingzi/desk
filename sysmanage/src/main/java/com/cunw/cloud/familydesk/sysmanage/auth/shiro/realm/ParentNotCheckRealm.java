package com.cunw.cloud.familydesk.sysmanage.auth.shiro.realm;

import com.cunw.cloud.framework.auth.model.IUser;
import com.cunw.cloud.framework.auth.shiro.realm.AbstractStatelessRealm;
import com.cunw.cloud.framework.auth.shiro.realm.UserPayload;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.authc.AuthenticationException;

import java.util.HashSet;
import java.util.Set;

/**
 * Shiro域的实现，安全数据源
 * @author hekang
 * @createTime 2017/6/29 13:44
 */
@Log4j
public class ParentNotCheckRealm extends AbstractStatelessRealm {

    @Override
    public IUser findByLoginName(UserPayload payload) {
        //if (LoginType.PARENT.ordinal() == payload.getType()) {
        if(true){
            Parent parent = new Parent();
            parent.setId(payload.getId());
            parent.setLoginName(payload.getName());
            parent.setName(payload.getLoginName());
            parent.setType(payload.getType());
            return parent;
        } else {
            throw new AuthenticationException("没有权限！");
        }
    }

    @Override
    public boolean checkRealm(UserPayload payload) {
        //TODO 需要后续维护完善用户信息
        return true;
        //return LoginType.PARENT.ordinal() == payload.getType();
    }

    @Override
    public boolean checkSingle(String jwt, UserPayload payload) {
        return true;
    }

    @Override
    public UserRolesAndPermissions doGetGroupAuthorizationInfo(IUser userInfo) {
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
//        List<SysRole> sysRoles = sysRoleService.getByUserId(userInfo.getId());
//        for (SysRole sysRole : sysRoles){
//            userRoles.add(sysRole.getRoleCode());
//        }
//        List<SysFuncAction> sysFunActions = sysFuncActionService.queryByUserId(userInfo.getId());
//        for (SysFuncAction action : sysFunActions){
//            userPermissions.add(action.getActionCode());
//        }
        return new UserRolesAndPermissions(userRoles, userPermissions);
    }

    @Override
    public UserRolesAndPermissions doGetRoleAuthorizationInfo(IUser userInfo) {
        // doGetGroupAuthorizationInfo 已加载
        return null;
    }

    @Override
    public String getName() {
        return "parent";
    }

    private class Parent implements IUser {

        private String id;

        private String loginName;

        private String orgCode;

        private String password;

        private String name;

        private Integer type;

        @Override
        public String getId() {
            return this.id;
        }

        @Override
        public String getLoginName() {
            return this.loginName;
        }

        @Override
        public String getOrgCode() {
            return this.orgCode;
        }

        @Override
        public String getPassword() {
            return this.password;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }
}
