package com.pattern.proxy;

import java.math.BigDecimal;

/**
 * 商品信息（Java 17 Record）。
 */
public record Product(
        String productId,
        String name,
        String description,
        BigDecimal price,
        int stock
) {
}
