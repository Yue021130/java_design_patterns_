package com.pattern.bridge;

/**
 * 具体实现化角色：短信渠道。
 */
public class SmsChannel implements MessageChannel {

    @Override
    public void send(String recipient, String content) {
        System.out.println("[SMS] 发送至 " + recipient + "，内容: " + content);
    }
}
