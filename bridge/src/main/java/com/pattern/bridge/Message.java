package com.pattern.bridge;

/**
 * 抽象化角色：消息。
 *
 * <p>持有一个 {@link MessageChannel} 引用，把具体发送委托给渠道实现。
 * 不同的消息子类负责组装自己的内容，但不需要关心通过什么渠道发送。</p>
 */
public abstract class Message {

    protected final MessageChannel channel;

    protected Message(MessageChannel channel) {
        this.channel = channel;
    }

    /**
     * 发送消息给指定接收者。
     */
    public abstract void send(String recipient);
}
