package com.shinemo.mq-server.dal.user.wrapper;

import com.shinemo.mq-server.client.common.list.ListWrapper;
import com.shinemo.mq-server.client.common.result.Result;
import com.shinemo.mq-server.client.user.domain.UserInfoDomain;

/**
 * Created by zhangyan on 15/11/2018.
 */
public interface UserInfoWrapper {
    Result<ListWrapper<UserInfoDomain>> findUserInfoOrderByName(Integer pageSize, Integer currentPage, Boolean asc);

    Result<Integer> randomUpdateUsers(Integer pageSize, Integer currentPage, Boolean asc);
}
