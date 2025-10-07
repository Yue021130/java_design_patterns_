package com.pattern.chain;

/**
 * 财务处理器：处理所有大额退款。
 */
public class FinanceHandler extends AbstractRefundHandler {

    public FinanceHandler() {
        super("财务");
    }

    @Override
    public RefundResult handle(RefundRequest request) {
        // 财务处理所有剩余金额
        return new RefundResult(true, handlerName,
                "财务已审批通过，金额：" + request.amount());
    }
}
