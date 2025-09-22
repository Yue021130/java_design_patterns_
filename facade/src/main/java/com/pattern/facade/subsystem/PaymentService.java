package com.pattern.facade.subsystem;

import java.math.BigDecimal;

/**
 * 支付子系统。
 */
public class PaymentService {

    public PaymentResult charge(String userId, BigDecimal amount) {
        System.out.println("[支付] 用户=" + userId + "，金额=" + amount);
        // 模拟支付成功
        return new PaymentResult(true, "PAY-" + System.currentTimeMillis(), "支付成功");
    }

    public record PaymentResult(boolean success, String transactionId, String message) {
    }
}
