package com.pattern.chain;

import java.math.BigDecimal;

/**
 * 退款申请（Java 17 Record）。
 */
public record RefundRequest(
        String orderId,
        String userId,
        BigDecimal amount,
        String reason
) {
}
