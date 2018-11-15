package com.shinemo.mq-server.client.user.domain;

import  com.shinemo.mq-server.client.common.domain.Domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhangyan on 15/11/2018.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDomain implements Domain {
    private Long id;
    private String name;
    private Integer age;
}
