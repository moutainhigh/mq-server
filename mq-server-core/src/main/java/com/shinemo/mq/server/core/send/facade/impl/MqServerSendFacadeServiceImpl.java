package com.shinemo.mq.server.core.send.facade.impl;

import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.shinemo.mq.server.client.send.facade.MqServerSendFacadeService;

public class MqServerSendFacadeServiceImpl implements MqServerSendFacadeService{

	@Override
	public void sendWithSelector(String topic, String tags, String body, MessageQueueSelector selector,
			Object selectorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(String topic, String tags, String body) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendCrossCluster(String topic, String tags, String body, Integer toAppType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendCrossClusterWithSelector(String topic, String tags, String body, Integer toAppType,
			MessageQueueSelector selector, Object selectorId) {
		// TODO Auto-generated method stub
		
	}

}
