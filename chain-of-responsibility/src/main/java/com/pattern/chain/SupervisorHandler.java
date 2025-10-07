package com.pattern.chain;

import java.math.BigDecimal;

/**
 * 主管处理器：处理中等金额退款（<= 500 元）。
 */
public class SupervisorHandler extends AbstractRefundHandler {

    public SupervisorHandler() {
        super("主管");
    }

    @Override
    public RefundResult handle(RefundRequest request) {
        if (request.amount().compareTo(new BigDecimal("500")) <= 0) {
            return new RefundResult(true, handlerName,
                    "主管已审批通过，金额：" + request.amount());
        }
        return passToNext(request);
    }
}
