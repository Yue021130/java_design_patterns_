package com.pattern.abstractfactory;

import java.util.Map;

/**
 * SaaS 平台客户端：使用抽象工厂创建并使用一整套云服务。
 *
 * <p>在实际项目中，工厂的实例通常由配置中心根据部署区域决定，
 * 例如国内用阿里云、海外用 AWS，从而保证同一环境使用同一厂商的产品族。</p>
 */
public class SaaSPlatform {

    private final ObjectStorage objectStorage;
    private final SmsService smsService;
    private final MessageQueue messageQueue;

    public SaaSPlatform(CloudServiceFactory factory) {
        this.objectStorage = factory.createObjectStorage();
        this.smsService = factory.createSmsService();
        this.messageQueue = factory.createMessageQueue();
    }

    public String uploadFile(String bucket, String fileName, byte[] data) {
        return objectStorage.upload(bucket, fileName, data);
    }

    public String sendNotification(String phone, String templateCode, Map<String, String> params) {
        return smsService.send(phone, templateCode, params);
    }

    public void pushEvent(String topic, String message) {
        messageQueue.sendMessage(topic, message);
    }
}
