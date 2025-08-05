package com.pattern.factorymethod;

import java.util.UUID;

/**
 * 微信支付处理器：Concrete Product。
 *
 * <p>模拟微信渠道特有的 mchid、openid 等初始化逻辑。</p>
 */
public final class WechatPayProcessor implements PaymentProcessor {

    private final String mchId;
    private final String appId;

    public WechatPayProcessor(String mchId, String appId) {
        this.mchId = mchId;
        this.appId = appId;
    }

    @Override
    public PaymentResult pay(Order order) {
        // 模拟微信支付：拼接 mchid + 订单号生成交易号
        String transactionId = "WX_" + mchId + "_" + UUID.randomUUID().toString().substring(0, 8);
        return new PaymentResult(transactionId, true,
                "微信支付成功，金额: " + order.amount());
    }

    @Override
    public String channel() {
        return "WECHAT";
    }
}
