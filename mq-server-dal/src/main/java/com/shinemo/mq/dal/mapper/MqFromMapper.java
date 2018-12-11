package com.shinemo.mq.dal.mapper;


import com.shinemo.mq.client.message.domain.MqFrom;
import com.shinemo.mq.client.message.domain.MqFromQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface MqFromMapper extends BaseMapper<MqFromQuery,MqFrom>{
}
