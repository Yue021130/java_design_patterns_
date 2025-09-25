package com.pattern.flyweight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FlyweightTest {

    @BeforeEach
    void setUp() {
        // 注意：工厂是单例，测试之间共享池。这里简单重置通过创建新 OrderService 不影响池。
        // 实际项目中应谨慎处理单例状态。
    }

    @Test
    void factoryShouldReturnSameInstanceForSameSku() {
        SkuFlyweightFactory factory = SkuFlyweightFactory.getInstance();

        SkuMetadata metadata1 = factory.getSkuMetadata(
                "SKU-001", "iPhone", "手机", "img1.jpg");
        SkuMetadata metadata2 = factory.getSkuMetadata(
                "SKU-001", "iPhone", "手机", "img1.jpg");

        assertSame(metadata1, metadata2);
    }

    @Test
    void factoryShouldCreateDifferentInstancesForDifferentSkus() {
        SkuFlyweightFactory factory = SkuFlyweightFactory.getInstance();

        SkuMetadata metadata1 = factory.getSkuMetadata(
                "SKU-001", "iPhone", "手机", "img1.jpg");
        SkuMetadata metadata2 = factory.getSkuMetadata(
                "SKU-002", "MacBook", "电脑", "img2.jpg");

        assertNotSame(metadata1, metadata2);
    }

    @Test
    void orderItemsShouldShareSkuMetadata() {
        OrderService orderService = new OrderService();

        orderService.addOrderItem("ITEM-1", "SKU-001", "iPhone", "手机", "img1.jpg",
                1, new BigDecimal("100"));
        orderService.addOrderItem("ITEM-2", "SKU-001", "iPhone", "手机", "img1.jpg",
                2, new BigDecimal("100"));

        OrderItem item1 = orderService.getOrderItems().get(0);
        OrderItem item2 = orderService.getOrderItems().get(1);

        assertSame(item1.getSkuMetadata(), item2.getSkuMetadata());
        assertNotEquals(item1.getOrderItemId(), item2.getOrderItemId());
    }

    @Test
    void shouldReduceMetadataObjectCount() {
        OrderService orderService = new OrderService();
        SkuFlyweightFactory factory = SkuFlyweightFactory.getInstance();

        int initialSize = factory.getPoolSize();
        String uniqueSkuId = "SKU-UNIQUE-" + System.currentTimeMillis();

        IntStream.range(0, 100).forEach(i ->
                orderService.addOrderItem(
                        "ITEM-" + i,
                        uniqueSkuId,
                        "iPhone",
                        "手机",
                        "img1.jpg",
                        1,
                        new BigDecimal("100")
                )
        );

        // 100 个订单项只应创建 1 个新的 SKU 元数据对象
        assertEquals(initialSize + 1, factory.getPoolSize());
        assertEquals(100, orderService.getOrderItems().size());
    }

    @Test
    void shouldCalculateSubtotalCorrectly() {
        OrderService orderService = new OrderService();
        orderService.addOrderItem("ITEM-1", "SKU-001", "iPhone", "手机", "img1.jpg",
                3, new BigDecimal("99.99"));

        OrderItem item = orderService.getOrderItems().get(0);
        assertEquals(new BigDecimal("299.97"), item.getSubtotal());
    }
}
