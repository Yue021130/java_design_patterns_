package com.pattern.facade;

import com.pattern.facade.subsystem.InventoryService;
import com.pattern.facade.subsystem.LogisticsService;
import com.pattern.facade.subsystem.NotificationService;
import com.pattern.facade.subsystem.PaymentService;
import com.pattern.facade.subsystem.PricingService;

/**
 * 外观模式客户端入口。
 */
public class FacadeDemo {

    public static void main(String[] args) {
        OrderFacade facade = new OrderFacade(
                new InventoryService(),
                new PricingService(),
                new PaymentService(),
                new LogisticsService(),
                new NotificationService()
        );

        var request = new OrderFacade.OrderRequest(
                "USER-001",
                "SKU-001",
                2,
                "COUPON-20",
                "上海市浦东新区"
        );

        OrderFacade.OrderResult result = facade.placeOrder(request);
        System.out.println("下单结果: " + result);
    }
}
