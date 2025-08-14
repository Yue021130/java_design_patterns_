package com.pattern.abstractfactory;

/**
 * 抽象工厂：多云厂商服务族创建入口。
 *
 * <p>抽象工厂模式解决的是“产品族”创建问题：同一云厂商的对象存储、短信、消息队列
 * 往往有统一的认证方式、Region 配置、SDK 依赖，应当作为一个整体被创建和切换。</p>
 */
public interface CloudServiceFactory {

    ObjectStorage createObjectStorage();

    SmsService createSmsService();

    MessageQueue createMessageQueue();
}
