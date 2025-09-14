package com.pattern.bridge;

/**
 * 扩展抽象化角色：验证码消息。
 */
public class VerificationMessage extends Message {

    private final String code;
    private final int expireMinutes;

    public VerificationMessage(MessageChannel channel, String code, int expireMinutes) {
        super(channel);
        this.code = code;
        this.expireMinutes = expireMinutes;
    }

    @Override
    public void send(String recipient) {
        String content = String.format("您的验证码是 %s，%d 分钟内有效。如非本人操作，请忽略。",
                code, expireMinutes);
        channel.send(recipient, content);
    }
}
