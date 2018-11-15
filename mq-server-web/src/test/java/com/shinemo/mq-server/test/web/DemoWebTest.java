package com.shinemo.mq-server.test.web;

import com.shinemo.mq-server.web.controller.UserInfoController;
import com.shinemo.mq-server.web.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by zhangyan on 15/11/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        value = {"server.port=8080"},
        classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class DemoWebTest {

    @Resource
    private UserInfoController userInfoController;

    @Test
    public void test() {
        System.out.println(userInfoController);
    }
}
