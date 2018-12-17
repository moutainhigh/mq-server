package com.shinemo.mq.server.client.common.result;

import com.shinemo.mq.server.client.common.error.ErrorWrapper;

import lombok.Data;

/**
 * Created by zhangyan on 15/11/2018.
 */
@Data
public class Result<Value> {
    private boolean success;
    private Value data;
    private ErrorWrapper error;

    Result(boolean success) {
        this(success, null, null);
    }

    Result(boolean success, Value data) {
        this(success, data, null);
    }

    Result(boolean success, Value data, ErrorWrapper error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public boolean hasValue() {
        return success && notNull();
    }

    public boolean notNull() {
        return data != null;
    }

}
