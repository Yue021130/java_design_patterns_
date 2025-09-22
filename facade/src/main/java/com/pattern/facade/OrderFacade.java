package com.pattern.facade;

import com.pattern.facade.subsystem.InventoryService;
import com.pattern.facade.subsystem.LogisticsService;
import com.pattern.facade.subsystem.NotificationService;
import com.pattern.facade.subsystem.PaymentService;
import com.pattern.facade.subsystem.PricingService;

import java.math.BigDecimal;

/**
 * 下单外观：为复杂的下单流程提供统一、简洁的入口。
 *
 * <p>客户端无需关心库存、价格、支付、物流、通知等子系统的调用顺序和依赖关系。</p>
 */
public class OrderFacade {

    private final InventoryService inventoryService;
    private final PricingService pricingService;
    private final PaymentService paymentService;
    private final LogisticsService logisticsService;
    private final NotificationService notificationService;

    public OrderFacade(InventoryService inventoryService,
                       PricingService pricingService,
                       PaymentService paymentService,
                       LogisticsService logisticsService,
                       NotificationService notificationService) {
        this.inventoryService = inventoryService;
        this.pricingService = pricingService;
        this.paymentService = paymentService;
        this.logisticsService = logisticsService;
        this.notificationService = notificationService;
    }

    /**
     * 统一下单入口。
     */
    public OrderResult placeOrder(OrderRequest request) {
        // 1. 扣减库存
        boolean stockOk = inventoryService.deductStock(request.skuId(), request.quantity());
        if (!stockOk) {
            return new OrderResult(false, null, null, "库存不足");
        }

        try {
            // 2. 计算价格
            BigDecimal price = pricingService.calculatePrice(
                    request.skuId(), request.quantity(), request.couponCode());

            // 3. 发起支付
            PaymentService.PaymentResult payment = paymentService.charge(request.userId(), price);
            if (!payment.success()) {
                inventoryService.rollbackStock(request.skuId(), request.quantity());
                return new OrderResult(false, null, null, payment.message());
            }

            // 4. 生成订单号（简化处理）
            String orderId = "ORD-" + System.currentTimeMillis();

            // 5. 创建物流单
            String trackingNo = logisticsService.createShipment(orderId, request.address());

            // 6. 发送通知
            notificationService.sendOrderConfirmation(request.userId(), orderId);

            return new OrderResult(true, orderId, trackingNo, "下单成功");
        } catch (Exception e) {
            inventoryService.rollbackStock(request.skuId(), request.quantity());
            return new OrderResult(false, null, null, "下单异常: " + e.getMessage());
        }
    }

    /**
     * 下单请求（Java 17 Record）。
     */
    public record OrderRequest(
            String userId,
            String skuId,
            int quantity,
            String couponCode,
            String address
    ) {
    }

    /**
     * 下单结果（Java 17 Record）。
     */
    public record OrderResult(
            boolean success,
            String orderId,
            String trackingNo,
            String message
    ) {
    }
}
