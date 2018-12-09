package com.shinemo.mq.client.listener;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.shinemo.mq.client.mq.service.MqMessageConsumerService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MqMessageListenerConcurrently implements MessageListenerConcurrently{
	
	
	
	private boolean checkRepeatMessage;
	
	private String bizName;
	
	private MqMessageConsumerService mqMessageConsumerService;

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
