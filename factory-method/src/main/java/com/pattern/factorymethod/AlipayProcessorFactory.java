package com.pattern.factorymethod;

/**
 * 支付宝处理器工厂：Concrete Creator。
 */
public class AlipayProcessorFactory implements PaymentProcessorFactory {

    @Override
    public PaymentProcessor createProcessor() {
        // 实际项目中从配置中心读取 appId 和私钥
        return new AlipayProcessor("2024APPID", "***encrypted-private-key***");
    }

    @Override
    public boolean supports(String channel) {
        return "ALIPAY".equalsIgnoreCase(channel);
    }
}
