package com.pattern.bridge;

/**
 * 实现化角色：消息发送渠道。
 *
 * <p>桥接模式把“消息类型”和“发送渠道”两个维度解耦，
 * 这里定义的是发送渠道的契约。</p>
 */
public interface MessageChannel {

    /**
     * 发送消息。
     *
     * @param recipient 接收者（手机号 / 邮箱 / 设备 ID）
     * @param content   消息内容
     */
    void send(String recipient, String content);
}
