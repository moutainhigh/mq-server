package com.shinemo.mq.server.client.message.domain;


import com.shinemo.mq.server.client.common.entity.BaseQuery;
import lombok.Getter;
import lombok.Setter;

public class MqFromQuery extends BaseQuery {
	


	private @Getter @Setter Long id;
	private @Getter @Setter MqFromStatusEnum mqFromStatus;
	private @Getter @Setter String bizName;

}
