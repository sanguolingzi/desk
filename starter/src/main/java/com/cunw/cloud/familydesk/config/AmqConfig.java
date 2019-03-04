package com.cunw.cloud.familydesk.config;

import com.cunw.cloud.framework.mq.activemq.configData.ActiveMQUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.util.backoff.FixedBackOff;

import javax.jms.ConnectionFactory;
import java.util.concurrent.Executors;

@Configuration
public class AmqConfig implements JmsListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(AmqConfig.class);

    @Value("${spring.activemq.broker-url}")
    private String url;

    @Value("${spring.activemq.user}")
    private String userName;

    @Value("${spring.activemq.password}")
    private String password;

    /**
     * 全局设置
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        //targetConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, url);
        connectionFactory.setUseCompression(true);
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //设置消息的最大重发次数 超过这个次数  则进入死信队列
        redeliveryPolicy.setMaximumRedeliveries(2);
        //启用指数倍数递增的方式增加延迟时间
        redeliveryPolicy.setUseExponentialBackOff(true);

        //设置重连时间间隔递增倍数，只有值大于1和启用useExponentialBackOff参数时才生效
        redeliveryPolicy.setBackOffMultiplier(2);

        //最大传送延迟，只在useExponentialBackOff为true时有效 达到最大时间后 将以最大时间为 间隔时间重发
        redeliveryPolicy.setMaximumRedeliveryDelay(1000*120);
        //设置初始重发间隔
        redeliveryPolicy.setInitialRedeliveryDelay(5000l);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        //异步发送消息
        connectionFactory.setAlwaysSyncSend(true);

        //设置预获取属性设置
        ActiveMQPrefetchPolicy activeMQPrefetchPolicy = new ActiveMQPrefetchPolicy();
        activeMQPrefetchPolicy.setQueuePrefetch(50);
        connectionFactory.setPrefetchPolicy(activeMQPrefetchPolicy);

        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setMaxConnections(20);
        pooledConnectionFactory.setConnectionFactory(connectionFactory);

        return pooledConnectionFactory;
    }


    @Bean
    public ActiveMQUtil activeMQUtil(ConnectionFactory connectionFactory) {
        return new ActiveMQUtil(connectionFactory);
    }

    /*
    @Bean("previewResourceProducer")
    public IMessageProducer previewResourceProducer(ConnectionFactory connectionFactory) {
        AmqConfigData amqConfigData = new AmqConfigData();
        try {
            amqConfigData.setIsPersistent(DeliveryMode.PERSISTENT);
            amqConfigData.setMqName(resourcePreviewQueue);
            amqConfigData.setConnection(connectionFactory.createConnection());
            return new PreviewResourceProducer(amqConfigData);
        } catch (Exception e) {
            logger.error("previewResourceProducer init error ", e);
        }
        return new PreviewResourceProducer(amqConfigData);
    }
    */


    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar jmsListenerEndpointRegistrar) {
        /*
        //定义班级消息推送消费者
        Object fileFromMqListener = applicationContextProvider.getBean("fileFromMqListener");
        SimpleJmsListenerEndpoint fileFromMqListenerEndpoint = new SimpleJmsListenerEndpoint();
        fileFromMqListenerEndpoint.setId("fileFromMqListener");
        fileFromMqListenerEndpoint.setDestination(receiveFileFromSchool);
        fileFromMqListenerEndpoint.setMessageListener((MessageListener)fileFromMqListener);
        fileFromMqListenerEndpoint.setConcurrency("1");
        jmsListenerEndpointRegistrar.registerEndpoint(fileFromMqListenerEndpoint);
        // end
        */
    }
    /**
     * 控制 jmslistener连接
     * @param connectionFactory
     * @return
     */
    @Bean("jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory,
             DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //设置消息确认模式 这里设置的是单条消息确认 由于和spring集成 用client确认模式 会被spring自动确认
        factory.setSessionAcknowledgeMode(4);
        //设置连接数
        //factory.setConcurrency("2-5");
        //设置backoff 重连设置 这是重连间隔  重连总次数
        FixedBackOff fixedBackOff = new FixedBackOff(5000l, 10);
        factory.setBackOff(fixedBackOff);

        //设置线程池 原有线程没有做到复用
        factory.setTaskExecutor(Executors.newFixedThreadPool(20));
        //每个任务最大的任务数目
        //factory.setMaxMessagesPerTask(1);

        //设置信息
        configurer.configure(factory, connectionFactory);
        return factory;
    }


}
