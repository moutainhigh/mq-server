package com.shinemo.mq.server.client.message.domain;


import com.shinemo.mq.server.client.common.entity.BaseQuery;
import lombok.Getter;
import lombok.Setter;


public class MqToQuery extends BaseQuery {

	private static final long serialVersionUID = -2965882533176505094L;
	private @Getter @Setter Long id;
	private @Getter @Setter String messageId;
	private @Getter @Setter String bizName;
}
