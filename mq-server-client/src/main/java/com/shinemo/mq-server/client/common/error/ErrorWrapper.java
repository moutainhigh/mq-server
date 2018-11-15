package com.shinemo.mq-server.client.common.error;

import lombok.Data;

/**
 * Created by zhangyan on 15/11/2018.
 */
@Data
public class ErrorWrapper {
    private long code;
    private String name;
    private String msg;

    public ErrorWrapper() {
        this(200, "EMPTY", "EMPTY");
    }

    public ErrorWrapper(long code, String name, String msg) {
        this.code = code;
        this.name = (name == null || name.trim().equals("")) ? "EMPTY" : name;
        this.msg = (msg == null || msg.trim().equals("")) ? "EMPTY" : msg;
    }

    public String toJson(){
        return String.format("{\"code\":%s,\"name\":\"%s\",\"msg\":\"%s\"}", code, name, msg);
    }
}
