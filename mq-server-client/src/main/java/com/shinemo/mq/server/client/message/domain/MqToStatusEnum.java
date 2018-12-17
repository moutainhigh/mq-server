package com.shinemo.mq.server.client.message.domain;


import com.shinemo.mq.server.client.common.entity.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MqToStatusEnum implements BaseEnum<MqToStatusEnum> {
	
	NORMAL("正常",1),
    DELETE("删除",0),
    ;
	
    private @Getter
    final String name;
    private @Getter
    final int id;
    
	public static MqToStatusEnum getById(int id) {
		MqToStatusEnum[] enums = MqToStatusEnum.values();
        for (MqToStatusEnum e : enums) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("not support");
    }
}
