package com.shinemo.mq.core.configuration;


import com.shinemo.jce.common.config.CenterConfig;
import com.shinemo.jce.common.config.ConsumerConfig;
import com.shinemo.jce.common.config.ProviderConfig;
import com.shinemo.mq.client.common.entity.InternalEventBus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * core 配置类
 *
 * @author zhangy
 * @date 2018-08-15
 */
@Configuration
@ComponentScan(basePackages = {
        "com.shinemo.mq.core",
})
public class CoreConfiguration {

    /**
     * 实例化 provider 配置
     *
     * @param port
     * @param proxy
     * @return com.shinemo.jce.common.config.ProviderConfig
     * @author zhangy
     * @date 2018-08-15
     **/
    @Bean(initMethod = "init")
    public ProviderConfig providerConfig(@Value("${shinemo.jce.provider.port}") Integer port,
                                         @Value("${shinemo.jce.provider.proxy}") String proxy) {
        ProviderConfig config = new ProviderConfig();
        config.setPort(port);
        config.setProxy(proxy);
        return config;
    }

    @Bean
    public InternalEventBus internalEventBus() {
        return new InternalEventBus();
    }
    /**
     * 实例化 consumer 配置
     *
     * @param url
     * @return com.shinemo.jce.common.config.ConsumerConfig
     * @author zhangy
     * @date 2018-08-15
     **/
    @Bean(initMethod = "init")
    public ConsumerConfig consumerConfig(@Value("${shinemo.jce.consumer.url}") String url) {
        ConsumerConfig config = new ConsumerConfig();
        config.setUrl(url);
        return config;
    }
    /**
     * 实例化 center 配置
     *
     * @param host
     * @param name
     * @return com.shinemo.jce.common.config.CenterConfig
     * @author zhangy
     * @date 2018-08-15
     **/
    @Bean(initMethod = "init")
    public CenterConfig centerConfig(@Value("${shinemo.jce.center.host}") String host,
                                     @Value("${application.name}") String name) {
        CenterConfig config = new CenterConfig();
        config.setIpAndPort(host);
        config.setAppName(name);
        return config;
    }
}
