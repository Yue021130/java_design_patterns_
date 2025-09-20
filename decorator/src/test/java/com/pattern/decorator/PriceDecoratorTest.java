package com.pattern.decorator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceDecoratorTest {

    @Test
    void basePriceShouldReturnOriginalPrice() {
        PriceCalculator calculator = new BasePriceCalculator();
        BigDecimal price = calculator.calculate("SKU-001",
                new BigDecimal("100"),
                new PriceCalculator.OrderContext("U1", 0, null, 0));
        assertEquals(new BigDecimal("100"), price);
    }

    @Test
    void memberDiscountShouldApplyCorrectRate() {
        PriceCalculator calculator = new MemberDiscountDecorator(new BasePriceCalculator());

        BigDecimal level0 = calculator.calculate("SKU-001", new BigDecimal("100"),
                new PriceCalculator.OrderContext("U1", 0, null, 0));
        assertEquals(new BigDecimal("100.00"), level0);

        BigDecimal level1 = calculator.calculate("SKU-001", new BigDecimal("100"),
                new PriceCalculator.OrderContext("U1", 1, null, 0));
        assertEquals(new BigDecimal("95.00"), level1);

        BigDecimal level3 = calculator.calculate("SKU-001", new BigDecimal("100"),
                new PriceCalculator.OrderContext("U1", 3, null, 0));
        assertEquals(new BigDecimal("85.00"), level3);
    }

    @Test
    void couponShouldDeductWhenMatched() {
        PriceCalculator calculator = new CouponDecorator(
                new BasePriceCalculator(), "COUPON-50", new BigDecimal("50"));

        BigDecimal withCoupon = calculator.calculate("SKU-001", new BigDecimal("100"),
                new PriceCalculator.OrderContext("U1", 0, "COUPON-50", 0));
        assertEquals(new BigDecimal("50"), withCoupon);

        BigDecimal withoutCoupon = calculator.calculate("SKU-001", new BigDecimal("100"),
                new PriceCalculator.OrderContext("U1", 0, "OTHER", 0));
        assertEquals(new BigDecimal("100"), withoutCoupon);
    }

    @Test
    void fullReductionShouldApplyWhenThresholdMet() {
        PriceCalculator calculator = new FullReductionDecorator(
                new BasePriceCalculator(),
                new BigDecimal("200"), new BigDecimal("30"));

        BigDecimal eligible = calculator.calculate("SKU-001", new BigDecimal("250"),
                new PriceCalculator.OrderContext("U1", 0, null, 0));
        assertEquals(new BigDecimal("220"), eligible);

        BigDecimal notEligible = calculator.calculate("SKU-001", new BigDecimal("150"),
                new PriceCalculator.OrderContext("U1", 0, null, 0));
        assertEquals(new BigDecimal("150"), notEligible);
    }

    @Test
    void decoratorsShouldBeComposable() {
        PriceCalculator calculator = new BasePriceCalculator();
        calculator = new MemberDiscountDecorator(calculator);
        calculator = new CouponDecorator(calculator, "COUPON-50", new BigDecimal("50"));

        // 原价 300，会员 9 折 -> 270，再用 50 券 -> 220
        BigDecimal price = calculator.calculate("SKU-001", new BigDecimal("300"),
                new PriceCalculator.OrderContext("U1", 2, "COUPON-50", 0));
        assertEquals(new BigDecimal("220.00"), price);
    }

    @Test
    void promotionEngineShouldBuildDefaultChain() {
        PromotionEngine engine = new PromotionEngine();
        PriceCalculator calculator = engine.buildDefaultCalculator();

        BigDecimal price = calculator.calculate("SKU-001", new BigDecimal("300"),
                new PriceCalculator.OrderContext("U1", 2, "COUPON-50", 500));

        assertTrue(price.compareTo(new BigDecimal("300")) < 0);
        assertTrue(price.compareTo(BigDecimal.ZERO) >= 0);
    }
}
