package com.pattern.decorator;

import java.math.BigDecimal;

/**
 * 抽象装饰器：持有被装饰的价格计算器，并在计算前后添加额外逻辑。
 */
public abstract class PriceDecorator implements PriceCalculator {

    protected final PriceCalculator delegate;

    protected PriceDecorator(PriceCalculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public abstract BigDecimal calculate(String skuId, BigDecimal originalPrice, OrderContext context);
}
