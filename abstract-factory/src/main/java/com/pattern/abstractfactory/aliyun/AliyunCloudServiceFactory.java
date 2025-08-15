package com.pattern.abstractfactory.aliyun;

import com.pattern.abstractfactory.CloudServiceFactory;
import com.pattern.abstractfactory.MessageQueue;
import com.pattern.abstractfactory.ObjectStorage;
import com.pattern.abstractfactory.SmsService;

/**
 * 阿里云具体工厂：创建阿里云产品族。
 */
public class AliyunCloudServiceFactory implements CloudServiceFactory {

    private final String accessKeyId;
    private final String accessKeySecret;
    private final String region;

    public AliyunCloudServiceFactory(String accessKeyId, String accessKeySecret, String region) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.region = region;
    }

    @Override
    public ObjectStorage createObjectStorage() {
        return new AliyunOss(accessKeyId, accessKeySecret, region);
    }

    @Override
    public SmsService createSmsService() {
        return new AliyunSms(accessKeyId, accessKeySecret, region);
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new AliyunMns(accessKeyId, accessKeySecret, region);
    }
}
