package com.shinemo.mq.server.client.listener;

import com.google.common.eventbus.Subscribe;
import com.shinemo.mq.server.client.common.entity.InternalEventBus;
import com.shinemo.mq.server.client.event.MqDbEvent;
import com.shinemo.mq.server.client.message.facade.MqMessageFacadeService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class MqDbEventListener {

    private InternalEventBus internalEventBus;

    public void init(){
        internalEventBus.register(this);
    }

    @Subscribe
    public void onEvent(MqDbEvent event) {
        log.info("[mqDbEvent] :{}", event);
        MqMessageFacadeService mqMessageFacadeService = event.getMqMessageFacadeService();
        // TODO 插入异常的放入缓存队列重试
        //插入的时候先查询已经存在不插入
    	if(event.getMqFrom() != null) {
            mqMessageFacadeService.insertMqFrom(event.getMqFrom());
    	}else {
            mqMessageFacadeService.insertMqTo(event.getMqTo());
    	}
    	
    }

}
