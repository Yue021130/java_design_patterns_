package com.pattern.factorymethod;

import java.math.BigDecimal;
import java.util.List;

/**
 * 工厂方法模式客户端入口。
 */
public class FactoryMethodDemo {

    public static void main(String[] args) {
        // 模拟 Spring 注入所有工厂实例
        List<PaymentProcessorFactory> factories = List.of(
                new AlipayProcessorFactory(),
                new WechatPayProcessorFactory(),
                new UnionPayProcessorFactory()
        );

        PaymentService paymentService = new PaymentService(factories);

        var order = new PaymentProcessor.Order("ORD-20240730-001",
                new BigDecimal("199.99"), "WECHAT");

        var result = paymentService.pay(order);
        System.out.println("支付结果: " + result);
    }
}
