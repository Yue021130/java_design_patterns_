package com.pattern.bridge;

/**
 * 具体实现化角色：邮件渠道。
 */
public class EmailChannel implements MessageChannel {

    @Override
    public void send(String recipient, String content) {
        System.out.println("[EMAIL] 发送至 " + recipient);
        System.out.println("主题: 系统通知");
        System.out.println("正文: " + content);
    }
}
