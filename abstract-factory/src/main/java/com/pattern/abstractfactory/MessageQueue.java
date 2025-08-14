package com.pattern.abstractfactory;

/**
 * 消息队列服务：抽象产品 C。
 */
public interface MessageQueue {

    void sendMessage(String topic, String message);

    String receiveMessage(String topic);
}
