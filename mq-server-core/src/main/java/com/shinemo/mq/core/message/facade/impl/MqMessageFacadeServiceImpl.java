package com.shinemo.mq.core.message.facade.impl;

import com.shinemo.mq.client.common.list.ListWrapper;
import com.shinemo.mq.client.common.result.Result;
import com.shinemo.mq.client.common.result.ResultFactory;
import com.shinemo.mq.client.message.domain.MqFrom;
import com.shinemo.mq.client.message.domain.MqFromQuery;
import com.shinemo.mq.client.message.domain.MqTo;
import com.shinemo.mq.client.message.domain.MqToQuery;
import com.shinemo.mq.client.message.facade.MqMessageFacadeService;
import com.shinemo.mq.dal.wrapper.MqFromWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("mqMessageFacadeService")
@Slf4j
public class MqMessageFacadeServiceImpl implements MqMessageFacadeService {

    @Resource
    private MqFromWrapper mqFromWrapper;

    @Override
    public Result<MqFrom> insertMqFrom(MqFrom from) {
        return mqFromWrapper.insert(from);
    }

    @Override
    public Result<Void> updateMqFrom(MqFrom from) {
        Result<MqFrom> rs = mqFromWrapper.update(from);
        if(rs.hasValue()){
            return ResultFactory.success();
        }else{
            return ResultFactory.error(rs.getError());
        }
    }

    @Override
    public Result<ListWrapper<MqFrom>> findMqFrom(MqFromQuery query) {
        return mqFromWrapper.find(query);
    }

    @Override
    public Result<MqFrom> getMqFrom(MqFromQuery query) {
        return mqFromWrapper.get(query);
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
