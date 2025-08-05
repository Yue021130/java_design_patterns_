package com.pattern.factorymethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        List<PaymentProcessorFactory> factories = List.of(
                new AlipayProcessorFactory(),
                new WechatPayProcessorFactory(),
                new UnionPayProcessorFactory()
        );
        paymentService = new PaymentService(factories);
    }

    @Test
    void shouldRouteToAlipay() {
        var order = new PaymentProcessor.Order("ORD-001", new BigDecimal("100"), "ALIPAY");
        var result = paymentService.pay(order);
        assertTrue(result.success());
        assertTrue(result.transactionId().startsWith("ALI_"));
    }

    @Test
    void shouldRouteToWechatPay() {
        var order = new PaymentProcessor.Order("ORD-002", new BigDecimal("200"), "WECHAT");
        var result = paymentService.pay(order);
        assertTrue(result.success());
        assertTrue(result.transactionId().startsWith("WX_"));
    }

    @Test
    void shouldRouteToUnionPay() {
        var order = new PaymentProcessor.Order("ORD-003", new BigDecimal("300"), "UNIONPAY");
        var result = paymentService.pay(order);
        assertTrue(result.success());
        assertTrue(result.transactionId().startsWith("UNION_"));
    }

    @Test
    void shouldThrowForUnsupportedChannel() {
        var order = new PaymentProcessor.Order("ORD-004", new BigDecimal("400"), "BITCOIN");
        assertThrows(UnsupportedOperationException.class, () -> paymentService.pay(order));
    }

    @Test
    void factoryShouldCreateCorrectProcessor() {
        PaymentProcessorFactory factory = new AlipayProcessorFactory();
        PaymentProcessor processor = factory.createProcessor();
        assertInstanceOf(AlipayProcessor.class, processor);
        assertEquals("ALIPAY", processor.channel());
    }
}
