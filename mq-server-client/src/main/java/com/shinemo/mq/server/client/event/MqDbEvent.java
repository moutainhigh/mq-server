package com.shinemo.mq.server.client.event;

import com.shinemo.mq.server.client.common.entity.BaseDO;
import com.shinemo.mq.server.client.message.domain.MqFrom;
import com.shinemo.mq.server.client.message.domain.MqTo;
import com.shinemo.mq.server.client.message.facade.MqMessageFacadeService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MqDbEvent extends BaseDO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7626749550554385009L;
	private MqMessageFacadeService mqMessageFacadeService;
    private MqFrom mqFrom;
    private MqTo mqTo;
    private Integer type;
}
