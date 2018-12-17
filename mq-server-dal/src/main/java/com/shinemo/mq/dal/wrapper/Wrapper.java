package com.shinemo.mq.dal.wrapper;


import com.shinemo.mq.server.client.common.entity.BaseDO;
import com.shinemo.mq.server.client.common.entity.BaseQuery;
import com.shinemo.mq.server.client.common.error.ErrorWrapper;
import com.shinemo.mq.server.client.common.list.ListWrapper;
import com.shinemo.mq.server.client.common.result.Result;
import com.shinemo.mq.dal.mapper.BaseMapper;

/**
 * Created by harold on 03/08/2017.
 */
public abstract class Wrapper<Q extends BaseQuery, D extends BaseDO> {

    protected abstract BaseMapper<Q, D > getMapper();

    public Result<Long> count(Q query) {
        return MapperWrapper.count(getMapper(), query);
    }

    public Result<Long> count(Q query, ErrorWrapper error) {
        return MapperWrapper.count(getMapper(), query, error);
    }

    public Result<ListWrapper<D>> find(Q query) {
        return MapperWrapper.find(getMapper(), query);
    }

    public Result<ListWrapper<D>> find(Q query, ErrorWrapper error) {
        return MapperWrapper.find(getMapper(), query, error);
    }

    public Result<D> get(Q query) {
        return MapperWrapper.get(getMapper(), query);
    }

    public Result<D> get(Q query, ErrorWrapper error) {
        return MapperWrapper.get(getMapper(), query, error);
    }

    public Result<D> insert(D entity) {
        return MapperWrapper.insert(getMapper(), entity);
    }

    public Result<D> insert(D entity, ErrorWrapper error) {
        return MapperWrapper.insert(getMapper(), entity, error);
    }

    public Result<D> update(D entity) {
        return MapperWrapper.update(getMapper(), entity);
    }

    public Result<D> update(D entity, ErrorWrapper error) {
        return MapperWrapper.update(getMapper(), entity, error);
    }

}