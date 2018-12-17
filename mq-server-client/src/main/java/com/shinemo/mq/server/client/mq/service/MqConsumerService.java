package com.shinemo.mq.server.client.mq.service;

import java.util.Set;

public interface MqConsumerService {
    /**
     * 初始化方法
     */
    void init();
    /**
     *关闭方法
     */
    void shutdown();

    /**
     * @param topic 自定义的mq topic
     * @param tags tag的set集合
     */
    void subscribe(String topic, Set<String> tags);
}
