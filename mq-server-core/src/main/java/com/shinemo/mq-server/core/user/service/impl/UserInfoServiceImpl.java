package com.shinemo.mq-server.core.user.service.impl;

import com.shinemo.mq-server.client.common.list.ListWrapper;
import com.shinemo.mq-server.client.common.result.Result;
import com.shinemo.mq-server.client.common.result.ResultFactory;
import com.shinemo.mq-server.client.user.domain.UserInfoDomain;
import com.shinemo.mq-server.core.user.service.UserInfoService;
import com.shinemo.mq-server.dal.user.wrapper.UserInfoWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhangyan on 15/11/2018.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoWrapper userInfoWrapper;

    public Result<ListWrapper<String>> findUserNameByDesc(Integer pageSize, Integer currentPage) {
        Result<ListWrapper<UserInfoDomain>> result = userInfoWrapper.findUserInfoOrderByName(pageSize, currentPage, false);
        if (!result.hasValue()) {
            return ResultFactory.error(result);
        }
        ListWrapper<UserInfoDomain> value = result.getData();
        return ResultFactory.success(value.convert(val -> val.getName()));
    }
}
