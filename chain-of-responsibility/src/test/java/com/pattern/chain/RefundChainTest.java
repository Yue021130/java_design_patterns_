package com.pattern.chain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RefundChainTest {

    @Test
    void smallAmountShouldBeHandledByCustomerService() {
        RefundHandlerChain chain = new RefundHandlerChain();
        RefundRequest request = new RefundRequest("O-001", "U-001",
                new BigDecimal("80"), "质量问题");

        RefundResult result = chain.process(request);

        assertTrue(result.approved());
        assertEquals("客服", result.handlerName());
    }

    @Test
    void mediumAmountShouldBeHandledBySupervisor() {
        RefundHandlerChain chain = new RefundHandlerChain();
        RefundRequest request = new RefundRequest("O-002", "U-002",
                new BigDecimal("300"), "发错货");

        RefundResult result = chain.process(request);

        assertTrue(result.approved());
        assertEquals("主管", result.handlerName());
    }

    @Test
    void largeAmountShouldBeHandledByFinance() {
        RefundHandlerChain chain = new RefundHandlerChain();
        RefundRequest request = new RefundRequest("O-003", "U-003",
                new BigDecimal("2000"), "贵重物品退货");

        RefundResult result = chain.process(request);

        assertTrue(result.approved());
        assertEquals("财务", result.handlerName());
    }

    @Test
    void exactBoundaryShouldBeHandledByCurrentHandler() {
        RefundHandlerChain chain = new RefundHandlerChain();

        RefundRequest boundary100 = new RefundRequest("O-004", "U-004",
                new BigDecimal("100"), "边界测试");
        assertEquals("客服", chain.process(boundary100).handlerName());

        RefundRequest boundary500 = new RefundRequest("O-005", "U-005",
                new BigDecimal("500"), "边界测试");
        assertEquals("主管", chain.process(boundary500).handlerName());
    }

    @Test
    void unsupportedRequestShouldBeRejectedWhenNoHandlerAvailable() {
        CustomerServiceHandler customerService = new CustomerServiceHandler();
        RefundRequest request = new RefundRequest("O-006", "U-006",
                new BigDecimal("1000"), "无后续处理");

        RefundResult result = customerService.handle(request);

        assertFalse(result.approved());
        assertEquals("客服", result.handlerName());
    }
}
