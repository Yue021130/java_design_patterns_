package com.pattern.chain;

/**
 * 退款审批结果（Java 17 Record）。
 */
public record RefundResult(
        boolean approved,
        String handlerName,
        String message
) {
}
