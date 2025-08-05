package com.pattern.factorymethod;

import java.util.UUID;

/**
 * 支付宝处理器：Concrete Product。
 *
 * <p>模拟支付宝渠道特有的签名、沙箱校验等初始化逻辑。</p>
 */
public final class AlipayProcessor implements PaymentProcessor {

    private final String appId;
    private final String privateKey;

    public AlipayProcessor(String appId, String privateKey) {
        this.appId = appId;
        this.privateKey = privateKey;
    }

    @Override
    public PaymentResult pay(Order order) {
        // 模拟支付宝支付：拼接 appId + 订单号生成交易号
        String transactionId = "ALI_" + appId + "_" + UUID.randomUUID().toString().substring(0, 8);
        return new PaymentResult(transactionId, true,
                "支付宝支付成功，金额: " + order.amount());
    }

    @Override
    public String channel() {
        return "ALIPAY";
    }
}
