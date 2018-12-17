package com.shinemo.mq.client.common.utils;



import java.util.HashMap;

import com.shinemo.jce.common.config.AceType;
import com.shinemo.jce.spring.AaceConsumerBean;
import com.shinemo.mq.client.message.facade.MqMessageFacadeService;
import com.shinemo.mq.server.client.send.facade.MqSendFacadeService;

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
                e.printStackTrace();
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
                    e.printStackTrace();
                }
            }
        }
        return mqMessageFacadeService;
    }


	public static final MqSendFacadeService getSendFacadeServiceByAppType(Integer appType) {
		// TODO Auto-generated method stub
		return null;
	}


}
