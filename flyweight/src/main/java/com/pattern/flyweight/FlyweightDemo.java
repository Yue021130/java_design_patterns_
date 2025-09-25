package com.pattern.flyweight;

import java.math.BigDecimal;
import java.util.stream.IntStream;

/**
 * 享元模式客户端入口。
 */
public class FlyweightDemo {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        SkuFlyweightFactory skuFactory = SkuFlyweightFactory.getInstance();

        // 模拟 1000 个订单项，但只有 3 种不同的 SKU
        String[] skuIds = {"SKU-001", "SKU-002", "SKU-003"};
        String[] names = {"iPhone 15", "MacBook Pro", "AirPods Pro"};
        String[] categories = {"手机", "电脑", "配件"};

        IntStream.range(0, 1000).forEach(i -> {
            int index = i % 3;
            orderService.addOrderItem(
                    "ITEM-" + i,
                    skuIds[index],
                    names[index],
                    categories[index],
                    "https://img.example.com/" + skuIds[index] + ".jpg",
                    1,
                    new BigDecimal("100")
            );
        });

        System.out.println("订单项总数: " + orderService.getOrderItems().size());
        System.out.println("SKU 元数据对象数: " + skuFactory.getPoolSize());
        System.out.println("订单总价: " + orderService.calculateTotal());

        // 验证共享：不同订单项引用同一个 SKU 元数据对象
        OrderItem item1 = orderService.getOrderItems().get(0);
        OrderItem item4 = orderService.getOrderItems().get(3);
        System.out.println("item1 和 item4 是否共享同一个 SkuMetadata: "
                + (item1.getSkuMetadata() == item4.getSkuMetadata()));
    }
}
