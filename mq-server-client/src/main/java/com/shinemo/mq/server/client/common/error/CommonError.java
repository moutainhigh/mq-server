package com.shinemo.mq.server.client.common.error;

public interface CommonError {

    ErrorWrapper MAPPER_NULL_COUNT = new ErrorWrapper(100001, "mapper null", "mapper is null");
    ErrorWrapper QUERY_NULL_COUNT = new ErrorWrapper(100002, "query is null", "query is null");
    ErrorWrapper SQL_ERROR_COUNT = new ErrorWrapper(100003, "SQL_ERROR_COUNT", "SQL_ERROR_COUNT");
    ErrorWrapper SQL_ERROR_FINND = new ErrorWrapper(100004, "SQL_ERROR_FINND", "SQL_ERROR_FINND");
    ErrorWrapper ENTTY_NULL = new ErrorWrapper(100005, "ENTTY_NULL", "ENTTY_NULL");
    ErrorWrapper SQL_ERROR_INSERT = new ErrorWrapper(100006, "SQL_ERROR_INSERT", "SQL_ERROR_INSERT");
    ErrorWrapper SQL_ERROR_UPDATE = new ErrorWrapper(100007, "SQL_ERROR_UPDATE", "SQL_ERROR_UPDATE");
}
