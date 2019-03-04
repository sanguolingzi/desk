package com.cunw.cloud.familydesk.config;

import com.cunw.cloud.familydesk.json.handler.JsonReturnHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 包装 RequestResponseBodyMethodProcessor 得到的数据内容 通过jackson进行指定字段的过滤
 */
@Configuration
public class BusiJsonInital implements InitializingBean{

    @Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> unmodifiableList = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> list = new ArrayList<>(unmodifiableList.size());
        for (HandlerMethodReturnValueHandler returnValueHandler : unmodifiableList) {
            //RequestResponseBodyMethodProcessor 是处理@ResponseBody注解返回值的Handler 这里只是做了一个包装
            //将返回数据通过json进行过滤 最终调用的仍然是 RequestResponseBodyMethodProcessor 中的处理逻辑
            if (returnValueHandler instanceof RequestResponseBodyMethodProcessor) {
                list.add(new JsonReturnHandler(returnValueHandler));
            } else {
                list.add(returnValueHandler);
            }
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(list);
    }
}
