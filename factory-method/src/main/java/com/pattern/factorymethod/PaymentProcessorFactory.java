package com.pattern.factorymethod;

/**
 * 支付处理器工厂：工厂方法模式中的 Creator 角色。
 *
 * <p>每个具体工厂负责创建一种支付渠道的处理器，将对象的创建延迟到子类。</p>
 */
public interface PaymentProcessorFactory {

    /**
     * 工厂方法：创建对应的支付处理器。
     */
    PaymentProcessor createProcessor();

    /**
     * 判断当前工厂是否支持指定的支付渠道。
     */
    boolean supports(String channel);
}
