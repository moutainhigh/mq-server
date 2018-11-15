package com.shinemo.mq-server.test.dal.user.wrapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zhangyan on 15/11/2018.
 */
@SpringBootApplication
@MapperScan(basePackages = {
        "com.shinemo.mq-server.dal.user.mapper"
})
@ComponentScan(basePackages = {
        "com.shinemo.mq-server.dal.user.wrapper",
})
public class ApplicationTest {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationTest.class, args);
    }
}
