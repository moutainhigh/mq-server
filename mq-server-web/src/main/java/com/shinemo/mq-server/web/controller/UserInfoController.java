package com.shinemo.mq-server.web.controller;

import com.shinemo.mq-server.client.common.list.ListWrapper;
import com.shinemo.mq-server.client.common.result.Result;
import com.shinemo.mq-server.core.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyan on 15/11/2018.
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "/findUserNameByDesc", method = RequestMethod.GET)
    public Result<ListWrapper<String>> findUserNameByDesc(
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "currentPage", required = false) Integer currentPage
    ) {
        return userInfoService.findUserNameByDesc(pageSize, currentPage);
    }

    @RequestMapping(value = "/server/time", method = RequestMethod.GET)
    public Date serverTime() {
        return new Date();
    }
}
