package com.cunw.cloud.familydesk.sysmanage.auth.shiro.token;

/**
 * 功能/ 模块：登录类型
 *
 * @author 贺康 hekang@newcloudnet.com
 * @version 1.0-SNAPSHORT 2018/3/12 14:32
 *          类描述
 *          修订历史：
 *          日期：2018/3/12
 *          作者：贺康
 *          参考：
 *          描述：
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
public enum LoginType {
    STUDENT("student"),
    PARENT("parent"),
    ADMIN("admin"),
    FAMILYDESK("familyDesk");

    private String type;

    LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}
