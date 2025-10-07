package com.pattern.chain;

import java.math.BigDecimal;

/**
 * 客服处理器：处理小额退款（<= 100 元）。
 */
public class CustomerServiceHandler extends AbstractRefundHandler {

    public CustomerServiceHandler() {
        super("客服");
    }

    @Override
    public RefundResult handle(RefundRequest request) {
        if (request.amount().compareTo(new BigDecimal("100")) <= 0) {
            return new RefundResult(true, handlerName,
                    "客服已审批通过，金额：" + request.amount());
        }
        return passToNext(request);
    }
}
