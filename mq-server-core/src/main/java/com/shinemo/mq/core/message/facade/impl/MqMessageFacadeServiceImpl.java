package com.shinemo.mq.core.message.facade.impl;

import com.shinemo.mq.client.common.list.ListWrapper;
import com.shinemo.mq.client.common.result.Result;
import com.shinemo.mq.client.message.domain.MqFrom;
import com.shinemo.mq.client.message.domain.MqFromQuery;
import com.shinemo.mq.client.message.domain.MqTo;
import com.shinemo.mq.client.message.domain.MqToQuery;
import com.shinemo.mq.client.message.facade.MqMessageFacadeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MqMessageFacadeServiceImpl implements MqMessageFacadeService {


    @Override
    public Result<MqFrom> insertMqFrom(MqFrom from) {
        return null;
    }

    @Override
    public Result<Void> updateMqFrom(MqFrom from) {
        return null;
    }

    @Override
    public Result<ListWrapper<MqFrom>> findMqFrom(MqFromQuery query) {
        return null;
    }

    @Override
    public Result<MqFrom> getMqFrom(MqFromQuery query) {
        return null;
    }

    @Override
    public Result<MqTo> insertMqTo(MqTo from) {
        return null;
    }

    @Override
    public Result<Void> updateMqTo(MqTo from) {
        return null;
    }

    @Override
    public Result<ListWrapper<MqTo>> findMqTo(MqToQuery query) {
        return null;
    }

    @Override
    public Result<MqTo> getMqFrom(MqToQuery query) {
        return null;
    }
}
