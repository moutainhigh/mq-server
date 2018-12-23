package com.shinemo.mq.server.client.common.utils;



import java.util.HashMap;

import com.alibaba.rocketmq.common.message.MessageExt;
import com.shinemo.jce.common.config.AceType;
import com.shinemo.jce.spring.AaceConsumerBean;
import com.shinemo.mq.server.client.message.domain.MqTo;
import com.shinemo.mq.server.client.message.domain.MqToStatusEnum;
import com.shinemo.mq.server.client.message.facade.MqMessageFacadeService;
import com.shinemo.mq.server.client.send.facade.MqSendFacadeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MqContextUtil {


    private static HashMap<String,Object> map = new HashMap<>();

    @SuppressWarnings("unchecked")
	public static  final  <T>  T getBeanAndGenerateIfNotExist(String beanName, Class<T> classBean){
        T t = null;
        if(map.get(beanName) != null){
            t = (T)map.get(beanName);
        }
        synchronized(MqContextUtil.class){
            try {
                t = classBean.newInstance();
            } catch (Exception e) {
                log.error("[getBeanAndGenerateIfNotExist] error",e);
            }
        }
        return t;
    }

    public static final MqMessageFacadeService getMessageFacadeService(boolean isNeedHttp){
        MqMessageFacadeService mqMessageFacadeService = (MqMessageFacadeService)map.get("mqMessageFacadeService");
        if(mqMessageFacadeService == null){
            synchronized(MqContextUtil.class){
                AaceConsumerBean consumerBean = new AaceConsumerBean();
                consumerBean.setInterfaceClazz(MqMessageFacadeService.class);
                consumerBean.setProxy("mq-server");
                if(isNeedHttp){
                    consumerBean.setAceType(AceType.HTTP.getName());
                }
                map.put("mqMessageFacadeService",consumerBean);
                try {
                    mqMessageFacadeService = (MqMessageFacadeService) consumerBean.getObject();
                } catch (Exception e) {
                	log.error("[getMessageFacadeService] error",e);
                }
            }
        }
        return mqMessageFacadeService;
    }


	public static final MqSendFacadeService getSendFacadeServiceByAppType() {
        MqSendFacadeService mqSendFacadeService = (MqSendFacadeService)map.get("mqSendFacadeService");
        if(mqSendFacadeService == null){
            synchronized(MqContextUtil.class){
                AaceConsumerBean consumerBean = new AaceConsumerBean();
                consumerBean.setInterfaceClazz(MqSendFacadeService.class);
                consumerBean.setProxy("MyAceProxy");
                consumerBean.setAceType(AceType.HTTP.getName());
                map.put("mqSendFacadeService",consumerBean);
                try {
                    mqSendFacadeService = (MqSendFacadeService) consumerBean.getObject();
                } catch (Exception e) {
                	log.error("[getSendFacadeServiceByAppType] error",e);
                }
            }
        }
        return mqSendFacadeService;
	}


    public static MqTo initMqTo(MessageExt msg, String bizName) {
        MqTo mqTo = new MqTo();
        mqTo.setBizName(bizName);
        mqTo.setBody(new String(msg.getBody()));
        mqTo.setMessageId(msg.getMsgId());
        mqTo.setMqToStatus(MqToStatusEnum.NORMAL);
        mqTo.setTags(msg.getTags());
        mqTo.setTopic(msg.getTopic());
        return mqTo;
    }
}
