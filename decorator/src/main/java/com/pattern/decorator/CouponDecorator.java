package com.pattern.decorator;

import java.math.BigDecimal;

/**
 * 具体装饰器：固定金额优惠券。
 */
public class CouponDecorator extends PriceDecorator {

    private final String couponCode;
    private final BigDecimal deduction;

    public CouponDecorator(PriceCalculator delegate, String couponCode, BigDecimal deduction) {
        super(delegate);
        this.couponCode = couponCode;
        this.deduction = deduction;
    }

    @Override
    public BigDecimal calculate(String skuId, BigDecimal originalPrice, OrderContext context) {
        BigDecimal price = delegate.calculate(skuId, originalPrice, context);
        if (couponCode.equals(context.couponCode())) {
            price = price.subtract(deduction);
            if (price.compareTo(BigDecimal.ZERO) < 0) {
                price = BigDecimal.ZERO;
            }
        }
        return price;
    }
}
