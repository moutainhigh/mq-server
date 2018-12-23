package com.shinemo.mq.server.client.mq.domain;

import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.shinemo.mq.server.client.common.entity.BaseDO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MqProviderConfig extends BaseDO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5355958473950335516L;
	
	/**
	 * 业务名 必传
	 */
	private String bizName;
	/**
	 * 生产者名称 必传
	 */
	private String producerGroupName;
	
	/**
	 * 去向appType
	 */
	private Integer toAppType;
	
	private MessageQueueSelector selector;
	
	private Object selectorId;
	
	

}
