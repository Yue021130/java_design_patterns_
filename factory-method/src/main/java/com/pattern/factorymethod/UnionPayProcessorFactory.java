package com.pattern.factorymethod;

/**
 * 银联支付处理器工厂：Concrete Creator。
 */
public class UnionPayProcessorFactory implements PaymentProcessorFactory {

    @Override
    public PaymentProcessor createProcessor() {
        // 实际项目中从配置中心读取商户号和证书路径
        return new UnionPayProcessor("777777777", "/certs/unionpay.pfx");
    }

    @Override
    public boolean supports(String channel) {
        return "UNIONPAY".equalsIgnoreCase(channel);
    }
}
