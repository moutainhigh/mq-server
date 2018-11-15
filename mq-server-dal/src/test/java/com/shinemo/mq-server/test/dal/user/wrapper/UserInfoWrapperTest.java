package com.shinemo.mq-server.test.dal.user.wrapper;

import com.shinemo.mq-server.client.user.query.UserInfoQuery;
import com.shinemo.mq-server.dal.user.mapper.UserInfoMapper;
import com.shinemo.mq-server.dal.user.wrapper.UserInfoWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by zhangyan on 15/11/2018.
 */
@MybatisTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserInfoWrapperTest {
    @Resource
    private UserInfoWrapper userInfoWrapper;
    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    public void findUserInfoOrderByName() {
        Integer pageSize = null;
        Integer currentPage = null;
        Boolean asc = null;
        System.out.printf("pageSize=%s, currentPage=%s, asc=%s, result=%s%n",
                pageSize,
                currentPage,
                asc,
                userInfoWrapper.findUserInfoOrderByName(pageSize, currentPage, asc)
        );
    }

    @Test
    public void randomUpdateUsers() {
        UserInfoQuery query = new UserInfoQuery();
        String before = String.format("param=%s, result=%s%n", query, userInfoMapper.find(query));
        System.out.printf(before);
        Integer pageSize = null;
        Integer currentPage = null;
        Boolean asc = null;
        try {
            System.out.printf("pageSize=%s, currentPage=%s, asc=%s, result=%s%n",
                    pageSize,
                    currentPage,
                    asc,
                    userInfoWrapper.randomUpdateUsers(pageSize, currentPage, asc)
            );
        } catch (Exception e){
            e.printStackTrace();
        }
        String after = String.format("param=%s, result=%s%n", query, userInfoMapper.find(query));
        System.out.println(after.equals(before));
    }
}
