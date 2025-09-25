package com.pattern.flyweight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单服务：客户端，演示如何在创建订单项时复用 SKU 元数据。
 */
public class OrderService {

    private final SkuFlyweightFactory skuFactory = SkuFlyweightFactory.getInstance();
    private final List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 添加订单项。相同的 SKU 会复用同一个 SkuMetadata 对象。
     */
    public OrderItem addOrderItem(String orderItemId, String skuId, String name,
                                  String category, String imageUrl,
                                  int quantity, BigDecimal soldPrice) {
        SkuMetadata metadata = skuFactory.getSkuMetadata(skuId, name, category, imageUrl);
        OrderItem item = new OrderItem(orderItemId, metadata, quantity, soldPrice);
        orderItems.add(item);
        return item;
    }

    public List<OrderItem> getOrderItems() {
        return List.copyOf(orderItems);
    }

    public BigDecimal calculateTotal() {
        return orderItems.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
