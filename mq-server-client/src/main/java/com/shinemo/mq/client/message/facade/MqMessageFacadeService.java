package com.shinemo.mq.client.message.facade;

import com.shinemo.mq.client.common.list.ListWrapper;
import com.shinemo.mq.client.common.result.Result;
import com.shinemo.mq.client.message.domain.MqFrom;
import com.shinemo.mq.client.message.domain.MqFromQuery;
import com.shinemo.mq.client.message.domain.MqTo;
import com.shinemo.mq.client.message.domain.MqToQuery;

/**
 * Mq消息Db message操作类
 */
public interface MqMessageFacadeService {

    /**
     * 插入生产的消息
     * @param from
     * @return
     */
    Result<MqFrom> insertMqFrom(MqFrom from);

    /**
     * 更新生产的消息
     * @param from
     * @return
     */
    Result<Void> updateMqFrom(MqFrom from);

    /**
     * 查询生产的消息 列表
     * @param query
     * @return
     */
    Result<ListWrapper<MqFrom>> findMqFrom(MqFromQuery query);

    /**
     * 查询生产的消息
     * @param query
     * @return
     */
    Result<MqFrom> getMqFrom(MqFromQuery query);

    /**
     * 插入消费的消息
     * @param mqTo
     * @return
     */
    Result<MqTo> insertMqTo(MqTo mqTo);

    /**
     * 更新的消息
     * @param mqTo
     * @return
     */
    Result<Void> updateMqTo(MqTo mqTo);

    /**
     * 查询消费的消息列表
     * @param query
     * @return
     */
    Result<ListWrapper<MqTo>> findMqTo(MqToQuery query);

    /**
     * 查询消费的消息
     * @param query
     * @return
     */
    Result<MqTo> getMqTo(MqToQuery query);


}
