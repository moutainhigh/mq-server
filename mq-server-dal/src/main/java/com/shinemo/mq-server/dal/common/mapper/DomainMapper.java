package com.shinemo.mq-server.dal.common.mapper;

import com.shinemo.mq-server.client.common.domain.Domain;
import com.shinemo.mq-server.client.common.query.Query;

/**
 * Created by zhangyan on 15/11/2018.
 */
public interface DomainMapper<D extends Domain, Q extends Query> extends Mapper<D, D, Q> {
}
