package com.shinemo.mq.client.listener;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.shinemo.mq.client.mq.service.MqMessageConsumerService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MqMessageListenerOrderly implements MessageListenerOrderly{
	
	
	
	/**
	 * 是否校验重复消费
	 */
	private boolean checkRepeatMessage;
	
	/**
	 * 业务名称一般为应用名称
	 */
	private String bizName;
	
	/**
	 * 消费类
	 */
	private MqMessageConsumerService mqMessageConsumerService;
	
	/**
	 * 过期时间 单位天
	 */
	private long expireTimesDays;
	
	/**
	 * 校验是否过期
	 */
	private boolean checkExpire = false;
	
	//数据库操作 rpc类


	@Override
	public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
		log.info(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());
		for(MessageExt msg:msgs){
			if(checkExpire) {
				//判断过期 加log
				continue;
			}
			if(checkRepeatMessage){
				//先查询是否被消费 无则插入 有则直接返回
			}else{
				log.info("message receive:"+msg.getMsgId());
				mqMessageConsumerService.handleMessage(msg);
			}
		}
		return ConsumeOrderlyStatus.SUCCESS;
	}

}
