package com.pattern.facade.subsystem;

/**
 * 物流子系统。
 */
public class LogisticsService {

    public String createShipment(String orderId, String address) {
        System.out.println("[物流] 创建运单，订单=" + orderId + "，地址=" + address);
        return "SF" + System.currentTimeMillis();
    }
}
