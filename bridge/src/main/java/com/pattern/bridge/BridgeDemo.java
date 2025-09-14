package com.pattern.bridge;

/**
 * 桥接模式客户端入口。
 */
public class BridgeDemo {

    public static void main(String[] args) {
        // 创建不同的发送渠道
        MessageChannel sms = new SmsChannel();
        MessageChannel email = new EmailChannel();
        MessageChannel push = new PushChannel();

        // 创建不同的消息类型，并任意组合渠道
        Message verificationSms = new VerificationMessage(sms, "123456", 5);
        Message verificationEmail = new VerificationMessage(email, "654321", 10);
        Message marketingPush = new MarketingMessage(push, "全场 5 折", "https://example.com/sale");
        Message notificationEmail = new NotificationMessage(email, "订单发货", "您的订单已发货，请留意查收。");

        MessageService service = new MessageService();
        service.addMessage(verificationSms);
        service.addMessage(verificationEmail);
        service.addMessage(marketingPush);
        service.addMessage(notificationEmail);

        service.sendAll("user@example.com");
    }
}
