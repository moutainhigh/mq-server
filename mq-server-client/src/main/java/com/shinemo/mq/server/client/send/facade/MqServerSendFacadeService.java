package com.shinemo.mq.server.client.send.facade;

import com.alibaba.rocketmq.client.producer.MessageQueueSelector;


public interface MqServerSendFacadeService {
	
	/**
	 * 发送消息with选择器
	 * @param topic
	 * @param tags
	 * @param body
	 * @param selector
	 * @param selectorId
	 */
	void sendWithSelector(String topic,String tags,String body,MessageQueueSelector 
			selector, Object selectorId);
	/**
	 * 发送消息
	 * @param topic
	 * @param tags
	 * @param body
	 */
	void send(String topic,String tags,String body);
	
	/**
	 * 发送消息垮集群
	 * @param topic
	 * @param tags
	 * @param body
	 * @param toAppType
	 */
	void sendCrossCluster(String topic,String tags,String body,Integer toAppType);
	/**
	 * 发送消息垮集群
	 * @param topic
	 * @param tags
	 * @param body
	 * @param toAppType
	 * @param selector
	 * @param selectorId
	 */
	void sendCrossClusterWithSelector(String topic,String tags,String body,Integer toAppType,MessageQueueSelector 
			selector, Object selectorId);
	
}
