package com.pattern.facade;

import com.pattern.facade.subsystem.InventoryService;
import com.pattern.facade.subsystem.LogisticsService;
import com.pattern.facade.subsystem.NotificationService;
import com.pattern.facade.subsystem.PaymentService;
import com.pattern.facade.subsystem.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderFacadeTest {

    private OrderFacade facade;

    @BeforeEach
    void setUp() {
        facade = new OrderFacade(
                new InventoryService(),
                new PricingService(),
                new PaymentService(),
                new LogisticsService(),
                new NotificationService()
        );
    }

    @Test
    void shouldPlaceOrderSuccessfully() {
        var request = new OrderFacade.OrderRequest(
                "USER-001", "SKU-001", 2, "COUPON-20", "上海市");

        OrderFacade.OrderResult result = facade.placeOrder(request);

        assertTrue(result.success());
        assertNotNull(result.orderId());
        assertNotNull(result.trackingNo());
        assertEquals("下单成功", result.message());
    }

    @Test
    void shouldFailWhenStockInsufficient() {
        var request = new OrderFacade.OrderRequest(
                "USER-001", "SKU-001", 999, null, "上海市");

        OrderFacade.OrderResult result = facade.placeOrder(request);

        assertFalse(result.success());
        assertEquals("库存不足", result.message());
    }

    @Test
    void facadeShouldHideSubsystemComplexity() {
        // 客户端只需要与 OrderFacade 交互，不需要了解 InventoryService、PaymentService 等子系统
        var request = new OrderFacade.OrderRequest(
                "USER-002", "SKU-002", 1, null, "北京市");

        OrderFacade.OrderResult result = facade.placeOrder(request);

        assertTrue(result.success());
        assertNotNull(result.orderId());
    }
}
