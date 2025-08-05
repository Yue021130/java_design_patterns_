package com.pattern.factorymethod;

/**
 * 微信支付处理器工厂：Concrete Creator。
 */
public class WechatPayProcessorFactory implements PaymentProcessorFactory {

    @Override
    public PaymentProcessor createProcessor() {
        // 实际项目中从配置中心读取 mchId 和 appId
        return new WechatPayProcessor("1234567890", "wx2024appid");
    }

    @Override
    public boolean supports(String channel) {
        return "WECHAT".equalsIgnoreCase(channel);
    }
}
