package com.shinemo.mq.server.dal.mapper;


import com.shinemo.mq.server.client.message.domain.MqFrom;
import com.shinemo.mq.server.client.message.domain.MqFromQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface MqFromMapper extends BaseMapper<MqFromQuery,MqFrom>{
}
