package com.pattern.chain;

/**
 * 退款审批链：负责组装处理器链。
 */
public class RefundHandlerChain {

    private final RefundHandler head;

    public RefundHandlerChain() {
        CustomerServiceHandler customerService = new CustomerServiceHandler();
        SupervisorHandler supervisor = new SupervisorHandler();
        FinanceHandler finance = new FinanceHandler();

        customerService.setNext(supervisor);
        supervisor.setNext(finance);

        this.head = customerService;
    }

    public RefundResult process(RefundRequest request) {
        return head.handle(request);
    }
}
