package com.shinemo.mq.server.client.message.domain;


import com.shinemo.mq.server.client.common.entity.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MqFromStatusEnum implements BaseEnum<MqFromStatusEnum> {
	
	WAIT_SEND("等待发送",0),
    SNED_SUCCESS("发送成功",1),
    SNED_FAILURE("发送失败",2),
    ;
	
    private @Getter
    final String name;
    private @Getter
    final int id;
    
	public static MqFromStatusEnum getById(int id) {
		MqFromStatusEnum[] enums = MqFromStatusEnum.values();
        for (MqFromStatusEnum e : enums) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("not support");
    }
}
