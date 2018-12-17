package com.shinemo.mq.client.mq.service;

import com.alibaba.rocketmq.common.message.MessageExt;

public interface MqMessageConsumerService {
	
	/**
	 * 消费消息
	 * @param msg
	 */
	void handleMessage(MessageExt msg);
}
