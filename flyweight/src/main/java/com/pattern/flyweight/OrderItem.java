package com.pattern.flyweight;

import java.math.BigDecimal;

/**
 * 订单项：包含共享的 SKU 元数据（内部状态）和订单相关数据（外部状态）。
 */
public class OrderItem {

    private final String orderItemId;
    private final SkuMetadata skuMetadata;
    private final int quantity;
    private final BigDecimal soldPrice;

    public OrderItem(String orderItemId, SkuMetadata skuMetadata,
                     int quantity, BigDecimal soldPrice) {
        this.orderItemId = orderItemId;
        this.skuMetadata = skuMetadata;
        this.quantity = quantity;
        this.soldPrice = soldPrice;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public SkuMetadata getSkuMetadata() {
        return skuMetadata;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSoldPrice() {
        return soldPrice;
    }

    public BigDecimal getSubtotal() {
        return soldPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "OrderItem{orderItemId='" + orderItemId + "', sku=" + skuMetadata
                + ", quantity=" + quantity + ", soldPrice=" + soldPrice + "}";
    }
}
