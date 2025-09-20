package com.pattern.decorator;

import java.math.BigDecimal;

/**
 * 组件接口：价格计算器。
 *
 * <p>装饰器模式允许在不修改原有对象的情况下，动态地给对象添加职责。</p>
 */
public interface PriceCalculator {

    /**
     * 根据 SKU 和订单上下文计算最终价格。
     */
    BigDecimal calculate(String skuId, BigDecimal originalPrice, OrderContext context);

    /**
     * 订单上下文（Java 17 Record）。
     */
    record OrderContext(
            String userId,
            Integer memberLevel,
            String couponCode,
            Integer points
    ) {
    }
}
