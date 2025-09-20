package com.pattern.decorator;

import java.math.BigDecimal;

/**
 * 促销引擎：客户端，负责按业务规则组装装饰器链。
 */
public class PromotionEngine {

    /**
     * 构建默认的促销计算链：会员折扣 → 满减 → 优惠券 → 积分抵扣。
     */
    public PriceCalculator buildDefaultCalculator() {
        PriceCalculator calculator = new BasePriceCalculator();
        calculator = new MemberDiscountDecorator(calculator);
        calculator = new FullReductionDecorator(calculator,
                new BigDecimal("200"), new BigDecimal("30"));
        calculator = new CouponDecorator(calculator, "COUPON-50", new BigDecimal("50"));
        calculator = new PointsDeductionDecorator(calculator);
        return calculator;
    }
}
