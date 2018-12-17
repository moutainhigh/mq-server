package com.shinemo.mq.server.client.send.facade;

import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.shinemo.mq.client.common.result.Result;

public interface MqSendFacadeService {
	
	/**
	 * TODO 写在aaceProxy
	 * @param topic
	 * @param tags
	 * @param body
	 * @param selector
	 * @param selectorId
	 * @return
	 */
	Result<SendResult> sendWithSelector(String topic, String tags, String body, MessageQueueSelector 
			selector, Object selectorId);
	

}
