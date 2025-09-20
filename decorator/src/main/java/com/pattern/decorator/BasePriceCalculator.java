package com.pattern.decorator;

import java.math.BigDecimal;

/**
 * 具体组件：基础价格计算器。
 *
 * <p>直接返回商品原价，不做任何处理。</p>
 */
public class BasePriceCalculator implements PriceCalculator {

    @Override
    public BigDecimal calculate(String skuId, BigDecimal originalPrice, OrderContext context) {
        return originalPrice;
    }
}
