package com.shinemo.mq.server.core.send.facade.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.shinemo.jce.common.config.JceHolder;
import com.shinemo.mq.server.client.common.utils.MqContextUtil;
import com.shinemo.mq.server.client.common.utils.SpringContextHolder;
import com.shinemo.mq.server.client.mq.domain.MqProviderConfig;
import com.shinemo.mq.server.client.mq.service.MqProviderService;
import com.shinemo.mq.server.client.mq.service.impl.MqProviderServiceImpl;
import com.shinemo.mq.server.client.send.facade.MqSendFacadeService;
import com.shinemo.mq.server.client.send.facade.MqServerSendFacadeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mqServerSendFacadeService")
public class MqServerSendFacadeServiceImpl implements MqServerSendFacadeService{
	
	@Value("shinemo.mq.address")
	private String mqAddr;
	
	private static final int MAIN_APP = 1;

	@Override
	public void send(String topic, String tags, String body,MqProviderConfig config) {
		Assert.hasText(topic,"topic is null");
		Assert.hasText(tags,"tags is null");
		Assert.hasText(body,"body is null");
		Assert.notNull(config,"config is null");
		MqProviderService mqProviderService = initMqProviderService(config.getBizName(),config.getProducerGroupName());
		boolean crossCluster = false;
		if(config.getToAppType() != MAIN_APP) {
			crossCluster = true;
		}
		mqProviderService.send(topic, tags, body, config.getSelector(),config.getSelectorId(),crossCluster,config.getToAppType());
	}

	
	private MqProviderService initMqProviderService(String bizName, String producerGroup) {
		String beanName = bizName+"mqProviderService";
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MqProviderServiceImpl.class);
        beanDefinitionBuilder.addPropertyValue("nameSrcAddr", mqAddr);
        beanDefinitionBuilder.addPropertyValue("producerGroup",producerGroup);
        beanDefinitionBuilder.addPropertyValue("bizName", bizName);
        beanDefinitionBuilder.addPropertyValue("isInsertDB", true);
        beanDefinitionBuilder.setInitMethodName("init");
		MqProviderService mqProviderService = SpringContextHolder.getBeanAndGenerateIfNotExist
				(beanName,MqProviderServiceImpl.class, beanDefinitionBuilder);
		return mqProviderService;
	}

	

}
