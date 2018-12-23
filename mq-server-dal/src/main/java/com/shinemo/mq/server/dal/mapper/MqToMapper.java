package com.shinemo.mq.server.dal.mapper;


import com.shinemo.mq.server.client.message.domain.MqTo;
import com.shinemo.mq.server.client.message.domain.MqToQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface MqToMapper extends BaseMapper<MqToQuery,MqTo>{
}
