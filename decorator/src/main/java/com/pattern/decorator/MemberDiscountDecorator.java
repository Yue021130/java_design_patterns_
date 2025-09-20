package com.pattern.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 具体装饰器：会员折扣。
 */
public class MemberDiscountDecorator extends PriceDecorator {

    public MemberDiscountDecorator(PriceCalculator delegate) {
        super(delegate);
    }

    @Override
    public BigDecimal calculate(String skuId, BigDecimal originalPrice, OrderContext context) {
        BigDecimal price = delegate.calculate(skuId, originalPrice, context);
        BigDecimal discountRate = switch (context.memberLevel()) {
            case 1 -> new BigDecimal("0.95");
            case 2 -> new BigDecimal("0.90");
            case 3 -> new BigDecimal("0.85");
            default -> BigDecimal.ONE;
        };
        return price.multiply(discountRate).setScale(2, RoundingMode.HALF_UP);
    }
}
