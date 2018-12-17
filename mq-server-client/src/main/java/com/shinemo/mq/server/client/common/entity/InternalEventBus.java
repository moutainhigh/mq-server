package com.shinemo.mq.server.client.common.entity;

import com.google.common.eventbus.AsyncEventBus;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class InternalEventBus {
	
	private final Executor poolExecutor = Executors.newFixedThreadPool(5);

	/**
	 * 异步的消息通道，异步执行注意确保事件处理方法的同步
	 */
	private final AsyncEventBus asyncEventBus = new AsyncEventBus("_internel_async_", poolExecutor);

	public void post(Object event) {
		asyncEventBus.post(event);
	}

	public void register(Object listener) {
		asyncEventBus.register(listener);
	}

	public void unregister(Object listener) {
		asyncEventBus.unregister(listener);
	}

}
