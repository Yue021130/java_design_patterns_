package com.pattern.chain;

/**
 * 抽象处理器：提供链式传递的基础设施。
 */
public abstract class AbstractRefundHandler implements RefundHandler {

    protected RefundHandler next;
    protected final String handlerName;

    protected AbstractRefundHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    @Override
    public void setNext(RefundHandler next) {
        this.next = next;
    }

    /**
     * 如果当前处理器不处理，则传递给下一个处理器。
     */
    protected RefundResult passToNext(RefundRequest request) {
        if (next == null) {
            return new RefundResult(false, handlerName,
                    "无法处理：没有后续审批节点");
        }
        return next.handle(request);
    }
}
