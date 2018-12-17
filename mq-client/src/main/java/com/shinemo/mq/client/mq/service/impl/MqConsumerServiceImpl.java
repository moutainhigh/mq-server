package com.shinemo.mq.client.mq.service.impl;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.google.common.base.Joiner;
import com.shinemo.mq.client.common.utils.AssertUtil;
import com.shinemo.mq.client.listener.MqMessageListenerConcurrently;
import com.shinemo.mq.client.mq.service.MqConsumerService;
import com.shinemo.mq.client.mq.service.MqMessageConsumerService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.Set;

@Slf4j
@Getter
@Setter
public class MqConsumerServiceImpl implements MqConsumerService{


    private static final String SPLIT_STR = "\\||";
    private static final String STR = "||";

    /**
     * 消费组名称
     */
    private String consumerGroupName;
    /**
     * 地址列表,若是集群用;作为地址的分隔符
     */
    private String nameSrvAddr;
    /**
     * 默认ip@pid(pid代表jvm名字)
     * 同一个consumerGroupName下
     * 实例名称 如果客户端不设置此参数 默认会生成不同的实例名称 消费消息的时候利于负载均衡
     * 如果设置了此参数 有了同样的实例名称 会共有链接和资源 一个消息会被同样名称的每个consumer消费
     */
    private String instanceName;
    /**
     * topic和tag集合的map
     */
    private Map<String, Set<String>> topicAndSetTags;
    /**
     * 消息监听器 MessageListenerConcurrently是无序的效率更高  
     * MessageListenerOrderly是有序的能保证按顺序消费
     */
    private MessageListener messageListener;
    /**
     * 消费者
     */
    private DefaultMQPushConsumer mqPushConsumer;
    
    /**
     * 消费service
     */
    private MqMessageConsumerService mqMessageConsumerService;
    /**
     * 是否需要http调用数据库
     */
    private boolean isNeedHttp = false;


   
	@Override
    public void init() {
        
        AssertUtil.notNullString(nameSrvAddr,"nameSrvAddr is null");
        AssertUtil.notNullString(consumerGroupName,"consumerGroupName is null");
        AssertUtil.notNullMap(topicAndSetTags,"topicMap is null");
        mqPushConsumer = new DefaultMQPushConsumer(consumerGroupName);
        if(!StringUtils.isBlank(instanceName)){
            mqPushConsumer.setInstanceName(instanceName);
        }
        if(messageListener == null) {
        	AssertUtil.notNullObject(mqMessageConsumerService, "listener and mqMessageConsumerService not together null");
        	MqMessageListenerConcurrently listener = new MqMessageListenerConcurrently();
        	listener.setBizName(consumerGroupName);
        	listener.setCheckExpire(true);
        	listener.setMqMessageConsumerService(mqMessageConsumerService);
            listener.setNeedHttp(isNeedHttp);
        }
        
        mqPushConsumer.setMessageModel(MessageModel.CLUSTERING);
        try {
            for (Map.Entry<String, Set<String>> entry : topicAndSetTags.entrySet()) {
                String tagsString = Joiner.on(STR).join(entry.getValue());
                mqPushConsumer.subscribe(entry.getKey(), tagsString);
            }
            if(messageListener.getClass().isInstance(MessageListenerConcurrently.class)) {
            	mqPushConsumer.registerMessageListener((MessageListenerConcurrently)messageListener);
            }else {
            	mqPushConsumer.registerMessageListener((MessageListenerOrderly)messageListener);
            }
            mqPushConsumer.start();
            log.info("[startComsumer] consumerGroupName:{} nameSrvAddr:{} topicMap:{}",consumerGroupName,
                    nameSrvAddr,topicAndSetTags);
        } catch (Exception e) {
            log.error("consumer start exception:"+consumerGroupName+","+nameSrvAddr+","+instanceName,e);
        }

    }

    @Override
    public void shutdown() {
    	if(mqPushConsumer!=null){
    		mqPushConsumer.shutdown();
			log.error("consumer shutdown:"+consumerGroupName+","+nameSrvAddr+","+instanceName);
		}
    }

    @Override
    public void subscribe(String topic, Set<String> tags) {
    	log.info("[subscribe] topic:"+topic+",tags:"+tags);
		if(tags== null || tags.size()<=0) {
			return;
		}
		Set<String> oldTags = topicAndSetTags!=null? topicAndSetTags.get(topic):null;
		if(oldTags!=null&&oldTags.size()>0) {
			tags.addAll(oldTags);
		}
		String tagsString = Joiner.on(STR).join(tags);
		try {
			mqPushConsumer.subscribe(topic, tagsString);
			log.info("[subscribe] success:"+topic+","+tagsString);
		} catch (MQClientException e) {
			e.printStackTrace();
		}

    }
}
