package com.pattern.decorator;

import java.math.BigDecimal;

/**
 * 具体装饰器：积分抵扣。
 *
 * <p>假设 100 积分 = 1 元，最多抵扣 50% 的价格。</p>
 */
public class PointsDeductionDecorator extends PriceDecorator {

    private static final BigDecimal POINTS_RATIO = new BigDecimal("0.01");
    private static final BigDecimal MAX_RATIO = new BigDecimal("0.50");

    public PointsDeductionDecorator(PriceCalculator delegate) {
        super(delegate);
    }

    @Override
    public BigDecimal calculate(String skuId, BigDecimal originalPrice, OrderContext context) {
        BigDecimal price = delegate.calculate(skuId, originalPrice, context);
        if (context.points() == null || context.points() <= 0) {
            return price;
        }

        BigDecimal maxDeduction = price.multiply(MAX_RATIO);
        BigDecimal pointsValue = BigDecimal.valueOf(context.points()).multiply(POINTS_RATIO);
        BigDecimal actualDeduction = pointsValue.min(maxDeduction);

        return price.subtract(actualDeduction);
    }
}
