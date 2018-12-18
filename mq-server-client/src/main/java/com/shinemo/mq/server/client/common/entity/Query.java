package com.shinemo.mq.server.client.common.entity;

public interface Query {
	boolean isPageEnable();

	Long getStartRow();

	Long getCurrentPage();

	Long getPageSize();

}
