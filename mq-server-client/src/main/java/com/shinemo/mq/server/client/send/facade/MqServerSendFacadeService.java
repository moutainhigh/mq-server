package com.shinemo.mq.server.client.send.facade;

import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.shinemo.mq.server.client.mq.domain.MqProviderConfig;


public interface MqServerSendFacadeService {
	/**
	 * 发送消息
	 * @param topic
	 * @param tags
	 * @param body
	 */
	void send(String topic,String tags,String body,MqProviderConfig config);
}
