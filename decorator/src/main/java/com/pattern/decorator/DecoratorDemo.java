package com.pattern.decorator;

import java.math.BigDecimal;

/**
 * 装饰器模式客户端入口。
 */
public class DecoratorDemo {

    public static void main(String[] args) {
        PromotionEngine engine = new PromotionEngine();
        PriceCalculator calculator = engine.buildDefaultCalculator();

        var context = new PriceCalculator.OrderContext(
                "USER-001", 2, "COUPON-50", 500);

        BigDecimal finalPrice = calculator.calculate(
                "SKU-001", new BigDecimal("300"), context);

        System.out.println("最终支付价格: " + finalPrice + " 元");

        // 也可以手动按需组装装饰器
        PriceCalculator simple = new MemberDiscountDecorator(
                new BasePriceCalculator());
        BigDecimal memberPrice = simple.calculate(
                "SKU-002", new BigDecimal("100"),
                new PriceCalculator.OrderContext("USER-002", 3, null, 0));
        System.out.println("仅会员折扣价: " + memberPrice + " 元");
    }
}
