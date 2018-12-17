package com.shinemo.mq.dal.mapper;


import com.shinemo.mq.client.message.domain.MqTo;
import com.shinemo.mq.client.message.domain.MqToQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface MqToMapper extends BaseMapper<MqToQuery,MqTo>{
}
