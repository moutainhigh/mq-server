package com.shinemo.mq.client.common.exception;

import com.shinemo.mq.client.common.error.ErrorWrapper;

import lombok.Getter;

/**
 * Created by zhangyan on 15/11/2018.
 */
@Getter
public abstract class BaseException extends RuntimeException {
    private ErrorWrapper errorWrapper;

    public BaseException(ErrorWrapper errorWrapper) {
        super(toJson(errorWrapper));
        this.errorWrapper = errorWrapper;
    }

    public BaseException(ErrorWrapper errorWrapper, Throwable cause) {
        super(toJson(errorWrapper), cause);
        this.errorWrapper = errorWrapper;
    }

    public BaseException(ErrorWrapper errorWrapper, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(toJson(errorWrapper), cause, enableSuppression, writableStackTrace);
        this.errorWrapper = errorWrapper;
    }

    private static String toJson(ErrorWrapper errorWrapper) {
        return errorWrapper == null ? null : errorWrapper.toJson();
    }
}
