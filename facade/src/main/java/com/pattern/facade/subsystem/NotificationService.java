package com.pattern.facade.subsystem;

/**
 * 通知子系统。
 */
public class NotificationService {

    public void sendOrderConfirmation(String userId, String orderId) {
        System.out.println("[通知] 发送订单确认，用户=" + userId + "，订单=" + orderId);
    }
}
