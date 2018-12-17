package com.shinemo.mq.dal.wrapper;

import com.shinemo.mq.client.message.domain.MqTo;
import com.shinemo.mq.client.message.domain.MqToQuery;
import com.shinemo.mq.dal.mapper.BaseMapper;
import com.shinemo.mq.dal.mapper.MqToMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MqToWrapper extends Wrapper<MqToQuery,MqTo>{

    @Resource
    private MqToMapper mqToMapper;

    @Override
    protected BaseMapper<MqToQuery, MqTo> getMapper() {
        return this.mqToMapper;
    }
}
