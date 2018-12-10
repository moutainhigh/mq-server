package com.shinemo.mq.client.mq.service;

import com.alibaba.rocketmq.client.producer.MQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;

public interface MqProviderService {
    /**
     * 初始化方法
     */
    void init();
    /**
     * 关闭方法
     */
    void shutdown();

    /**
     * 发送消息伴随特定的消息队列
     * 例如订单系统同一订单号可以发送到同一队列
     * @param topic
     * @param tags
     * @param body
     * @param selector
     * @param selectorId
     * @return
     */
    SendResult send(String topic, String tags, String body, MessageQueueSelector selector, Object selectorId,boolean crossCluster,String url);

    /**
     * 发送消息
     * @param topic
     * @param tags
     * @param body
     * @return
     */
    SendResult send(String topic, String tags, String body);

    /**
     * 重试
     * @param topic
     * @param tags
     * @param body
     * @param selector
     * @param selectorId
     * @return
     */
    SendResult retry(String topic, String tags, String body, MessageQueueSelector selector, Object selectorId);

    /**
     * 重试
     * @param topic
     * @param tags
     * @param body
     * @return
     */
    SendResult retry(String topic, String tags, String body);
}
