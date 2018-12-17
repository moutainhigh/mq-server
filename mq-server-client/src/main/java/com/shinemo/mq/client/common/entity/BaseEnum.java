package com.shinemo.mq.client.common.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public interface BaseEnum<E extends Enum<E>> {
    @JsonValue
    int getId();

    String getName();
}
