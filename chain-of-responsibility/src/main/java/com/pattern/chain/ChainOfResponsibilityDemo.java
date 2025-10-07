package com.pattern.chain;

import java.math.BigDecimal;

/**
 * 责任链模式客户端入口。
 */
public class ChainOfResponsibilityDemo {

    public static void main(String[] args) {
        RefundHandlerChain chain = new RefundHandlerChain();

        RefundRequest small = new RefundRequest("O-001", "U-001",
                new BigDecimal("50"), "商品质量问题");
        RefundRequest medium = new RefundRequest("O-002", "U-002",
                new BigDecimal("300"), "发错货");
        RefundRequest large = new RefundRequest("O-003", "U-003",
                new BigDecimal("2000"), "贵重物品退货");

        System.out.println("小额退款: " + chain.process(small));
        System.out.println("中额退款: " + chain.process(medium));
        System.out.println("大额退款: " + chain.process(large));
    }
}
