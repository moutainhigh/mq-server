package com.shinemo.mq.server.core.configuration;


import com.shinemo.jce.spring.AaceProviderBean;
import com.shinemo.mq.server.client.message.facade.MqMessageFacadeService;
import com.shinemo.mq.server.client.send.facade.MqServerSendFacadeService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ProviderConfiguration {

    @Bean(initMethod = "init")
    @DependsOn("mqServerSendFacadeService")
    public AaceProviderBean providerOrderPortraitFacadeService(@Qualifier("mqServerSendFacadeService") MqServerSendFacadeService mqServerSendFacadeService) {
        AaceProviderBean aaceProviderBean = new AaceProviderBean();
        aaceProviderBean.setInterfaceName(MqServerSendFacadeService.class.getName());
        aaceProviderBean.setTarget(mqServerSendFacadeService);
        return aaceProviderBean;
    }
}
