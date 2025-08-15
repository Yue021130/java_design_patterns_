package com.pattern.abstractfactory.aliyun;

import com.pattern.abstractfactory.MessageQueue;

/**
 * 阿里云 MNS：具体产品 C1。
 */
public class AliyunMns implements MessageQueue {

    private final String accessKeyId;
    private final String accessKeySecret;
    private final String region;

    public AliyunMns(String accessKeyId, String accessKeySecret, String region) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.region = region;
    }

    @Override
    public void sendMessage(String topic, String message) {
        System.out.println("[AliyunMNS] send to topic=" + topic + ", msg=" + message);
    }

    @Override
    public String receiveMessage(String topic) {
        return "[AliyunMNS] message from " + topic;
    }
}
