package com.shinemo.mq.core.configuration;


import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.shinemo.jce.common.config.CenterConfig;
import com.shinemo.jce.common.config.ConsumerConfig;
import com.shinemo.jce.common.config.ProviderConfig;
import com.shinemo.mq.server.client.common.entity.InternalEventBus;
import com.shinemo.mq.server.client.message.facade.MqServerFacadeService;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
/**
 * core 配置类
 *
 * @author zhangy
 * @date 2018-08-15
 */
@Configuration
@ComponentScan(basePackages = {
        "com.shinemo.mq.core",
        "com.shinemo.mq.dal"
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
    public ProviderConfig providerConfig(@Value("${shinemo.jce.provider-port}") Integer port,
                                         @Value("${shinemo.jce.provider-proxy}") String proxy) {
        ProviderConfig config = new ProviderConfig();
        config.setPort(port);
        config.setProxy(proxy);
        return config;
    }

    @Bean
    public InternalEventBus internalEventBus() {
        return new InternalEventBus();
    }
    
    @Bean
	@DependsOn("shineMoConfig")
	public Map<String, String> appTypeAceProxyMap(@Qualifier("shineMoConfig") ShineMoConfig shineMoConfig) {
		Map<String, Integer> siteId = shineMoConfig.getApplication().getSiteId();
		Map<String, String> siteDomain = shineMoConfig.getJce().getSiteDomain();
		Set<String> keys = Sets.intersection(siteId.keySet(), siteDomain.keySet());
		Map<String, String> appTypeAceProxyMap = Maps.newHashMap();
		Integer site;
		String url;
		for (String key : keys) {
			site = siteId.get(key);
			url = siteDomain.get(key);
			if (site == null || StringUtils.isBlank(url)) {
				continue;
			}
			appTypeAceProxyMap.put(String.valueOf(site), url + "/aceproxy/dispatch");
		}
		return appTypeAceProxyMap;
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
    @DependsOn("appTypeAceProxyMap")
    public ConsumerConfig consumerConfig(@Value("${shinemo.jce.consumer.url}") String url,
    					@Qualifier("appTypeAceProxyMap") Map<String,String> appTypeAceProxyMap) {
        ConsumerConfig config = new ConsumerConfig();
        config.setUrlMap(appTypeAceProxyMap);
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
