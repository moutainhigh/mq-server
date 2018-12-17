package com.shinemo.mq.server.client.listener;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.shinemo.mq.server.client.common.result.Result;
import com.shinemo.mq.server.client.common.utils.MqContextUtil;
import com.shinemo.mq.server.client.message.domain.MqTo;
import com.shinemo.mq.server.client.message.domain.MqToQuery;
import com.shinemo.mq.server.client.message.facade.MqMessageFacadeService;
import com.shinemo.mq.server.client.mq.service.MqMessageConsumerService;

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

	/**
	 * 一天的毫秒数
	 */
	private final static long DAYMILILLS = 24*60*60*1000;


	@Override
	public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
		log.info(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());
		for(MessageExt msg:msgs){
			if(checkExpire) {
				long exprieMills = DAYMILILLS * expireTimesDays;
				long now = System.currentTimeMillis();
				if(now - msg.getBornTimestamp() > exprieMills) {
					log.error("[mqExpire] message:{}",msg);
					continue;
				}
			}
			if(checkRepeatMessage){
				MqMessageFacadeService messageFacadeService = MqContextUtil.getMessageFacadeService(false);
				MqToQuery query = new MqToQuery();
				query.setMessageId(msg.getMsgId());
				query.setBizName(bizName);
				Result<MqTo> repeatRs = messageFacadeService.getMqTo(query);
				if(repeatRs.hasValue()){
					log.error("[mqRepeatMessage] message:{}",msg);
					continue;
				}
				messageFacadeService.insertMqTo(MqContextUtil.initMqTo(msg,bizName));
			}else{
				log.info("message receive:"+msg.getMsgId());
			}
			mqMessageConsumerService.handleMessage(msg);
		}
		return ConsumeOrderlyStatus.SUCCESS;
	}

}
