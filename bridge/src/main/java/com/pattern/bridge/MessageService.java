package com.pattern.bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息服务：客户端，负责组合抽象与实现并发送消息。
 *
 * <p>在 Spring 项目中，通常根据业务场景注入不同的 Message 和 Channel Bean。</p>
 */
public class MessageService {

    private final List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void sendAll(String recipient) {
        for (Message message : messages) {
            message.send(recipient);
        }
    }
}
