package com.shinemo.mq-server.test.core.user.service;

import com.shinemo.mq-server.core.user.service.UserInfoService;
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
public class UserInfoServiceTest {

    @Resource
    private UserInfoService userInfoService;

    @Test
    public void test(){
        System.out.println(userInfoService);
    }
}
