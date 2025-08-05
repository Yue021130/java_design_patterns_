package com.pattern.factorymethod;

import java.util.List;
import java.util.Objects;

/**
 * 支付服务：客户端代码，依赖工厂接口而非具体实现。
 *
 * <p>在 Spring 项目中，通常由容器注入所有 {@link PaymentProcessorFactory} 实例。</p>
 */
public class PaymentService {

    private final List<PaymentProcessorFactory> factories;

    public PaymentService(List<PaymentProcessorFactory> factories) {
        this.factories = List.copyOf(factories);
    }

    /**
     * 根据订单渠道选择对应工厂创建处理器并执行支付。
     */
    public PaymentProcessor.PaymentResult pay(PaymentProcessor.Order order) {
        Objects.requireNonNull(order, "订单不能为空");

        return factories.stream()
                .filter(factory -> factory.supports(order.channel()))
                .findFirst()
                .map(PaymentProcessorFactory::createProcessor)
                .map(processor -> processor.pay(order))
                .orElseThrow(() -> new UnsupportedOperationException(
                        "不支持的支付渠道: " + order.channel()));
    }
}
