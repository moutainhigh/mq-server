package com.shinemo.mq-server.client.user.query;

import com.shinemo.mq-server.client.common.query.Query;
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
public class UserInfoQuery extends Query {
    private Long id;
    private String name;
    private Integer age;
}
