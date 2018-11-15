package com.shinemo.mq-server.core.user.service;

import com.shinemo.mq-server.client.common.list.ListWrapper;
import com.shinemo.mq-server.client.common.result.Result;

/**
 * Created by zhangyan on 15/11/2018.
 */
public interface UserInfoService {
    Result<ListWrapper<String>> findUserNameByDesc(Integer pageSize, Integer currentPage);
}
