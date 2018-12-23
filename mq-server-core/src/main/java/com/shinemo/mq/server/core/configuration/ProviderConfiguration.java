package com.shinemo.mq.server.core.configuration;


import com.shinemo.jce.spring.AaceProviderBean;
import com.shinemo.mq.server.client.message.facade.MqMessageFacadeService;
import com.shinemo.mq.server.client.message.facade.MqServerFacadeService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ProviderConfiguration {

    @Bean(initMethod = "init")
    @DependsOn("mqServerFacadeService")
    public AaceProviderBean providerOrderPortraitFacadeService(@Qualifier("mqServerFacadeService") MqServerFacadeService mqServerFacadeService) {
        AaceProviderBean aaceProviderBean = new AaceProviderBean();
        aaceProviderBean.setInterfaceName(MqServerFacadeService.class.getName());
        aaceProviderBean.setTarget(mqServerFacadeService);
        return aaceProviderBean;
    }
}
