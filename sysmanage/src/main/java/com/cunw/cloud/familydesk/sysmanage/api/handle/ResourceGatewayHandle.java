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
public class ResourceGatewayHandle extends GatewayHandle {

    @Value("${resource.version.url}")
    private String resourceServerUrl;

    private static String API_KEY = "ORG829902";

    private static String API_SECRET = "mfwwdqyjkozihvcnaqebbqadswawsajb";

    @Override
    protected String getServerUrl() {
        return resourceServerUrl + "/resource/gateway";
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
