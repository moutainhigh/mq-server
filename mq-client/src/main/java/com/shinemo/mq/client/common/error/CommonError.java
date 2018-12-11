package com.shinemo.mq.client.common.error;

public interface CommonError {

    ErrorWrapper MAPPER_NULL_COUNT = new ErrorWrapper(100001, "mapper null", "mapper is null");
    ErrorWrapper QUERY_NULL_COUNT = new ErrorWrapper(100002, "query is null", "query is null");
    ErrorWrapper SQL_ERROR_COUNT = new ErrorWrapper(100003, "SQL_ERROR_COUNT", "SQL_ERROR_COUNT");
    ErrorWrapper SQL_ERROR_FINND = new ErrorWrapper(100004, "SQL_ERROR_FINND", "SQL_ERROR_FINND");
}
