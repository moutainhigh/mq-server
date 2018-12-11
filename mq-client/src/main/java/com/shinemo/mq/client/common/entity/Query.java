package com.shinemo.mq.client.common.entity;

public interface Query {
	boolean isPageEnable();

	Long getStartRow();

	Long getCurrentPage();

	Long getPageSize();

	Long getTotalItem();

	void putTotalItem(Long totalItem);
}
