package com.cunw.cloud.familydesk.config;

import com.cunw.cloud.familydesk.sysmanage.auth.shiro.realm.ParentNotCheckRealm;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能/ 模块：
 *
 * @author 贺康 hekang@newcloudnet.com
 * @version 1.0-SNAPSHORT 2018/4/25 14:01
 * 类描述
 * 修订历史：
 * 日期：2018/4/25
 * 作者：贺康
 * 参考：
 * 描述：
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Configuration
public class ReamlsConfig {

    @Value("${cache.level:2}")
    private String cache;

    @Bean(name = "shiroRealms")
    @Primary
    public List<Realm> shiroRealms(
                                   @Qualifier("parentNotCheckRealm") ParentNotCheckRealm parentNotCheckRealm
                                   ) {
        List<Realm> realms = new ArrayList<>();
        realms.add(parentNotCheckRealm);
        return realms;
    }


    @Bean(name = "parentNotCheckRealm")
    @Primary
    public ParentNotCheckRealm parentNotCheckRealm(){
        ParentNotCheckRealm realm = new ParentNotCheckRealm();
        return realm;
    }
}
