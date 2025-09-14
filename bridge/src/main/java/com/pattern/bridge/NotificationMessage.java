package com.pattern.bridge;

/**
 * 扩展抽象化角色：系统通知消息。
 */
public class NotificationMessage extends Message {

    private final String title;
    private final String body;

    public NotificationMessage(MessageChannel channel, String title, String body) {
        super(channel);
        this.title = title;
        this.body = body;
    }

    @Override
    public void send(String recipient) {
        String content = String.format("【%s】%s", title, body);
        channel.send(recipient, content);
    }
}
