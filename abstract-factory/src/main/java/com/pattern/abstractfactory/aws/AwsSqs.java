package com.pattern.abstractfactory.aws;

import com.pattern.abstractfactory.MessageQueue;

/**
 * AWS SQS：具体产品 C2。
 */
public class AwsSqs implements MessageQueue {

    private final String accessKeyId;
    private final String secretAccessKey;
    private final String region;

    public AwsSqs(String accessKeyId, String secretAccessKey, String region) {
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.region = region;
    }

    @Override
    public void sendMessage(String topic, String message) {
        System.out.println("[AWS-SQS] send to queue=" + topic + ", msg=" + message);
    }

    @Override
    public String receiveMessage(String topic) {
        return "[AWS-SQS] message from " + topic;
    }
}
