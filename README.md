# 使用方式
mq统一管理的工程 

## 改动点
* mq消息统一入库地址
* 增加定时重试机制
* 支持垮集群发送mq消息

## 发送方使用方式
* 配置方式

```xml
<bean id="mqServerSendFacadeService" class="com.shinemo.jce.spring.AaceConsumerBean" init-method="init">
    <property name="interfaceName" value="com.shinemo.mq.server.client.send.facade.MqServerSendFacadeService" />
    <property name="proxy" value="mq-server" />
    <!-- <property name="aceType" value="http"/> -->
</bean>
```

* rpc调用

```java
void send(String topic,String tags,String body,MqProviderConfig config)
```
config参数解释

| 名称 | 属性 | 是否必须 |解释 |
| ------ | ------ | ------ |
|bizName|string|是|业务名称 |
|producerGroupName|string|是|生产组名称|
|toAppType|int|是|发往哪个集群
|MessageQueueSelector|MessageQueueSelector|否|消息选择器|
|selectorId|int|否|消息选择id|
|||否|后续会支持重试次数,以及频率等后续需要迭代的参数|

*兼容老的client配置方式

--- 
## 接收方使用方式

* 配置方式

```xml
<bean id="mqConsumerService" class="com.shinemo.mq.service.impl.MqConsumerServiceImpl" init-method="init">
	<property name="consumerGroupName" value="${buycenter.mq.group}"/>
	<property name="nameSrvAddr" value="${tc.mq.addr}" />
	<property name="topicAndSetTags">
		<map>
			<entry key="${tc.mq.topic}">
				<set>
					<value>${purchase.mq.order.tags}</value>
					<value>${shinemo.mq.tag.payment}</value>
					<value>${shinemo.mq.tag.refund}</value>
				</set>
			</entry>
		</map>
	</property>
	<! --可以省略<property name="messageListener" ref="mqMessageListenerOrderly" /> -->
</bean>
```
* 并且实现MqMessageConsumerService 接口




