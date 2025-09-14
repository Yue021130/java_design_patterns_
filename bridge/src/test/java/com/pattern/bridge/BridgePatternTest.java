package com.pattern.bridge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BridgePatternTest {

    /**
     * 内存渠道：用于测试，记录发送历史。
     */
    static class InMemoryChannel implements MessageChannel {
        final List<String> history = new ArrayList<>();

        @Override
        public void send(String recipient, String content) {
            history.add(recipient + "|" + content);
        }
    }

    @Test
    void sameMessageTypeCanUseDifferentChannels() {
        InMemoryChannel sms = new InMemoryChannel();
        InMemoryChannel email = new InMemoryChannel();

        Message smsVerification = new VerificationMessage(sms, "111111", 5);
        Message emailVerification = new VerificationMessage(email, "222222", 5);

        smsVerification.send("13800138000");
        emailVerification.send("user@example.com");

        assertEquals(1, sms.history.size());
        assertTrue(sms.history.get(0).startsWith("13800138000|"));
        assertTrue(sms.history.get(0).contains("111111"));

        assertEquals(1, email.history.size());
        assertTrue(email.history.get(0).startsWith("user@example.com|"));
        assertTrue(email.history.get(0).contains("222222"));
    }

    @Test
    void differentMessageTypesCanUseSameChannel() {
        InMemoryChannel channel = new InMemoryChannel();

        Message verification = new VerificationMessage(channel, "333333", 5);
        Message marketing = new MarketingMessage(channel, "满减活动", "https://sale.com");
        Message notification = new NotificationMessage(channel, "到账通知", "您有一笔退款已到账");

        verification.send("13800138000");
        marketing.send("13800138000");
        notification.send("13800138000");

        assertEquals(3, channel.history.size());
        assertTrue(channel.history.get(0).contains("验证码"));
        assertTrue(channel.history.get(1).contains("限时优惠"));
        assertTrue(channel.history.get(2).contains("到账通知"));
    }

    @Test
    void messageServiceShouldSendAllMessages() {
        InMemoryChannel channel = new InMemoryChannel();
        MessageService service = new MessageService();
        service.addMessage(new VerificationMessage(channel, "444444", 5));
        service.addMessage(new NotificationMessage(channel, "测试", "测试内容"));

        service.sendAll("13800138000");

        assertEquals(2, channel.history.size());
    }
}
