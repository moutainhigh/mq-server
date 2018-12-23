package com.shinemo.mq.server.core.send.facade.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.stereotype.Service;

import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.shinemo.jce.common.config.JceHolder;
import com.shinemo.mq.server.client.common.utils.MqContextUtil;
import com.shinemo.mq.server.client.common.utils.SpringContextHolder;
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

	@Override
	public void sendWithSelector(String topic, String tags, String body, MessageQueueSelector selector,
			Object selectorId) {
		log.info("");
		String bizName = "";//TODO 取proxyName
		String producerGroup = "";//TODO 取proxyName
		MqProviderService mqProviderService = initMqProviderService(bizName,producerGroup);
		mqProviderService.send(topic, tags, body, selector, selectorId, false, null);
		
	}

	@Override
	public void send(String topic, String tags, String body) {
		sendWithSelector(topic,tags,body,null,null);
	}

	@Override
	public void sendCrossCluster(String topic, String tags, String body, Integer toAppType) {
		sendCrossClusterWithSelector(topic,tags,body,toAppType,null,null);
	}

	@Override
	public void sendCrossClusterWithSelector(String topic, String tags, String body, Integer toAppType,
			MessageQueueSelector selector, Object selectorId) {
		String bizName = "";//TODO 取proxyName
		String producerGroup = "";//TODO 取proxyName
		MqProviderService mqProviderService = initMqProviderService(bizName,producerGroup);
		mqProviderService.send(topic, tags, body, selector, selectorId, true, toAppType);
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
