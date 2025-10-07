package com.pattern.chain;

/**
 * 退款审批处理器接口。
 */
public interface RefundHandler {

    /**
     * 设置下一个处理器。
     */
    void setNext(RefundHandler next);

    /**
     * 处理退款申请。
     */
    RefundResult handle(RefundRequest request);
}
