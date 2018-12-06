package com.shinemo.mq.client.common.entity;

import org.apache.commons.lang.builder.*;

import java.io.Serializable;

public class BaseDO implements Serializable, Cloneable, Comparable {


    private static final long serialVersionUID = -6255964206225566100L;

    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public int compareTo(Object o) {
        return CompareToBuilder.reflectionCompare(this, o);
    }
}
