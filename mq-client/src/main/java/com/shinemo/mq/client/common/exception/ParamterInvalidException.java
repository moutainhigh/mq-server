package com.shinemo.mq.client.common.exception;

import com.shinemo.mq.client.common.error.ErrorWrapper;


/**
 * Created by zhangyan on 15/11/2018.
 */
public class ParamterInvalidException extends BaseException {
    public ParamterInvalidException(ErrorWrapper errorWrapper) {
        super(errorWrapper);
    }

    public ParamterInvalidException(ErrorWrapper errorWrapper, Throwable cause) {
        super(errorWrapper, cause);
    }

    public ParamterInvalidException(ErrorWrapper errorWrapper, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errorWrapper, cause, enableSuppression, writableStackTrace);
    }
}
