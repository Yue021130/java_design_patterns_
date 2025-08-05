package com.pattern.factorymethod;

import java.util.UUID;

/**
 * 银联支付处理器：Concrete Product。
 *
 * <p>模拟银联渠道特有的商户号、证书等初始化逻辑。</p>
 */
public final class UnionPayProcessor implements PaymentProcessor {

    private final String merchantId;
    private final String certPath;

    public UnionPayProcessor(String merchantId, String certPath) {
        this.merchantId = merchantId;
        this.certPath = certPath;
    }

    @Override
    public PaymentResult pay(Order order) {
        // 模拟银联支付：拼接 merchantId + 订单号生成交易号
        String transactionId = "UNION_" + merchantId + "_" + UUID.randomUUID().toString().substring(0, 8);
        return new PaymentResult(transactionId, true,
                "银联支付成功，金额: " + order.amount());
    }

    @Override
    public String channel() {
        return "UNIONPAY";
    }
}
