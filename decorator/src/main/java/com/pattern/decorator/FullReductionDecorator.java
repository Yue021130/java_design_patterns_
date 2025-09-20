package com.pattern.decorator;

import java.math.BigDecimal;

/**
 * 具体装饰器：满减优惠。
 */
public class FullReductionDecorator extends PriceDecorator {

    private final BigDecimal threshold;
    private final BigDecimal reduction;

    public FullReductionDecorator(PriceCalculator delegate,
                                  BigDecimal threshold,
                                  BigDecimal reduction) {
        super(delegate);
        this.threshold = threshold;
        this.reduction = reduction;
    }

    @Override
    public BigDecimal calculate(String skuId, BigDecimal originalPrice, OrderContext context) {
        BigDecimal price = delegate.calculate(skuId, originalPrice, context);
        if (price.compareTo(threshold) >= 0) {
            price = price.subtract(reduction);
            if (price.compareTo(BigDecimal.ZERO) < 0) {
                price = BigDecimal.ZERO;
            }
        }
        return price;
    }
}
