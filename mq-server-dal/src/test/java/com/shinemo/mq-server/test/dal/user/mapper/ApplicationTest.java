package com.shinemo.mq-server.test.dal.user.mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by zhangyan on 15/11/2018.
 */
@SpringBootApplication
@MapperScan(basePackages = {
        "com.shinemo.mq-server.dal.user.mapper"
})
public class ApplicationTest {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationTest.class, args);
    }
}
