package com.shinemo.mq.client.listener;

import com.google.common.eventbus.Subscribe;
import com.shinemo.mq.client.common.entity.InternalEventBus;
import com.shinemo.mq.client.event.MqDbEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MqDbEventListener {

    private InternalEventBus internalEventBus;

    public void init(){
        internalEventBus.register(this);
    }

    @Subscribe
    public void onEvent(MqDbEvent event) {
        // TODO 调用rpc接口
        // 失败插入缓存队列重试
        // TODO
    }

}
