package com.pattern.bridge;

/**
 * 具体实现化角色：APP 推送渠道。
 */
public class PushChannel implements MessageChannel {

    @Override
    public void send(String recipient, String content) {
        System.out.println("[PUSH] 推送至设备 " + recipient + "，内容: " + content);
    }
}
