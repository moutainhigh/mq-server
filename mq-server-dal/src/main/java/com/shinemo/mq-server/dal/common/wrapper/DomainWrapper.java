package com.shinemo.mq-server.dal.common.wrapper;

import com.shinemo.mq-server.client.common.domain.Domain;
import com.shinemo.mq-server.client.common.query.Query;

/**
 * Created by zhangyan on 15/11/2018.
 */
public abstract class DomainWrapper<D extends Domain, Q extends Query> extends Wrapper<D, D, Q> {
}