package com.shinemo.mq-server.dal.user.wrapper.impl;

import com.shinemo.mq-server.client.common.error.ErrorWrapper;
import com.shinemo.mq-server.client.common.exception.DatabaseSqlExecuteException;
import com.shinemo.mq-server.client.common.list.ListWrapper;
import com.shinemo.mq-server.client.common.result.Result;
import com.shinemo.mq-server.client.common.result.ResultFactory;
import com.shinemo.mq-server.client.user.domain.UserInfoDomain;
import com.shinemo.mq-server.client.user.query.UserInfoQuery;
import com.shinemo.mq-server.dal.common.wrapper.MapperWrapper;
import com.shinemo.mq-server.dal.user.mapper.UserInfoMapper;
import com.shinemo.mq-server.dal.user.wrapper.UserInfoWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * Created by zhangyan on 15/11/2018.
 */
@Service("userInfoWrapper")
public class UserInfoWrapperImpl implements UserInfoWrapper {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Result<ListWrapper<UserInfoDomain>> findUserInfoOrderByName(Integer pageSize, Integer currentPage, Boolean asc) {
        UserInfoQuery query = new UserInfoQuery();
        query.setPageSize(pageSize);
        query.setCurrentPage(currentPage);
        query.putOrderBy("name", asc);
        return MapperWrapper.find(userInfoMapper, query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> randomUpdateUsers(Integer pageSize, Integer currentPage, Boolean asc) {
        UserInfoQuery query = new UserInfoQuery();
        query.setPageSize(pageSize);
        query.setCurrentPage(currentPage);
        query.putOrderBy("name", asc);
        Result<ListWrapper<UserInfoDomain>> usersResult = MapperWrapper.find(userInfoMapper, query);
        if (!usersResult.hasValue()) {
            return ResultFactory.error(usersResult);
        }
        List<UserInfoDomain> list = usersResult.getData().getRows();
        Random random = new Random();
        int age = 0;
        int updateCount = 0;
        int updateSuccessCount = 0;
        for (UserInfoDomain userInfoDomain : list) {
            age = random.nextInt(20) + 10;
            if (age > 20) {
                userInfoDomain.setAge(age);
                Result<UserInfoDomain> userResult = MapperWrapper.update(userInfoMapper, userInfoDomain, new ErrorWrapper());
                if (userResult.isSuccess()) {
                    updateCount++;
                    if (userResult.notNull()) {
                        updateSuccessCount++;
                    }
                }
            }
        }
        System.out.printf("%s,%s%n", updateCount, updateSuccessCount);
        if(updateSuccessCount > 0){
            throw new DatabaseSqlExecuteException(new ErrorWrapper(0, "TEST_TRANSACTION", "test transaction"));
        }
        return ResultFactory.success(updateSuccessCount);
    }
}
