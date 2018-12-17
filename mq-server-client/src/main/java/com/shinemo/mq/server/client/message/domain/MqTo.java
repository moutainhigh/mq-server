package com.shinemo.mq.server.client.message.domain;


import com.shinemo.mq.server.client.common.entity.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 我收到的消息 
 */

@Getter @Setter
public class MqTo extends BaseDO {
	private static final long serialVersionUID = -6586269882495284170L;
	private  Long id;
	private String topic;
	private String tags;
	private String body;
	private  String messageId;
	private Date gmtCreate;
	private Date gmtModified;
	private String bizName;
	private MqToStatusEnum mqToStatus;
	
}
