package com.pattern.facade.subsystem;

import java.math.BigDecimal;

/**
 * 价格子系统。
 */
public class PricingService {

    public BigDecimal calculatePrice(String skuId, int quantity, String couponCode) {
        System.out.println("[价格] 计算 SKU=" + skuId + "，数量=" + quantity + "，优惠券=" + couponCode);
        // 模拟价格计算：单价 100，有券减 20
        BigDecimal unitPrice = new BigDecimal("100");
        BigDecimal couponDiscount = "COUPON-20".equals(couponCode) ? new BigDecimal("20") : BigDecimal.ZERO;
        return unitPrice.multiply(BigDecimal.valueOf(quantity)).subtract(couponDiscount);
    }
}
