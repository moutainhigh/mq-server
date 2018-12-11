package com.shinemo.mq.client.event;

import com.shinemo.mq.client.common.entity.BaseDO;
import com.shinemo.mq.client.message.domain.MqFrom;
import com.shinemo.mq.client.message.facade.MqMessageFacadeService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MqDbEvent extends BaseDO {

    private MqMessageFacadeService mqMessageFacadeService;
    private MqFrom mqFrom;
    private Integer type;
}
