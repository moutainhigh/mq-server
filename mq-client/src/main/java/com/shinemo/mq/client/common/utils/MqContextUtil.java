package com.shinemo.mq.client.common.utils;



import java.util.HashMap;

public class MqContextUtil {


    private static HashMap<String,Object> map;

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


}
