package com.cunw.cloud.familydesk.sysmanage.api.handle;

import com.cunw.cloud.framework.gateway.handle.GatewayHandle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 功能/ 模块：
 *
 * @author 贺康 hekang@newcloudnet.com
 * @version 1.0-SNAPSHORT 2018/5/7 16:06
 * 类描述
 * 修订历史：
 * 日期：2018/5/7
 * 作者：贺康
 * 参考：
 * 描述：
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@Component
public class PlatGatewayHandle extends GatewayHandle {

    @Value("${plat.server.url}")
    private String platServerUrl;

    @Value("${plat.server.api.key}")
    private String API_KEY;

    @Value("${plat.server.api.secret}")
    private String API_SECRET;

    @Override
    protected String getServerUrl() {
        return platServerUrl + "/plat/gateway";
    }

    @Override
    protected String getApiKey() {
        return API_KEY;
    }

    @Override
    protected String getApiSecret() {
        return API_SECRET;
    }
}
