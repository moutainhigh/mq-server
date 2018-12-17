package com.shinemo.mq.server.client.message.domain;


import com.shinemo.mq.server.client.common.entity.BaseDO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * 我发出的消息
 *
 */
@Getter @Setter
public class MqFrom extends BaseDO {


    private static final long serialVersionUID = 6570764791410403814L;
    private Long id;
	private String topic;
	private String tags;
	private String body;
	private Date gmtCreate;
	private Date gmtModified;
	private String bizName;
	private MqFromStatusEnum mqFromStatus;
	private Integer errorTimes;
	
}
