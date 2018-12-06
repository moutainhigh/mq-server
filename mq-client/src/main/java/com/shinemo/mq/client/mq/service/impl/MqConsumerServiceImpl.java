package com.shinemo.mq.client.mq.service.impl;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.shinemo.mq.client.mq.service.MqConsumerService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

@Slf4j
@Getter
@Setter
public class MqConsumerServiceImpl implements MqConsumerService{


    private static final String SPLIT_STR = "\\||";
    private static final String STR = "||";


    private String consumerGroupName;
    private String nameSrvAddr;
    private String instanceName;
    private Map<String, Set<String>> topicAndSetTags;
    private MessageListener messageListener;
    private String bizName;
    private boolean checkRepeatMessage;
    private boolean clustering = true;//集群模式
    private DefaultMQPushConsumer mqPushConsumer;


    @Override
    public void init() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void subscribe(String topic, Set<String> tags) {

    }
}
