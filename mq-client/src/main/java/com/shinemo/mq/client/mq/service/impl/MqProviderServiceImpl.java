package com.shinemo.mq.client.mq.service.impl;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.shinemo.mq.client.common.utils.AssertUtil;
import com.shinemo.mq.client.mq.service.MqProviderService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;

@Slf4j
@Getter
@Setter
public class MqProviderServiceImpl implements MqProviderService{


    /**
     * 生产者
     */
    private DefaultMQProducer producer = null;
    /**
     * 地址列表,若是集群用;作为地址的分隔符
     */
    private String nameSrcAddr;
    /**
     * 默认ip@pid(pid代表jvm名字) 同一jvm 如果要往不同集群发送消息 建议设置instanceName
     * 不设置的话 producer内部只有一个实例
     */
    private String instanceName;
    /**
     * 生产组
     */
    private String producerGroup;
    /**
     * 超时时间
     */
    private int sendMsgTimeout;
    /**
     * 重试次数
     */
    private int retryTimesWhenSendFailed;
    /**
     * 业务名称 一般配置应用名称
     */
    private String bizName;
    /**
     * mq消息统一放在同一个地方保管
     * 如果跨集群 走httpRpc调用
     */
    private boolean isRpcHttp;


    @Override
    public void init() {

        AssertUtil.notNullString(nameSrcAddr,"nameSrvAddr is null");
        AssertUtil.notNullString(producerGroup,"producerGroup is null");
        AssertUtil.notNullString(bizName,"bizName is null");
        producer = new DefaultMQProducer(producerGroup);
        try {
            producer.setNamesrvAddr(nameSrcAddr);
            if(StringUtils.isNotBlank(instanceName)){
                producer.setInstanceName(instanceName);
            }
            producer.setProducerGroup(producerGroup);
            if(sendMsgTimeout>0){
                producer.setSendMsgTimeout(sendMsgTimeout);
            }
            if(retryTimesWhenSendFailed>0){
                producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
            }
            if(isRpcHttp){
                //TODO producer变成json参数
            }else{
                producer.start();
            }
            log.error("mq producer start success:" + producerGroup + "," + nameSrcAddr + "," + instanceName+","+sendMsgTimeout+","+retryTimesWhenSendFailed);
        } catch (Exception e) {
            log.error("mq producer start error:" + producerGroup + "," + nameSrcAddr + "," + instanceName+","+sendMsgTimeout+","+retryTimesWhenSendFailed,e);
        }
    }

    @Override
    public void shutdown() {
        if (producer != null) {
            producer.shutdown();
            log.error("mq producer shutdown success:" + producerGroup + "," + nameSrcAddr + "," + instanceName+","+sendMsgTimeout+","+retryTimesWhenSendFailed);
        }
    }

    @Override
    public SendResult send(String topic, String tags, String body, MessageQueueSelector selector, Object selectorId) {
        String loggerString = MessageFormat.format("Topic={0},Tags={1},body={2}", topic, tags, body);
        SendResult sendResult = null;
        if(isRpcHttp){
            //
        }
        try{
            Message message = new Message();
            message.setTopic(topic);
            message.setTags(tags);
            message.setBody(body.getBytes("utf-8"));
            if(selector==null){
                sendResult = producer.send(message);
            }else{
                sendResult = producer.send(message,selector,selectorId);
            }
            if(sendResult == null || sendResult.getSendStatus() != SendStatus.SEND_OK){
                log.error("send fail:"+sendResult+","+loggerString);
                //TODO 插入数据库 发送异步线程
            }else{
                log.debug("send msg success,messageId="+sendResult.getMsgId()+","+loggerString);
            }
        }catch(Exception e){
            log.info("send exception:"+sendResult+","+loggerString,e);
            //TODO 插入数据库线程
        }
        return sendResult;
    }

    @Override
    public SendResult send(String topic, String tags, String body){
        return null;
    }

    @Override
    public SendResult retry(String topic, String tags, String body, MessageQueueSelector selector, Object selectorId) {
        return null;
    }

    @Override
    public SendResult retry(String topic, String tags, String body) {
        return null;
    }
}
