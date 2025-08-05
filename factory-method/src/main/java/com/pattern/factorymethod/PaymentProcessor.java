package com.pattern.factorymethod;

import java.math.BigDecimal;

/**
 * 支付处理器抽象：工厂方法模式中的 Product 角色。
 *
 * <p>使用 sealed interface 限制实现类，利用 Java 17 特性在编译期约束产品族。</p>
 */
public sealed interface PaymentProcessor permits AlipayProcessor, WechatPayProcessor, UnionPayProcessor {

    /**
     * 执行支付。
     *
     * @param order 订单信息
     * @return 支付结果
     */
    PaymentResult pay(Order order);

    /**
     * 获取该处理器对应的支付渠道编码。
     */
    String channel();

    /**
     * 订单信息（Java 17 Record）。
     */
    record Order(String orderId, BigDecimal amount, String channel) {
    }

    /**
     * 支付结果（Java 17 Record）。
     */
    record PaymentResult(String transactionId, boolean success, String message) {
    }
}
