package com.shinemo.mq.client.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 校验工具类 类似Spring自带的Assert
 */
public class AssertUtil {

    /**
     * 判断字符串是否为空 如果为空则抛出异常
     * @param text text
     * @param message message
     */
    public static void notNullString(String text, String message) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断map是否为空 如果为空抛出异常
     * @param map map
     * @param message message
     */
    public static void notNullMap(Map map, String message) {
        if (map == null || map.size()== 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 对象不为空 如果为空抛出异常
     * @param object
     * @param message
     */
    public static void notNullObject(Object object, String message) {
        if (object == null ) {
            throw new IllegalArgumentException(message);
        }
    }
}
