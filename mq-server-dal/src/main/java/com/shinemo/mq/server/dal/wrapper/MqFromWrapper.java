package com.shinemo.mq.server.dal.wrapper;


import com.shinemo.mq.server.client.message.domain.MqFrom;
import com.shinemo.mq.server.client.message.domain.MqFromQuery;
import com.shinemo.mq.server.dal.mapper.BaseMapper;
import com.shinemo.mq.server.dal.mapper.MqFromMapper;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MqFromWrapper extends Wrapper<MqFromQuery,MqFrom>{

    @Resource
    private MqFromMapper mqFromMapper;

    @Override
    protected BaseMapper<MqFromQuery, MqFrom> getMapper() {
        return this.mqFromMapper;
    }
}
