package com.shinemo.mq-server.dal.common.mapper;

import com.shinemo.mq-server.client.common.entity.Entity;
import com.shinemo.mq-server.client.common.model.Model;
import com.shinemo.mq-server.client.common.query.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangyan on 15/11/2018.
 */
public interface Mapper<E extends Entity, M extends Model, Q extends Query> {
    long count(Q query);

    List<M> find(Q query);

    M get(Q query);

    long insert(E value);

    long batchInsert(List<E> value);

    long update(E value);

    long delete(E value);

    void create(@Param("tableName") String tableName);
}
