package com.shinemo.mq.core.configuration;


import com.shinemo.jce.spring.AaceProviderBean;
import com.shinemo.mq.server.client.message.facade.MqMessageFacadeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ProviderConfiguration {

    @Bean(initMethod = "init")
    @DependsOn("mqMessageFacadeService")
    public AaceProviderBean providerOrderPortraitFacadeService(@Qualifier("mqMessageFacadeService") MqMessageFacadeService mqMessageFacadeService) {
        AaceProviderBean aaceProviderBean = new AaceProviderBean();
        aaceProviderBean.setInterfaceName(MqMessageFacadeService.class.getName());
        aaceProviderBean.setTarget(mqMessageFacadeService);
        return aaceProviderBean;
    }
}
