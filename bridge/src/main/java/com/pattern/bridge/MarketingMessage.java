package com.pattern.bridge;

/**
 * 扩展抽象化角色：营销消息。
 */
public class MarketingMessage extends Message {

    private final String promotion;
    private final String landingPage;

    public MarketingMessage(MessageChannel channel, String promotion, String landingPage) {
        super(channel);
        this.promotion = promotion;
        this.landingPage = landingPage;
    }

    @Override
    public void send(String recipient) {
        String content = String.format("【限时优惠】%s，点击查看 %s。回复 TD 退订。",
                promotion, landingPage);
        channel.send(recipient, content);
    }
}
